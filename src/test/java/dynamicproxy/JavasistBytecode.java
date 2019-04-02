package dynamicproxy;

import javassist.*;

import javax.security.auth.Subject;
import java.lang.reflect.Field;

/**
 * @author xiewenlin@frtauto.com
 * @ClassName JavasistBytecode
 * @Description
 * @Date 2019/4/1 11:39
 * @Version V1.0.0
 */
public class JavasistBytecode {
    public static Subject createJavasistBytecodeDynamicProxy(final Subject delegate) throws Exception {
        ClassPool pool = ClassPool.getDefault();
        CtClass proxyClass = pool.makeClass(Subject.class.getName() + "JavasistProxy");
        proxyClass.addInterface(pool.get(Subject.class.getName()));
        proxyClass.addConstructor(CtNewConstructor.defaultConstructor(proxyClass));
        proxyClass.addField(CtField.make("private " + Subject.class.getName() + " delegate ;", proxyClass));
        proxyClass.addMethod(CtNewMethod.make(
                "public void request() { delegate.request();}", proxyClass));
        Class<?> clazz = proxyClass.toClass();
        Subject bytecodeProxy = (Subject) clazz.newInstance();
        Field field = bytecodeProxy.getClass().getDeclaredField("delegate");
        field.setAccessible(true);
        field.set(bytecodeProxy,delegate);
        return bytecodeProxy;
    }

    public static void main(String args[]) throws Exception {
        /*Subject delegate = new RealSubject();
        Subject bytecodeProxy = createJavasistBytecodeDynamicProxy(delegate);
        bytecodeProxy.request();*/
    }
}
