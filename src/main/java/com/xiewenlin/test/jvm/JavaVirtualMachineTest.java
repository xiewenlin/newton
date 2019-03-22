package com.xiewenlin.test.jvm;

import com.xiewenlin.jvm.JavaVirtualMachine;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * @author xiewenlin@frtauto.com
 * @ClassName JavaVirtualMachineTest
 * @Description
 * @Date 2019/3/22 16:53
 * @Version V1.0.0
 */
public class JavaVirtualMachineTest {
    public static void main(String[] args){
        args=new String[2];
        args[0]="test";
        try {
            JavaVirtualMachine jvm = new JavaVirtualMachine(Paths.get("C:/WorkSpace/idea/gitversion/newton/target/classes/com/xiewenlin/test/jvm/"), "HelloJavaVirtualMachine");
            jvm.run(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //JavaVirtualMachine vm = new JavaVirtualMachine(Paths.get("C:\\WorkSpace\\idea\\gitversion\\newton\\target\\classes\\com\\xiewenlin\\test\\jvm\\"), "HelloJavaVirtualMachine");
    //vm.run(String[] args);
    /*public static void main(String[] args){
        args=new String[2];
        args[0]="";
        args[1]="HelloJavaVirtualMachine.class";
        if(args.length == 0){
            System.out.println("Usage: <classpath> <JavaVirtualMachineTest class> [args...]");
            return;
        }
        JavaVirtualMachine vm = new JavaVirtualMachine(Paths.get(args[0]), args[1]);
        try {
            *//**
             * Arrays.copyOfRange(T[ ] original,int from,int to)
             * 将一个原始的数组original，从小标from开始复制，复制到小标to，生成一个新的数组。
             * 注意这里包括下标from，不包括上标to。
             *//*
            args = Arrays.copyOfRange(args, 2, args.length);
            vm.run(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}
