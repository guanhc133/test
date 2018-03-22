package com.finance.test.msg.send.util.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * DESCRIPTION:
 * Create on:2018/3/21,17:05
 *
 * @author guanhc
 */
public class Base64Util {

    /**
     * Base64加密
     */
    public static String encryptBASE64(byte[] key) throws Exception {
        return (new BASE64Encoder()).encodeBuffer(key);
    }

    /**
     * Base64解密
     */
    public static byte[] decryptBASE64(String key) throws Exception {
        return (new BASE64Decoder()).decodeBuffer(key);
    }
}
