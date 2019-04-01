package jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiewenlin@frtauto.com
 * @ClassName StringImprove
 * @Description
 * @Date 2019/3/26 14:28
 * @Version V1.0.0
 */
public class StringImprove {
    public void stringBeforeImproveTest(){
        List<String> stringList=new ArrayList<String>();
        //BigString不到1000次就内存溢出,但是PerfectBigString不会
        for (int i=0;i<1000;i++){
            BigString bigString=new BigString();
            stringList.add(bigString.getSubString(1,5));
        }
    }

    public void stringAfterImproveTest(){
        List<String> stringList=new ArrayList<String>();
        //BigString不到1000次就内存溢出,但是PerfectBigString不会
        for (int i=0;i<1000;i++){
            PerfectBigString perfectBigString=new PerfectBigString();
            stringList.add(perfectBigString.getSubString(1,5));
        }
    }
}

class BigString{
    //一个巨长的字符串
    private String string=new String(new char[100000]);
    public String getSubString(int begin,int end){
        //截取字符串，可能导致内存溢出
        return string.substring(begin,end);
    }
}

class PerfectBigString{
    //一个巨长的字符串
    private String string=new String(new char[100000]);
    public String getSubString(int begin,int end){
        //新的字符串，不会发生内存溢出
        return new String(string.substring(begin,end));
    }
}
