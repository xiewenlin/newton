package com.xiewenlin.jvm.nativex;

import com.xiewenlin.jvm.langx.ClassX;
import com.xiewenlin.jvm.langx.MethodX;
import com.xiewenlin.jvm.runtime.RunEnvironment;
import com.xiewenlin.jvm.runtime.StackFrame;

import java.lang.reflect.Constructor;

/**
 * @author xiewenlin@frtauto.com
 * @ClassName NativeConstructorX
 * @Description native类的构造器方法
 * @Date 2019/3/21 16:21
 * @Version V1.0.0
 */
public class NativeConstructorX implements MethodX {
    private final Constructor constructor;
    private final ClassX clazz;

    public NativeConstructorX(ClassX clazz, Constructor constructor){
        this.clazz = clazz;
        this.constructor = constructor;
    }
    @Override
    public void call(RunEnvironment env, Object thiz, Object... args) throws Exception {
        assert (thiz instanceof NativeObjectX);

        StackFrame frame = env.getStack().newFrame(clazz, this);
        Object object = constructor.newInstance(NativeObjectX.multiUnwrap(args));
        ((NativeObjectX) thiz).setNativeObject(object);
        //将返回值推入调用者的操作数栈
        frame.setReturn(null, "void");
    }

    @Override
    public int getParameterCount() {
        return constructor.getParameterCount();
    }

    @Override
    public String getName() {
        return "<init>";
    }
}
