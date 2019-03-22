package com.xiewenlin.jvm.user;

import com.xiewenlin.jvm.runtime.RunEnvironment;
import com.xiewenlin.jvm.runtime.StackFrame;

/**
 * @author xiewenlin@frtauto.com
 * @ClassName UserInvoker
 * @Description
 * @Date 2019/3/22 9:42
 * @Version V1.0.0
 */
public interface UserInvoker {
    /**
     * 字节码
     * @param env
     * @param frame
     * @throws Exception
     */
    void invoke(RunEnvironment env, StackFrame frame) throws Exception ;
}
