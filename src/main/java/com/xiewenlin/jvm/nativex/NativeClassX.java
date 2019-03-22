package com.xiewenlin.jvm.nativex;

import com.xiewenlin.jvm.langx.*;
import com.xiewenlin.jvm.runtime.RunEnvironment;
import jdk.internal.org.objectweb.asm.Type;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xiewenlin@frtauto.com
 * @ClassName NativeClassX
 * @Description 创建Java虚拟机提供的原生类：native类(可直接调用原生类执行，比如 java.langx.System)
 * @Date 2019/3/21 16:06
 * @Version V1.0.0
 */
public class NativeClassX implements ClassX{
    private final Class nativeClass;
    private final String className;
    private final ClassLoaderX classLoader;
    private final HashMap<Map.Entry<String, String>, MethodX> methods = new HashMap<>();

    public NativeClassX(ClassLoaderX classLoader, Class nativeClass){
        this.nativeClass = nativeClass;
        this.classLoader = classLoader;
        this.className = nativeClass.getName();
        //普通方法
        for (Method method : nativeClass.getMethods()) {
            methods.put(
                    new AbstractMap.SimpleEntry<>(method.getName(), Type.getMethodDescriptor(method)),
                    new NativeMethodX(this, method)
            );
        }
        //构造方法
        for( Constructor constructor : nativeClass.getConstructors()){
            methods.put(
                    new AbstractMap.SimpleEntry<>("<init>", Type.getConstructorDescriptor(constructor)),
                    new NativeConstructorX(this, constructor)
            );
        }
    }

    @Override
    public ObjectX newInstance(RunEnvironment env) throws InstantiationException, IllegalAccessException {
        return new NativeObjectX(this);
    }

    @Override
    public MethodX getMethod(String name, String desc) throws NoSuchMethodException {
        MethodX found = methods.get(new AbstractMap.SimpleEntry<>(name, desc));
        if(found == null){
            throw new NoSuchMethodException(name+":"+desc+" not exist");
        }
        return found;
    }

    @Override
    public boolean hasMethod(String name, String desc) {
        MethodX found = methods.get(new AbstractMap.SimpleEntry<>(name, desc));
        return found != null;
    }

    @Override
    public FieldX getField(String name) throws NoSuchFieldException, IllegalAccessException {
        return new NativeFieldX(this, nativeClass.getField(name));
    }

    @Override
    public ClassLoaderX getClassLoader() {
        return classLoader;
    }

    @Override
    public ClassX getSuperClass() throws ClassNotFoundException {
        return classLoader.loadClass(nativeClass.getSuperclass().getName());
    }

    @Override
    public String getName() {
        return className;
    }

    public Class getNativeClass() {
        return nativeClass;
    }
}
