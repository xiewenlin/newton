package com.xiewenlin.jvm;

import com.xiewenlin.jvm.runtime.RunEnvironment;
import com.xiewenlin.jvm.langx.ClassLoaderX;
import com.xiewenlin.jvm.langx.ClassX;
import com.xiewenlin.jvm.langx.MethodX;


import java.nio.file.Path;
import java.util.Hashtable;

/**
 * @author xiewenlin@frtauto.com
 * @ClassName JavaVirtualMachine
 * @Description java虚拟机类
 * @Date 2019/3/20 16:06
 * @Version V1.0.0
 */
public class JavaVirtualMachine {

    /**
     * 初始类
     * 包含 main 方法的类名
     */
    private String initialClass;
    /**
     * 类加载器
     */
    private ClassLoaderX classLoader;
    /**
     * 方法区（Method Area）
     * 存储运行时类信息
     */
    private Hashtable<String, ClassX> methodArea = new Hashtable<String, ClassX>();

    public JavaVirtualMachine(Path classPath, String initialClass){
        classLoader =new DefaultClassLoaderX(classPath);
        this.initialClass = initialClass;
    }
    /**
     * 执行虚拟机
     * @param args
     * @throws Exception
     */
    public void run(String[] args) throws Exception {
        RunEnvironment env = new RunEnvironment(this);
        ClassX clazz = getClass(initialClass);
        //找到入口方法
        MethodX method = clazz.getMethod(
                "main",
                "([Ljava/langx/String;)V");
        //执行入口方法
        method.call(env, null, new Object[]{args});
    }

    public ClassX getClass(String className) throws ClassNotFoundException {
        ClassX found = methodArea.get(className);
        if(found == null){
            found = classLoader.loadClass(className);
            methodArea.put(className, found);
        }
        return found;
    }

}
