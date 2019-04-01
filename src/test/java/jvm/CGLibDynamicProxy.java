package jvm;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author xiewenlin@frtauto.com
 * @ClassName CGLibDynamicProxy
 * @Description
 * @Date 2019/3/27 9:33
 * @Version V1.0.0
 */
public class CGLibDynamicProxy implements MethodInterceptor {
    /**
     * 使用cglib动态代理通过反射调用方法
     * @param o
     * @param method
     * @param objects
     * @param methodProxy
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method,Object[] objects,
                            MethodProxy methodProxy) throws Throwable {
        long beginTime=System.currentTimeMillis();
        Object result=methodProxy.invokeSuper(o,objects);
        long endTime=System.currentTimeMillis();
        System.out.println("方法"+method.getName()+"的执行时间是："
                +(endTime-beginTime));
        return result;
    }
    public <T> T getProxy(Class<T> cls){
        return (T) Enhancer.create(cls,this);
    }
    public static void main(String[] args) {
        CGLibDynamicProxy cgLibDynamicProxy=new CGLibDynamicProxy();
        StringImprove stringImprove=cgLibDynamicProxy.getProxy
                (StringImprove.class);
        stringImprove.stringBeforeImproveTest();
        stringImprove.stringAfterImproveTest();
    }
}
