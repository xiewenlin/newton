package com.xiewenlin.jvm.nativex;

import com.xiewenlin.jvm.langx.FieldX;
import com.xiewenlin.jvm.runtime.RunEnvironment;

import java.lang.reflect.Field;

/**
 * @author xiewenlin@frtauto.com
 * @ClassName NativeFieldX
 * @Description 创建native的属性 NativeFieldX
 * @Date 2019/3/21 16:21
 * @Version V1.0.0
 */
public class NativeFieldX implements FieldX {
    NativeClassX clazz;
    Field filed;
    public NativeFieldX(NativeClassX nativeClass, Field field){
        this.clazz = nativeClass;
        this.filed = field;
    }
    @Override
    public Object get(RunEnvironment env, Object thiz) throws IllegalAccessException {
        // 非基础类型，需要用NativeObjectX包装
        return NativeObjectX.wrap(filed.get(thiz), filed.getType(), clazz.getClassLoader());
    }

    @Override
    public void set(RunEnvironment env, Object thiz,  Object value) throws IllegalAccessException {
        // 去掉NativeObjectX包装
        filed.set(thiz, NativeObjectX.unwrap(value));
    }
}
