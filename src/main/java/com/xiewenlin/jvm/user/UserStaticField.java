package com.xiewenlin.jvm.user;

import com.sun.tools.classfile.ConstantPoolException;
import com.sun.tools.classfile.Descriptor;
import com.sun.tools.classfile.Field;
import com.xiewenlin.jvm.langx.FieldX;
import com.xiewenlin.jvm.runtime.RunEnvironment;

/**
 * @author xiewenlin@frtauto.com
 * @ClassName UserStaticField
 * @Description 静态成员变量
 * @Date 2019/3/21 16:25
 * @Version V1.0.0
 */
public class UserStaticField implements FieldX {
    private final Field field;
    private final UserClass clazz;
    private Object value;
    private final String type;
    public UserStaticField(UserClass clazz, Field field) throws Descriptor.InvalidDescriptor, ConstantPoolException {
        this.field = field;
        this.clazz = clazz;
        type = field.descriptor.getFieldType(this.clazz.getClassFile().constant_pool);
        //初始化为默认值
        switch (type){
            case "byte":
                value = (byte)0;
                break;
            case "char":
                value = '\u0000';
                break;
            case "double":
                value = 0.;
                break;
            case "float":
                value = 0.f;
                break;
            case "int":
                value = 0;
                break;
            case "long":
                value = 0L;
                break;
            case "short":
                value = (short)0;
                break;
            case "value":
                value = false;
                break;
            default:
                value = null;
                break;
        }
    }

    @Override
    public void set(RunEnvironment env, Object object, Object value) throws IllegalAccessException {
        this.value = value;
    }

    @Override
    public Object get(RunEnvironment env, Object object) throws IllegalAccessException {
        return value;
    }
}
