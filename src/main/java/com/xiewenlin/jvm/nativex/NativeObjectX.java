package com.xiewenlin.jvm.nativex;

import com.xiewenlin.jvm.langx.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import java.util.Arrays;

/**
 * @author xiewenlin@frtauto.com
 * @ClassName NativeObjectX
 * @Description 创建Java虚拟机提供的原生对象：NativeObjectX类
 * @Date 2019/3/21 16:21
 * @Version V1.0.0
 */
public class NativeObjectX implements ObjectX {
    private Object object;
    private final NativeClassX clazz;

    public NativeObjectX(NativeClassX clazz){
        this.clazz = clazz;
    }

    public Object getNativeObject() {
        return object;
    }

    public void setNativeObject(Object object) {
        this.object = object;
    }

    @Override
    public ObjectX getSuper() {
        throw new NotImplementedException();
    }

    @Override
    public ClassX getClazz() {
        return clazz;
    }

    static public Object wrap(Object object, Class clazz, ClassLoaderX classLoader){
        if(object == null){
            return null;
        }
        // 非基础类型，需要用NativeObjectX包装
        String primitiveTypes[] = {
                byte.class.getName(),
                short.class.getName(),
                int.class.getName(),
                long.class.getName(),
                char.class.getName(),
                float.class.getName(),
                double.class.getName(),
                boolean.class.getName()
        };
        Arrays.sort(primitiveTypes);
        if(Arrays.binarySearch(primitiveTypes, clazz.getName()) <0 ){
            NativeObjectX wrap = new NativeObjectX(new NativeClassX(classLoader, clazz));
            wrap.setNativeObject(object);
            return wrap;
        }
        return object;
    }

    static public Object unwrap(Object object){
        if(object instanceof NativeObjectX){
            return ((NativeObjectX) object).getNativeObject();
        }
        return object;
    }
    static public Object[] multiUnwrap(Object[] objects){
        Object[] res = new Object[objects.length];
        for (int i=0; i<objects.length; i++) {
            res[i] = unwrap(objects[i]);
        }
        return res;
    }
}
