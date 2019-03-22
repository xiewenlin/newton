package com.xiewenlin.jvm.runtime;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * @author xiewenlin@frtauto.com
 * @ClassName SlotsStack
 * @Description 可指定槽位大小的栈，是用于存储操作数的栈，操作数栈用来准备字节码调用时的参数
 *              并接收其返回结果，操作数栈的长度由编译期决定。
 * @Date 2019/3/22 9:24
 * @Version V1.0.0
 */
public class SlotsStack<T> {
    private T[] buffer;

    private int end = 0;

    public SlotsStack(int size){
        buffer = (T[]) new Object[size];
    }

    public void push(T entity) throws IllegalArgumentException{
        this.push(entity, 1);
    }

    /**
     * 入栈
     * @param entity
     * @param size
     * @throws IllegalArgumentException
     */
    public void push(T entity, int size) throws IllegalArgumentException{
        if(size <=0 || end+size>buffer.length){
            throw new IllegalArgumentException("invalid entity size "+size);
        }
        buffer[end] = entity;
        for(int i=1; i<size; i++){
            buffer[end+i] = null;
        }
        end += size;

    }

    /**
     * 出栈
     * @return
     * @throws NoSuchElementException
     */
    public T pop() throws NoSuchElementException {
        while (end >0){
            end--;
            T entity = buffer[end];
            if(entity != null){
                buffer[end] = null;
                return entity;
            }

        }
        throw new NoSuchElementException();
    }

    /**
     * 按正常出栈的顺序 pop 出全部元素
     * @return
     */
    public ArrayList<T> multiPop(int count) {
        if(count<=0) {
            throw new IllegalArgumentException("count should not <= 0");
        }
        ArrayList<T> items = new ArrayList<>();
        while (end >0 && count>0){
            end--;
            T entity = buffer[end];
            if(entity != null){
                buffer[end] = null;
                items.add(entity);
                count --;
            }
        }
        return items;
    }

    /**
     * 取出最后一个元素，但不出栈
     * @return
     */
    public T pick(){
        int end = this.end;
        while (end > 0)
        {
            end--;
            T entity = buffer[end];
            if(entity != null){
                return entity;
            }
        }
        return null;
    }

    public int getEndSize() {
        int end = this.end;
        while (end > 0)
        {
            end--;
            if(buffer[end] != null){
                return this.end-end;
            }
        }
        return 0;
    }
}
