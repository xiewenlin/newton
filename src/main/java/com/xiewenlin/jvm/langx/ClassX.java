package com.xiewenlin.jvm.langx;

import com.xiewenlin.jvm.runtime.RunEnvironment;

/**
 * @author xiewenlin@frtauto.com
 * @ClassName ClassX java虚拟机内部的Class表示
 * @Description
 * @Date 2019/3/20 17:05
 * @Version V1.0.0
 */
public interface ClassX {
    /**
     * 分配实例的内存空间，但不执行实例的构造函数
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public ObjectX newInstance(RunEnvironment env) throws InstantiationException, IllegalAccessException;
    /**
     * 获取方法
     * @param name 方法名，如`main`
     * @param desc 方法类型描述，如`([Ljava/object/String;)V`
     * @return
     * @throws NoSuchMethodException
     */
    public MethodX getMethod(String name, String desc) throws NoSuchMethodException;

    /**
     * 获取方法
     * @param name 方法名，如`main`
     * @param desc 方法类型描述，如`([Ljava/object/String;)V`
     */
    public boolean hasMethod(String name, String desc);

    /**
     * 获取属性
     * @param name 属性名
     * @return
     * @throws NoSuchFieldException
     */
    public FieldX getField(String name) throws NoSuchFieldException, IllegalAccessException;

    /**
     * 获取当前类的 class loader
     * @return
     */
    public ClassLoaderX getClassLoader();

    /**
     * 返回父类
     */
    public ClassX getSuperClass() throws ClassNotFoundException;

    /**
     * 返回类名
     */
    public String getName();
}
