package org.example;

import cn.hutool.core.util.XmlUtil;
import cn.hutool.json.JSONUtil;

public class StringFormatTest {

    /**
     * 接收报文长度,业务报文长度，不足左侧补0
     */
    private static final String BUSINESS_SIZE = "%05d";

    /**
     * 接收报文长度,业务报文长度，不足右侧补空格
     */
    private static final String TRANS_CODE_SIZE = "%-5s";

    /**
     * 6位签名长度
     */
    private static final String SIGN_SIZE = "%06d";


    public static void main(String[] args) {
        //不足5位用0在左侧补足
        String format1 = String.format("%05d", 99);
        //满足5位不用补
        String format2 = String.format("%05d", 99999);
        System.out.println("转换后的字符串："+format1+"我是结尾");
        System.out.println("转换后的字符串："+format2+"我是结尾");


        //不足5位用0在空格补足
        String format3 = String.format("%-5s", 99);
        //满足5位不用补
        String format4 = String.format("%-5s", 99999);
        System.out.println("转换后的字符串："+format3+"我是结尾");
        System.out.println("转换后的字符串：" + format4 + "我是结尾");

    }
}
