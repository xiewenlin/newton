package com.xiewenlin.jvm.runtime;

import com.sun.tools.classfile.ConstantPool;
import com.xiewenlin.jvm.langx.ClassX;
import com.xiewenlin.jvm.langx.MethodX;
import com.xiewenlin.jvm.user.UserInvoker;

/**
 * @author xiewenlin@frtauto.com
 * @ClassName JavaVirtualMachineStack
 * @Description 虚拟机栈,每个虚拟机线程持有一个独立的栈
 * @Date 2019/3/20 18:21
 * @Version V1.0.0
 */
public class JavaVirtualMachineStack {
    /**
     * 指定栈的初始化槽位大小为1024
     */
    private SlotsStack<StackFrame> frames = new SlotsStack<>(1024);
    private boolean running = false;

    public StackFrame newFrame(ClassX clazz, MethodX method) {
        StackFrame frame = new StackFrame(clazz, method, null, null, 0, 0);
        frames.push(frame, 1);
        return frame;
    }

    public StackFrame newFrame(ClassX clazz, MethodX method, ConstantPool constantPool,
                               UserInvoker[] byteCodes,
                               int variables,
                               int stackSize) {
        StackFrame frame = new StackFrame(clazz, method, constantPool, byteCodes, variables, stackSize);
        frames.push(frame, 1);
        return frame;
    }
    public StackFrame currentFrame() {
        return frames.pick();
    }
    public StackFrame popFrame(){
        return frames.pop();
    }
    public boolean isRunning() {
        return running;
    }
    public void setRunning(boolean running) {
        this.running = running;
    }
}
