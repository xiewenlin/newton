package com.xiewenlin.jvm.runtime;

import com.sun.tools.classfile.ConstantPool;
import com.xiewenlin.jvm.langx.ClassX;
import com.xiewenlin.jvm.langx.MethodX;
import com.xiewenlin.jvm.user.UserInvoker;

/**
 * @author xiewenlin@frtauto.com
 * @ClassName NativeClassX
 * @Description 栈帧:对应 JVM 规范中的栈帧的概念，用于表示一次方法调用的上下文
 * @Date 2019/3/21 16:06
 * @Version V1.0.0
 */
public class StackFrame {

    /**
     * 局部变量表(Local Variables）
     * 用于存储方法的局部变量
     */
    private SlotsArray<Object> localVariables;

    /**
     * 操作数栈(Operand Stack）
     * 用于存储操作指令的输入输出
     */
    private SlotsStack<Object> operandStack;

    /**
     * 字节码
     */
    private UserInvoker[] byteCodes;

    /**
     * 程序计数器
     */
    private int pc=0;
    /**
     * 常量池（Constant Pool）
     */
    private ConstantPool constantPool;
    private Object returnVal;
    private String returnType;
    private boolean isReturned = false;
    private final ClassX clazz;
    private final MethodX method;

    StackFrame(ClassX clazz, MethodX method, ConstantPool constantPool,
               UserInvoker[] byteCodes,
               int variables,
               int stackSize) {
        this.constantPool = constantPool;
        this.byteCodes = byteCodes;
        this.operandStack = new SlotsStack<>(stackSize);
        this.localVariables = new SlotsArray<>(variables);
        this.clazz = clazz;
        this.method = method;
    }

    public SlotsArray<Object> getLocalVariables() {
        return localVariables;
    }

    public SlotsStack<Object> getOperandStack() {
        return operandStack;
    }

    public ConstantPool getConstantPool() {
        return constantPool;
    }

    public void setPC(int pc) {
        this.pc = pc;
    }

    public void setReturn(Object returnVal, String returnType) {
        this.isReturned = true;
        this.returnVal = returnVal;
        this.returnType = returnType;
    }

    public Object getReturn() {
        return returnVal;
    }
    public String getReturnType() {
        return returnType;
    }

    public boolean isReturned() {
        return isReturned;
    }

    public int getPC() {
        return pc;
    }
    public int increasePC(){
        return pc++;
    }
    public UserInvoker[] getOpcodes() {
        return byteCodes;
    }

    public ClassX getCurrentClass() {
        return clazz;
    }

    public MethodX getCurrentMethod() {
        return method;
    }
}
