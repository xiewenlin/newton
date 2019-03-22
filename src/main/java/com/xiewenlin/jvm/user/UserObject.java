package com.xiewenlin.jvm.user;

import com.xiewenlin.jvm.langx.ClassX;
import com.xiewenlin.jvm.langx.ObjectX;
import com.xiewenlin.jvm.runtime.RunEnvironment;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xiewenlin@frtauto.com
 * @ClassName UserObject
 * @Description UserObjectX表示用户定义的对象，通过字节码执行
 * @Date 2019/3/21 16:25
 * @Version V1.0.0
 */
public class UserObject implements ObjectX {
    private final UserClass clazz;
    private final Map<String, Object> fields = new HashMap<>();
    /**
     * 父类的实例，即通过 super 访问到的实例
     */
    private final ObjectX superObject;
    public UserObject(RunEnvironment env, UserClass clazz) throws IllegalAccessException, InstantiationException {
        this.clazz = clazz;
        ClassX superClass = null;
        try {
            superClass = clazz.getSuperClass();
        } catch (ClassNotFoundException e) {
            throw new InstantiationException(e.getMessage());
        }
        superObject = superClass.newInstance(env);
        //初始化成员变量
        for (Field field : superClass.getClass().getFields()) {
            Object value;
            //初始化为默认值
            switch (field.getType().getName()){
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
                case "boolean":
                    value = false;
                    break;
                default:
                    value = null;
                    break;
            }
            fields.put(field.getName(), value);
        }
    }
    public void setField(String name, Object value) {
        fields.put(name, value);
    }

    public Object getField(String name) {
        return fields.get(name);
    }

    @Override
    public ObjectX getSuper() {
        return superObject;
    }

    @Override
    public ClassX getClazz() {
        return clazz;
    }
}
