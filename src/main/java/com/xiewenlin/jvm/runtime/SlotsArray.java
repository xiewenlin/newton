package com.xiewenlin.jvm.runtime;

import java.util.NoSuchElementException;

/**
 * @author xiewenlin@frtauto.com
 * @ClassName SlotsArray
 * @Description 可指定槽位大小的数组
 * 相关知识点：在Java SE 1.5之前，没有泛型的情况的下，通过对类型Object的引用来实现参数的“任意化”，“任意化”
 *      带来的缺点是要做显式的强制类型转换，而这种转换是要求开发者对实际参数类型可以预知的情况下进行的。
 *      对于强制类型转换错误的情况，编译器可能不提示错误，在运行的时候才出现异常，这是一个安全隐患。
 *
 * 泛型的好处是在编译的时候检查类型安全，并且所有的强制转换都是自动和隐式的，以提高代码的重用率。
 *
 * 泛型的规则限制:
 *   1.泛型的类型参数只能是类类型（包括自定义类），不能是简单类型。
 *   2.同一种泛型可以对应多个版本（因为参数类型是不确定的），不同版本的泛型类实例是不兼容的。
 *   3.泛型的类型参数可以有多个,使用extends语句，Java泛型简单的用 extends 统一的表示了原有的
 *   extends 和 implements 的概念，但仍要遵循应用的体系，Java 只能继承一个类，但可以实现多个接口，
 *   所以你的某个类型需要用 extends 限定，且有多种类型的时候，只能存在一个是类，并且类写在第一位，
 *   接口列在后面，也就是：Demo<T extends ClassType & interface1 & interface2 & interface3>
 *   4.泛型的参数类型还可以是通配符类型,用“？”代表未知类型,
 *   例如Class<?> classType = Class.forName(“java.lang.String”);
 *
 * 泛型的好处：
 *      开始版本
 *      public void write(Integer i, Integer[] ia);
 *      public void write(Double  d, Double[] da);
 *      泛型版本
 *      public <T> void write(T t, T[] ta);
 *
 * JDK里的泛型潜规则：jdk为了便于理解，用K表示键，V表示值，T表示type类型，E表示enum枚举，
 * 其实这四个都只是符号，都是表示泛型名称。换成其他字母都没关系，但是都要在之前声明。
 * @Date 2019/3/21 16:52
 * @Version V1.0.0
 */
public class SlotsArray<T> {
    private T[] buffer;

    public SlotsArray(int size){
        buffer = (T[]) new Object[size];
    }

    /**
     * 定义可指定槽位大小的数组的set方法
     * @param pos   当前数组下标起始位置
     * @param entity 存入数组中的入参可以是任意类，这里的T相当于Object
     * @param size 指定数组的大小
     * @throws IllegalArgumentException
     */
    public void set(int pos, T entity, int size) throws IllegalArgumentException{
        if(pos <0 || pos+size > buffer.length){
            throw new IllegalArgumentException("invalid entity size "+size);
        }
        buffer[pos] = entity;
        for(int i=1; i<size; i++){
            buffer[pos+i] = null;
        }
    }

    /**
     * 获取指定数组的位置的对象
     * @param pos 获取指定数组的位置
     * @return Object
     * @throws NoSuchElementException
     */
    public Object get(int pos) throws NoSuchElementException {
        if(pos<0 || pos >= buffer.length){
            throw new NoSuchElementException();
        }
        return buffer[pos];
    }

    public int size(){
        return buffer.length;
    }
}
