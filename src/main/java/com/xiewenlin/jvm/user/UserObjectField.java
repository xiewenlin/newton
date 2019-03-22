package com.xiewenlin.jvm.user;

import com.sun.tools.classfile.ConstantPoolException;
import com.sun.tools.classfile.Field;
import com.xiewenlin.jvm.langx.FieldX;
import com.xiewenlin.jvm.runtime.RunEnvironment;

/**
 * @author xiewenlin@frtauto.com
 * @ClassName UserObjectField
 * @Description 成员变量
 * @Date 2019/3/21 16:25
 * @Version V1.0.0
 */
public class UserObjectField implements FieldX {
    private final UserClass clazz;
    private final Field field;
    private final String fieldName;

    public UserObjectField(UserClass clazz, Field field) throws ConstantPoolException {
        this.clazz = clazz;
        this.field = field;
        fieldName = field.getName(clazz.getClassFile().constant_pool);
    }
    @Override
    public void set(RunEnvironment env, Object object, Object value) throws IllegalAccessException {
        assert object instanceof UserObject;
        ((UserObject) object).setField(fieldName, value);
    }

    @Override
    public Object get(RunEnvironment env, Object object) throws IllegalAccessException {
        assert object instanceof UserObject;
        return ((UserObject) object).getField(fieldName);
    }
}
