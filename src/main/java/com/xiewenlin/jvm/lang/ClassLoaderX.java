package com.xiewenlin.jvm.lang;

/**
 * @author xiewenlin@frtauto.com
 * @ClassName ClassLoaderX
 * @Description
 * @Date 2019/3/20 17:03
 * @Version V1.0.0
 */
public interface ClassLoaderX {
    ClassX loadClass(String className) throws ClassNotFoundException;
}
