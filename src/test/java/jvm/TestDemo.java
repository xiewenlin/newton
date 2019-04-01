package jvm;

/**
 * @author xiewenlin@frtauto.com
 * @ClassName TestDemo
 * @Description
 * @Date 2019/3/29 14:53
 * @Version V1.0.0
 */
public class TestDemo implements Cloneable{
    public static void main(String[] args) {
        //Object objectColone=super.clone();
        ThreadLocal threadLocal=new ThreadLocal();
        int intValue1=1,intValue2=1;
        System.out.println((intValue1++)+","+(++intValue2));
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
