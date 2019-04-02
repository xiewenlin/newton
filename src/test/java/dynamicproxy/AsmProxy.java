package dynamicproxy;

import com.esotericsoftware.asm.ClassWriter;
import com.esotericsoftware.asm.FieldVisitor;
import com.esotericsoftware.asm.MethodVisitor;
import com.esotericsoftware.asm.Opcodes;

import javax.security.auth.Subject;
import java.lang.reflect.Field;

/**
 * @author xiewenlin@frtauto.com
 * @ClassName AsmProxy
 * @Description
 * @Date 2019/4/1 11:39
 * @Version V1.0.0
 */
public class AsmProxy {
    public static Subject createAsmByteCodeDynamicProxy(Subject delegate) throws Exception {
        //ClassWriter classWriter = new ClassWriter(true);
        ClassWriter classWriter = new ClassWriter(1);
        String className = Subject.class.getName() + "AsmProxy";
        String classPath = className.replace('.', '/');
        String interfacePath = Subject.class.getName().replace('.', '/');

        classWriter.visit(Opcodes.V1_5, Opcodes.ACC_PUBLIC, classPath, null,
                "java/lang/Object", new String[] {interfacePath});
        MethodVisitor initVistor = (MethodVisitor) classWriter.visitMethod(Opcodes.ACC_PUBLIC,
                "<init>", "()V", null, null);
        initVistor.visitCode();
        initVistor.visitVarInsn(Opcodes.ALOAD, 0);
        initVistor.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V");
        initVistor.visitInsn(Opcodes.RETURN);
        initVistor.visitMaxs(0, 0);
        initVistor.visitEnd();

        FieldVisitor fieldVisitor = classWriter.visitField(Opcodes.ACC_PRIVATE, "delegate" ,
                "L" + interfacePath + ";" , null, null);
        fieldVisitor.visitEnd();

        MethodVisitor methodVisitor = classWriter.visitMethod(Opcodes.ACC_PUBLIC, "request", "()V", null, null);
        methodVisitor.visitCode();
        methodVisitor.visitVarInsn(Opcodes.ALOAD, 0);
        methodVisitor.visitFieldInsn(Opcodes.GETFIELD, classPath, "delegate", "L" + interfacePath + ";");
        methodVisitor.visitMethodInsn(Opcodes.INVOKEINTERFACE, interfacePath, "request", "()V");
        methodVisitor.visitInsn(Opcodes.RETURN);
        methodVisitor.visitMaxs(0, 0);
        methodVisitor.visitEnd();

        classWriter.visitEnd();
        byte[] code = classWriter.toByteArray();
        Subject asmProxy = (Subject) new ByteArrayClassLoader().getClass(className, code).newInstance();
        Field field  = asmProxy.getClass().getDeclaredField("delegate");
        field.setAccessible(true);
        field.set(asmProxy, delegate);
        return asmProxy;

    }
    private static class ByteArrayClassLoader extends ClassLoader {

        ByteArrayClassLoader() {
            super(ByteArrayClassLoader.getSystemClassLoader());
        }

        public synchronized Class<?> getClass(String name, byte[] code) {
            if(name == null)
                throw new IllegalArgumentException("name == null");
            return defineClass(name, code, 0, code.length);
        }
    }

    public static void main(String args[]) throws Exception{
       /* Subject real = new RealSubject();
        Subject asmProxy  =createAsmByteCodeDynamicProxy(real);
        asmProxy.request();*/
    }
}
