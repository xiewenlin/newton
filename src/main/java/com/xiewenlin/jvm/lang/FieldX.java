package com.xiewenlin.jvm.lang;

import com.xiewenlin.jvm.core.RunEnvironment;

/**
 * @author xiewenlin@frtauto.com
 * @ClassName FieldX
 * @Description
 * @Date 2019/3/20 20:14
 * @Version V1.0.0
 */
public interface FieldX {
    public void set(RunEnvironment env, Object object, Object value) throws IllegalAccessException;
    public Object get(RunEnvironment env, Object object)throws IllegalAccessException;
}
