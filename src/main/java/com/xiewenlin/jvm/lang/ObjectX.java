package com.xiewenlin.jvm.lang;

/**
 * @author xiewenlin@frtauto.com
 * @ClassName ObjectX 定义根对象
 * @Description
 * @Date 2019/3/20 17:25
 * @Version V1.0.0
 */
public interface ObjectX {

    /**
     * 获取父类实例
     * @return 返回根对象
     */
    ObjectX getSuper();

    /**
     * 获取实例的类
     * @return 返回java虚拟机内部的实例的类Class
     */
    ClassX getClazz();
}
