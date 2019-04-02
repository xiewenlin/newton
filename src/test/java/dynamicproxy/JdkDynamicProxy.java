package dynamicproxy;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author xiewenlin@frtauto.com
 * @ClassName JdkDynamicProxy
 * @Description
 * @Date 2019/3/27 9:23
 * @Version V1.0.0
 */
public class JdkDynamicProxy implements InvocationHandler {
    private Object target;
    public JdkDynamicProxy(Object target) {
        this.target = target;
    }
    /**
     * 使用JDK动态代理通过反射调用方法
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        long beginTime=System.currentTimeMillis();
        Object result=method.invoke(target,args);
        long endTime=System.currentTimeMillis();
        System.out.println("方法"+method.getName()+"的执行时间是："
                +(endTime-beginTime));
        return result;
    }
    /**
     * 获取代理对象
     * @param <T>
     * @return
     */
    public <T> T getProxy(){
        return (T)Proxy.newProxyInstance(target.getClass().getClassLoader()
                ,target.getClass().getInterfaces(),this);
    }
    public static void main(String[] args) {
        JdkDynamicProxy jdkDynamicProxy =new JdkDynamicProxy(new Speaker
                ("你好"));
        SpeakerInterface speaker= jdkDynamicProxy.getProxy();
        speaker.helloTo("Java虚拟机");

    }
}
