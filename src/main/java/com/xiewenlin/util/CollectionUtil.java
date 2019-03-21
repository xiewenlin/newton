package com.xiewenlin.util;

import java.util.Collection;
import java.util.Map;

/**
 * @author xiewenlin@frtauto.com
 * @ClassName CollectionUtil
 * @Description 集合处理工具类
 * @Date 2019/3/12 17:14
 * @Version V1.0.0
 */
public class CollectionUtil {
    /**
     * 判断 Collection 是否为空
     */
    public static boolean isEmpty(Collection<?> collection) {
        return checkCollectionIsEmpty(collection);
    }

    /**
     * 判断 Collection 是否非空
     */
    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    /**
     * 判断 Map 是否为空
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return checkMapIsEmpty(map);
    }

    /**
     * 判断 Map 是否非空
     */
    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

    /**
     * 校验集合对象是否为空
     * @param coll 集合入参对象
     * @return
     */
    public static boolean checkCollectionIsEmpty(final Collection<? extends Object> coll) {
        return coll == null || coll.isEmpty();
    }

    /**
     * 校验Map对象是否为空
     * @param map 入参Map对象
     * @return
     */
    public static boolean checkMapIsEmpty(final Map<?,?> map) {
        return map == null || map.isEmpty();
    }
}
