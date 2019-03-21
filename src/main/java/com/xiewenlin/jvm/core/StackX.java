package com.xiewenlin.jvm.core;

import com.sun.org.apache.bcel.internal.classfile.ConstantPool;

/**
 * @author xiewenlin@frtauto.com
 * @ClassName StackX
 * @Description 虚拟机栈,个虚拟机线程持有一个独立的栈
 * @Date 2019/3/20 18:21
 * @Version V1.0.0
 */
public class StackX {
    private SlotsStack<StackFrame> frames = new SlotsStack<>(1024);
    private boolean running = false;

    public StackFrame newFrame(JvmClass clazz, JvmMethod method) {
        StackFrame frame = new StackFrame(clazz, method, null, null, 0, 0);
        frames.push(frame, 1);
        return frame;
    }

    public StackFrame newFrame(JvmClass clazz, JvmMethod method, ConstantPool constantPool,
                               OpcodeInvoker[] opcodes,
                               int variables,
                               int stackSize) {
        StackFrame frame = new StackFrame(clazz, method, constantPool, opcodes, variables, stackSize);
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
