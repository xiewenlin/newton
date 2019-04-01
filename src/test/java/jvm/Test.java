package jvm;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xiewenlin@frtauto.com
 * @ClassName Test
 * @Description 全小写的 class 是关键字，用来定义类，而首字母大写的 Class ，它是所有 class 的类。
 * @Date 2019/3/27 11:55
 * @Version V1.0.0
 */
public class Test {
    public static void main(String[] args) throws Exception{
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        List<Integer> list2 = list1;
        List<Integer> list3 = new ArrayList<Integer>(list1);
        list1.add(2);
        list2.add(3);
        list3.add(33);
        System.out.println(list1);
        System.out.println(list2);
        System.out.println(list3);
      /*  //实例化类Speaker
        SpeakerInterface speaker=new Speaker("你好");
        //通过类Speaker小写的class属性来获取大写的Class类对象
        Class<Speaker> speakerClass=Speaker.class;
        //通过类Speaker大写的Class对象获取私有成员属性Field
        Field hello=speakerClass.getDeclaredField("hello");
        //设置私有属性可以访问和修改
        hello.setAccessible(true);
        //修改类Speaker私有属性hello的值
        hello.set(speaker,"你不好");
        speaker.helloTo("Java虚拟机");*/
    }
}
