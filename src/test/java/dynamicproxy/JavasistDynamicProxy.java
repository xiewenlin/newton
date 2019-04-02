package dynamicproxy;


import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;
import javassist.util.proxy.ProxyObject;

import javax.security.auth.Subject;
import java.lang.reflect.Method;

/**
 * @author xiewenlin@frtauto.com
 * @ClassName JavasistDynamicProxy
 * @Description
 * @Date 2019/4/1 11:37
 * @Version V1.0.0
 */
public class JavasistDynamicProxy {
    public static Subject createJavasistDynamicProxy(final Subject delegate)
            throws Exception {

        ProxyFactory factory = new ProxyFactory();
        //factory.setInterfaces(new Class<?>[]{com.sunchao.jdkdyproxy.Subject.class});
        Class<?> proxyClass = factory.createClass();
        Subject javasistProxy = (Subject) proxyClass.newInstance();
        //((ProxyObject)javasistProxy).setHandler(new JavasistInterceptor(delegate));
        return javasistProxy;
    }

    private static class JavasistInterceptor implements MethodHandler {
        final private Object delegate;

        JavasistInterceptor(Object delegate) {
            this.delegate = delegate;
        }

        @Override
        public Object invoke(Object arg0, Method arg1, Method arg2,
                             Object[] arg3) throws Throwable {
            //System.out.println("Aop before the real request");
            return    arg1.invoke(delegate, arg3);
            //    System.out.println("Aop after the real request");
            //    return null;
        }

    }
}
