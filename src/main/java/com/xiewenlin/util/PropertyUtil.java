package com.xiewenlin.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author xiewenlin@frtauto.com
 * @ClassName PropertyUtil
 * @Description 读取配置文件属性内容工具类
 * @Date 2019/3/12 15:15
 * @Version V1.0.0
 */
public class PropertyUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(PropertyUtil.class);

    /**
     * 加载属性文件，只需传递一个属性文件的名称,即可返回一个Properties对象
     * Properties 继承于 Hashtable.表示一个持久的属性集.属性列表中每个键及其对应值都是一个字符串。
     * Properties 类被许多Java类使用。例如，在获取环境变量时它就作为System.getProperties()方法的返回值。
     * 常用方法说明：
     * String getProperty(String key)：用指定的键在此属性列表中搜索属性
     * void load(InputStream streamIn) throws IOException 从输入流中读取属性列表（键和元素对）。
     * Object setProperty(String key, String value) 调用 Hashtable 的方法 put。
     * Properties对象官方文档说明：https://docs.oracle.com/javase/7/docs/api/java/util/Properties.html
     */
    public static Properties loadProps(String fileName) {
        Properties props = null;
        InputStream is = null;
        try {
            is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
            if (is == null) {
                throw new FileNotFoundException(fileName + " 文件找不到");
            }
            props = new Properties();
            props.load(is);
        } catch (IOException e) {
            LOGGER.error("加载属性配置文件失败", e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    LOGGER.error("关闭输入流失败", e);
                }
            }
        }
        return props;
    }

    /**
     * 获取字符型属性（默认值为空字符串）
     */
    public static String getString(Properties props, String key) {
        return getString(props, key, "");
    }

    /**
     * 获取字符型属性（可指定默认值）
     */
    public static String getString(Properties props, String key, String defaultValue) {
        String value = defaultValue;
        if (props.containsKey(key)) {
            value = props.getProperty(key);
        }
        return value;
    }

    /**
     * 获取数值型属性（默认值为 0）
     */
    public static int getInt(Properties props, String key) {
        return getInt(props, key, 0);
    }

    // 获取数值型属性（可指定默认值）
    public static int getInt(Properties props, String key, int defaultValue) {
        int value = defaultValue;
        if (props.containsKey(key)) {
            value = DataTypeConvert.castInt(props.getProperty(key));
        }
        return value;
    }

    /**
     * 获取布尔型属性（默认值为 false）
     */
    public static boolean getBoolean(Properties props, String key) {
        return getBoolean(props, key, false);
    }

    /**
     * 获取布尔型属性（可指定默认值）
     */
    public static boolean getBoolean(Properties props, String key, boolean defaultValue) {
        boolean value = defaultValue;
        if (props.containsKey(key)) {
            value = DataTypeConvert.castBoolean(props.getProperty(key));
        }
        return value;
    }
}
