package com.xiewenlin.jvm.user;

import com.sun.tools.classfile.*;
import com.xiewenlin.jvm.langx.*;
import com.xiewenlin.jvm.runtime.RunEnvironment;

import java.io.IOException;
import java.nio.file.Path;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xiewenlin@frtauto.com
 * @ClassName UserClass
 * @Description UserClassX表示用户定义的类，通过字节码执行，也就是这个例子中的Hello JVM ( 区别于 native 类 )
 * @Date 2019/3/21 16:07
 * @Version V1.0.0
 */
public class UserClass implements ClassX {

    private final ClassFile classFile;
    private final String className;
    private final ClassLoaderX classLoader;
    private Map<Map.Entry<String, String>, UserMethod> methods = new HashMap<>();
    private Map<String, FieldX> fields = new HashMap<>();
    /**
     *  是否已经初始化
     */
    boolean inited = false;

    static public UserClass read(ClassLoaderX classLoader, Path path) throws ClassNotFoundException {
        try {
            return  new UserClass(classLoader, ClassFile.read(path));
        } catch (IOException e) {
            throw new ClassNotFoundException(e.toString());
        } catch (Exception e) {
            throw new InternalError(e);
        }
    }
    /**
     * @param classLoader
     * @param classFile
     * @throws ConstantPoolException
     */
    private UserClass(ClassLoaderX classLoader, ClassFile classFile) throws ConstantPoolException, Descriptor.InvalidDescriptor {
        this.classFile = classFile;
        this.className = classFile.getName();
        this.classLoader = classLoader;
        for (Method method : classFile.methods) {
            String name = method.getName(classFile.constant_pool);
            String desc = method.descriptor.getValue(classFile.constant_pool);
            methods.put(new AbstractMap.SimpleEntry<>(name, desc), new UserMethod(this, method));
        }
        //准备阶段
        prepare();
    }

    /**
     * 准备阶段（Preparation）
     * 分配静态变量，并初始化为默认值，但不会执行任何字节码，在初始化阶段（clinit) 会有显式的初始化器来初始化这些静态字段，所以准备阶段不做
     * 这些事情。
     * @see `http://docs.oracle.com/javase/specs/jvms/se7/html/jvms-5.html#jvms-5.4.2`
     */
    private void prepare() throws ConstantPoolException, Descriptor.InvalidDescriptor {
        for(Field i : this.classFile.fields){
            if(i.access_flags.is(AccessFlags.ACC_STATIC)){
                fields.put(i.getName(classFile.constant_pool), new UserStaticField(this, i));
            }else{
                fields.put(i.getName(classFile.constant_pool), new UserObjectField(this, i));
            }
        }
    }

    /**
     * 初始化阶段（Initialization）
     * 调用类的<clinit>方法（如果有）
     * @see `http://docs.oracle.com/javase/specs/jvms/se7/html/jvms-5.html#jvms-5.5`
     */
    public void clinit(RunEnvironment env) throws Exception {
        if(inited){
            return;
        }
        synchronized(this){
            if(inited){
                return;
            }
            inited = true;
            UserMethod method = methods.get(new AbstractMap.SimpleEntry<>("<clinit>", "()V"));
            if(method != null){
                method.call(env, null);
            }
        }
    }

    @Override
    public ObjectX newInstance(RunEnvironment env) throws InstantiationException, IllegalAccessException {
        try {
            clinit(env);
        } catch (Exception e) {
            throw new InstantiationException(e.getMessage());
        }
        return new UserObject(env, this);
    }

    @Override
    public MethodX getMethod(String name, String desc) throws NoSuchMethodException {
        UserMethod method = methods.get(new AbstractMap.SimpleEntry<>(name, desc));
        if(method == null){
            throw new NoSuchMethodException("method "+name+":"+ desc+" not exist");
        }
        return method;
    }

    @Override
    public boolean hasMethod(String name, String desc) {
        UserMethod method = methods.get(new AbstractMap.SimpleEntry<>(name, desc));
        return method != null;
    }

    @Override
    public FieldX getField(String name) throws NoSuchFieldException, IllegalAccessException {
        FieldX field = fields.get(name);
        if(field == null){
            throw new NoSuchFieldException("field "+name+" of "+ className+" not exist");
        }
        return field;
    }

    @Override
    public ClassLoaderX getClassLoader() {
        return classLoader;
    }

    @Override
    public ClassX getSuperClass() throws ClassNotFoundException {
        try {
            return classLoader.loadClass(classFile.getSuperclassName());
        } catch (ConstantPoolException e) {
            throw new ClassNotFoundException(e.getMessage());
        }
    }

    @Override
    public String getName() {
        return className;
    }

    public ClassFile getClassFile() {
        return classFile;
    }
}
