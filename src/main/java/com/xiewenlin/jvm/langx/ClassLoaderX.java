package com.xiewenlin.jvm.langx;

/**
 * @author xiewenlin@frtauto.com
 * @ClassName ClassLoaderX
 * @Description
 * @Date 2019/3/20 17:03
 * @Version V1.0.0
 */
public interface ClassLoaderX {
    /**
     * 定义类加载器方法
     * @param className
     * @return
     * @throws ClassNotFoundException
     */
    ClassX loadClass(String className) throws ClassNotFoundException;
}
