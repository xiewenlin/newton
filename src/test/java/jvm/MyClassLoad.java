package jvm;

import java.net.URL;

/**
 * @author xiewenlin@frtauto.com
 * @ClassName MyClassLoad
 * @Description
 * @Date 2019/3/26 10:11
 * @Version V1.0.0
 */
public class MyClassLoad extends ClassLoader{
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException{
        try {
            byte[] result=getClassFromMyPath(name);
        }catch (Exception e){
            e.printStackTrace();
        }
        throw new ClassNotFoundException(name);
    }
    private byte[] getClassFromMyPath(String name){
        byte[] initByte=new byte[1024];
        //从自定义路径中加载指定类
        return initByte;
    }
    public static void main(String[] args) {
        MyClassLoad myClassLoad=new MyClassLoad();
        try {
            Class<?> clazz=Class.forName(StringImprove.class.getName(),true,myClassLoad);
            Object object=clazz.newInstance();
            System.out.println(object.getClass().getClassLoader());
            System.out.println(object.getClass().getClassLoader().getParent());
            System.out.println(object.getClass().getClassLoader().getParent().getParent());
            URL[] bootStrapUrls=sun.misc.Launcher.getBootstrapClassPath().getURLs();
            for(URL bootStrapUrl : bootStrapUrls){
                System.out.println(bootStrapUrl);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

