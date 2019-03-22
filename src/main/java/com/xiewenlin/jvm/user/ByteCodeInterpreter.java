package com.xiewenlin.jvm.user;

import com.sun.org.apache.bcel.internal.Constants;
import com.xiewenlin.jvm.runtime.JavaVirtualMachineStack;
import com.xiewenlin.jvm.runtime.RunEnvironment;
import com.xiewenlin.jvm.runtime.StackFrame;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author xiewenlin@frtauto.com
 * @ClassName ByteCodeInterpreter
 * @Description 字节码解析器
 * 字节码的执行过程如下：
 * 1.获取栈顶的第一个栈帧。
 * 2.获取当前栈的程序计数器(PC，其默认值为0)指向的字节码，程序计数器+1。
 * 3.执行上一步获取的字节码，推出操作数栈的元素，作为其参数，执行字节码。
 * 4.字节码返回的值（如果有），重新推入操作数栈。
 * 5.如果操作数为return等，则设置栈帧为已返回状态。
 * 6.如果操作数为invokevirtual等嵌套调用其他方法，则创建新的栈帧，并回到第一步。
 * 7.如果栈帧已设置为返回，则将返回值推入上一个栈帧的操作数栈，并推出当前栈。
 * 8.重复执行1~7，直到虚拟机栈为空。
 *
 * @Date 2019/3/22 15:25
 * @Version V1.0.0
 */
public class ByteCodeInterpreter {
    //执行字节码
    public static void run(RunEnvironment env) throws Exception {
        //只需要最外层调用执行栈上操作
        if(env.getStack().isRunning()){
            return;
        }
        StackFrame frame;
        JavaVirtualMachineStack stack = env.getStack();
        stack.setRunning(true);

        while ((frame = stack.currentFrame()) != null){
            //如果栈帧被设置为返回，则将其返回值推入上一个栈帧的操作数栈
            if(frame.isReturned()){
                StackFrame oldFrame = frame;
                stack.popFrame();
                frame = stack.currentFrame();
                if(frame != null && !"void".equals(oldFrame.getReturnType())){
                    frame.getOperandStack().push(oldFrame.getReturn());
                }
                continue;
            }
            UserInvoker[] codes = frame.getOpcodes();
            int pc = frame.increasePC();
            StringBuilder sb = new StringBuilder();
            sb.append("> ");
            sb.append(frame.getCurrentClass().getName());
            sb.append(".");
            sb.append(frame.getCurrentMethod().getName());
            sb.append("@");
            sb.append(pc);
            sb.append(":");
            sb.append(codes[pc]);
            System.out.println(sb);
            codes[pc].invoke(env, frame);
        }
    }

    public static UserInvoker[] parseCodes(byte[] codes){
        ArrayList<UserInvoker> opcodes = new ArrayList<>();
        for(int i=0; i<codes.length; i++){
            short code = (short)(0xff&codes[i]);
            final UserByteCodeRout route = UserByteCodeRout.valueOf(code);
            short noOfOperands = Constants.NO_OF_OPERANDS[code];
            byte[] operands = Arrays.copyOfRange(codes, i + 1, i + 1 + noOfOperands);
            opcodes.add(new UserInvoker() {
                @Override
                public void invoke(RunEnvironment env, StackFrame frame) throws Exception {
                    route.invoke(env, frame, operands);
                }
                @Override
                public String toString() {
                    return route.name();
                }
            });
            i += noOfOperands;
        }
        return Arrays.copyOf(opcodes.toArray(), opcodes.size(), UserInvoker[].class);
    }
}
