package com.xiewenlin.jvm;

import com.xiewenlin.jvm.langx.ClassX;
import com.xiewenlin.jvm.langx.ClassLoaderX;
import com.xiewenlin.jvm.nativex.NativeClassX;
import com.xiewenlin.jvm.user.UserClass;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author xiewenlin@frtauto.com
 * @ClassName DefaultClassLoaderX
 * @Description
 * @Date 2019/3/20 17:01
 * @Version V1.0.0
 */
public class DefaultClassLoaderX implements ClassLoaderX {

    /**
     * 类搜索路径
     */
    private Path classPath;

    public DefaultClassLoaderX(Path classPath) {
        this.classPath = classPath;
    }

    @Override
    public ClassX loadClass(String className) throws ClassNotFoundException {
        //获取编译后的.class文件名称
        String fileName = classPath + "/"+className.replace(".", "/")+".class";
        //获取文件路径
        Path path = Paths.get(fileName);
        //如果文件存在，加载文件字节码
        //否则尝试通过虚拟机宿主加载指定类，并将加载后的类当做 native 类
        if(Files.exists(path)){
            return UserClass.read(this, path);
        }else{
            return new NativeClassX(this, Class.forName(className.replace("/",".")));
        }
    }
}
