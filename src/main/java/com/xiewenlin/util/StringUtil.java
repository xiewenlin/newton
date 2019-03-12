package com.xiewenlin.util;

/**
 * @author xiewenlin@frtauto.com
 * @ClassName StringUtil
 * @Description 字符串处理工具类
 * @Date 2019/3/12 15:41
 * @Version V1.0.0
 */
public class StringUtil {
    /**
     * 判断传入的字符串是否为空，过滤类型包括但不限于："",null,NULL,undefined,带空格的"  "
     * @param paramStr
     * @return
     */
    public static boolean isEmptyX(String paramStr){
        boolean checkResult=false;
        if(isBlank(paramStr)||"".equals(paramStr.trim())||"null".equals(paramStr.trim().toLowerCase())||"undefined".equals(paramStr.trim().toLowerCase())){
            checkResult=true;
        }
        return checkResult;
    }
    /**
     * 判断字符串是否非空
     */
    public static boolean isNotEmptyX(String str) {
        return !isEmptyX(str);
    }
    /**
     * 取自org.apache.commons.lang3.StringUtils中的方法
     * <p>Checks if a CharSequence is empty (""), null or whitespace only.</p>
     *
     * <pre>
     * StringUtils.isBlank(null)      = true
     * StringUtils.isBlank("")        = true
     * StringUtils.isBlank(" ")       = true
     * StringUtils.isBlank("bob")     = false
     * StringUtils.isBlank("  bob  ") = false
     * </pre>
     */
    public static boolean isBlank(final CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
