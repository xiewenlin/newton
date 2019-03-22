package com.xiewenlin.jvm.langx;

import com.xiewenlin.jvm.runtime.RunEnvironment;

/**
 * @author xiewenlin@frtauto.com
 * @ClassName MethodX
 * @Description
 * @Date 2019/3/20 17:28
 * @Version V1.0.0
 */
public interface MethodX {
    /**
     * 执行对象或者类方法
     * 方法被调用时，会产生一个新的栈帧，并推入当前线程的栈
     * 方法执行结束后，栈帧被退出，同时期返回值被推入上一个栈帧（当前方法的调用者）的操作数栈
     */
     void call(RunEnvironment env, Object object, Object ...args) throws Exception;

     int getParameterCount();

     String getName();
}
