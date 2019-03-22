package com.xiewenlin.jvm.nativex;

import com.xiewenlin.jvm.langx.ClassX;
import com.xiewenlin.jvm.langx.MethodX;
import com.xiewenlin.jvm.runtime.RunEnvironment;
import com.xiewenlin.jvm.runtime.StackFrame;

import java.lang.reflect.Method;

/**
 * @author xiewenlin@frtauto.com
 * @ClassName NativeMethodX
 * @Description 创建native的方法 NativeMethodX
 * @Date 2019/3/21 16:20
 * @Version V1.0.0
 */
public class NativeMethodX implements MethodX {
    private final ClassX clazz;
    private final Method method;
    private final String name;

    public NativeMethodX(ClassX clazz, Method method){
        this.clazz = clazz;
        this.method = method;
        this.name = method.getName();
    }
    @Override
    public void call(RunEnvironment env, Object thiz, Object... args) throws Exception {
        assert thiz instanceof NativeObjectX;
        StackFrame frame = env.getStack().newFrame(clazz, this);
        Object object = ((NativeObjectX) thiz).getNativeObject();
        Object res = method.invoke(object, NativeObjectX.multiUnwrap(args));
        // 非基础类型，需要用NativeObjectX包装
        res = NativeObjectX.wrap(res, method.getReturnType(), clazz.getClassLoader());
        //将返回值推入调用者的操作数栈
        frame.setReturn(res, method.getReturnType().getName());
    }

    @Override
    public int getParameterCount() {
        return method.getParameterCount();
    }

    @Override
    public String getName() {
        return name;
    }

    public Method getNativeMethod() {
        return method;
    }
}
