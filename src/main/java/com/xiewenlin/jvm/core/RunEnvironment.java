package com.xiewenlin.jvm.core;

import com.xiewenlin.jvm.JavaVirtualMachine;

/**
 * @author xiewenlin@frtauto.com
 * @ClassName RunEnvironment
 * @Description java运行环境线程上下文类
 * @Date 2019/3/20 18:17
 * @Version V1.0.0
 */
public class RunEnvironment {
    /**
     * 虚拟机栈
     */
    private StackX stack = new StackX();
    /**
     * 当前虚拟机
     */
    private JavaVirtualMachine vm;

    public RunEnvironment(JavaVirtualMachine vm){
        this.vm = vm;
    }

    public StackX getStack() {
        return stack;
    }

    public JavaVirtualMachine getVm() {
        return vm;
    }
}
