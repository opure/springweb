package com.oneler.web;

public class ThreadTest {


    public static String reverseBySort(String str){
        if(str == null || str.length() == 1){
            return null;
        }
        String[] a = str.split("\\.");
        StringBuffer sb = new StringBuffer();
        for (int i = a.length -1 ; i >= 0; i--) {
            sb.append(a[i]);
            //使用StringBuffer从右往左拼接字符
            sb.append(".");
        }
        return sb.toString().substring(0,sb.length()-1);
    }

    public static  String ReverSentence(String str) {
        return (str.lastIndexOf(" ")==-1)?str:str.substring(str.lastIndexOf(" ")+1) +" "+ReverSentence(str.substring(0,str.lastIndexOf(" ")));
    }


    public static void main(String[] args) {
        /**
         * 输入1：www.aukey.com
         输出1：com.aukey.www
         输入2：www.china.gov.cn
         输出2：cn.gov.china.wwwpublic String ReverSentence(String str) {
         return (str.lastIndexOf(" ")==-1)?str:str.substring(str.lastIndexOf(" ")+1) +" "+ReverSentence(str.substring(0,str.lastIndexOf(" ")));
         }
         */
        System.out.println(ReverSentence("www aukey com ddd"));
        System.out.println(reverseBySort("www.aukey.com"));
        System.out.println( new StringBuilder("www.aukey.com").reverse().toString());

    }
}
