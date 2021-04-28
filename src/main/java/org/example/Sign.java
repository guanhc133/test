package org.example;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayOutputStream;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * 加签/验签
 */
public class Sign {


/** */
    /**
     * 加密算法RSA
     */
    public static final String KEY_ALGORITHM = "RSA";

    /** */
    /**
     * 签名算法
     */
    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";

    /** */
    /**
     * 获取公钥的key
     */
    private static final String PUBLIC_KEY = "RSAPublicKey";

    /** */
    /**
     * 获取私钥的key
     */
    private static final String PRIVATE_KEY = "RSAPrivateKey";

    /** */
    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;

    /** */
    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;


    private static final String KEY_SHA = "SHA";
    private static final String KEY_MD5 = "MD5";
    private static String hexString = "0123456789ABCDEF";
    /**
     * MAC算法可选以下多种算法
     *
     * <pre>
     * HmacMD5
     * HmacSHA1
     * HmacSHA256
     * HmacSHA384
     * HmacSHA512
     * </pre>
     */
    public static final String KEY_MAC = "HmacMD5";


    /**
     * BASE64解密
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryptBASE64(String key) throws Exception {
        return Base64Ext.decode(key.getBytes(), Base64Ext.NO_WRAP);
        //return Base64.decodeBase64(key.getBytes());
        //return (new BASE64Decoder()).decodeBuffer(key);
    }

    /**
     * BASE64加密
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static String encryptBASE64(byte[] key) throws Exception {
        return new String(Base64Ext.encode(key, Base64Ext.NO_WRAP));
        //return new String(Base64.encodeBase64(key));
        //return (new BASE64Encoder()).encodeBuffer(key);
    }

    /**
     * MD5加密
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static byte[] encryptMD5(byte[] data) throws Exception {
        MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);
        md5.update(data);
        return md5.digest();

    }

    /**
     * SHA加密
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static byte[] encryptSHA(byte[] data) throws Exception {
        MessageDigest sha = MessageDigest.getInstance(KEY_SHA);
        sha.update(data);
        return sha.digest();

    }

    /**
     * 初始化HMAC密钥
     *
     * @return
     * @throws Exception
     */
    public static String initMacKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_MAC);
        SecretKey secretKey = keyGenerator.generateKey();
        return encryptBASE64(secretKey.getEncoded());
    }

    /**
     * HMAC加密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] encryptHMAC(byte[] data, String key) throws Exception {

        SecretKey secretKey = new SecretKeySpec(decryptBASE64(key), KEY_MAC);
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        mac.init(secretKey);

        return mac.doFinal(data);

    }

    /**
     * byte数组转 String
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static String bytesToString(byte[] src) throws Exception{
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        String bytes =  stringBuilder.toString();
        ByteArrayOutputStream baos = new ByteArrayOutputStream(bytes.length() / 2);
        // 将每2位16进制整数组装成一个字节
        for (int i = 0; i < bytes.length(); i += 2)
            baos.write((hexString.indexOf(bytes.charAt(i)) << 4 | hexString
                    .indexOf(bytes.charAt(i + 1))));
        return new String(baos.toByteArray());
    }

    /** */
    /**
     * <p>
     * 生成密钥对(公钥和私钥)
     * </p>
     *
     * @return
     * @throws Exception
     */
    public static Map<String, Object> genKeyPair() throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keyPairGen.initialize(1024);
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        Map<String, Object> keyMap = new HashMap<String, Object>(2);
        keyMap.put(PUBLIC_KEY, publicKey);
        keyMap.put(PRIVATE_KEY, privateKey);
        return keyMap;
    }

    /** */
    /**
     * <p>
     * 用私钥对信息生成数字签名
     * </p>
     *
     * @param data
     *            已加密数据
     * @param privateKey
     *            私钥(BASE64编码)
     *
     * @return
     * @throws Exception
     */
    public static String sign(byte[] data, String privateKey) throws Exception {
        byte[] keyBytes = decryptBASE64(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(privateK);
        signature.update(data);
        return encryptBASE64(signature.sign());
    }

    /** */
    /**
     * <p>
     * 校验数字签名
     * </p>
     *
     * @param data
     *            已加密数据
     * @param publicKey
     *            公钥(BASE64编码)
     * @param sign
     *            数字签名
     *
     * @return
     * @throws Exception
     *
     */
    public static boolean verify(byte[] data, String publicKey, String sign) throws Exception {
        byte[] keyBytes = decryptBASE64(publicKey);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PublicKey publicK = keyFactory.generatePublic(keySpec);
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initVerify(publicK);
        signature.update(data);
        return signature.verify(decryptBASE64(sign));
    }

    /** */
    /**
     * <P>
     * 私钥解密
     * </p>
     *
     * @param encryptedData
     *            已加密数据
     * @param privateKey
     *            私钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPrivateKey(byte[] encryptedData, String privateKey) throws Exception {
        byte[] keyBytes = decryptBASE64(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateK);
        int inputLen = encryptedData.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return decryptedData;
    }

    /** */
    /**
     * <p>
     * 公钥解密
     * </p>
     *
     * @param encryptedData
     *            已加密数据
     * @param publicKey
     *            公钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPublicKey(byte[] encryptedData, String publicKey) throws Exception {
        byte[] keyBytes = decryptBASE64(publicKey);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicK = keyFactory.generatePublic(x509KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, publicK);
        int inputLen = encryptedData.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return decryptedData;
    }

    /** */
    /**
     * <p>
     * 公钥加密
     * </p>
     *
     * @param data
     *            源数据
     * @param publicKey
     *            公钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPublicKey(byte[] data, String publicKey) throws Exception {
        byte[] keyBytes = decryptBASE64(publicKey);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicK = keyFactory.generatePublic(x509KeySpec);
        // 对数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicK);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return encryptedData;
    }

    /** */
    /**
     * <p>
     * 私钥加密
     * </p>
     *
     * @param data
     *            源数据
     * @param privateKey
     *            私钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPrivateKey(byte[] data, String privateKey) throws Exception {
        byte[] keyBytes = decryptBASE64(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, privateK);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return encryptedData;
    }

    /** */
    /**
     * <p>
     * 获取私钥
     * </p>
     *
     * @param keyMap
     *            密钥对
     * @return
     * @throws Exception
     */
    public static String getPrivateKey(Map<String, Object> keyMap) throws Exception {
        Key key = (Key) keyMap.get(PRIVATE_KEY);
        return encryptBASE64(key.getEncoded());
    }

    /** */
    /**
     * <p>
     * 获取公钥
     * </p>
     *
     * @param keyMap
     *            密钥对
     * @return
     * @throws Exception
     */
    public static String getPublicKey(Map<String, Object> keyMap) throws Exception {
        Key key = (Key) keyMap.get(PUBLIC_KEY);
        return encryptBASE64(key.getEncoded());
    }

    /**
     * java端公钥加密
     *
     * @throws Exception
     */
    public static String encryptedDataOnJava(String data, String PUBLICKEY) throws Exception {
        data = encryptBASE64(encryptByPublicKey(data.getBytes(), PUBLICKEY));
        return data;
    }

    /**
     * java端私钥解密
     *
     * @throws Exception
     */
    public static String decryptDataOnJava(String data, String PRIVATEKEY) throws Exception {
        String temp = "";
        byte[] rs = decryptBASE64(data);
        temp = new String(decryptByPrivateKey(rs, PRIVATEKEY), "UTF-8"); // 以utf-8的方式生成字符串
        return temp;
    }

    public static void main(String[] args) throws Exception {
        String str = "fsEQVJpNRVrrVPHeRenz1aAgg8/9jQt3Gmgcd24l2qJFGxkWH3jNe6spxqueT6K5WPsGnstFwQtAUWUx3qfICSCLDmTli339P0UQ/AjXylPMY+mBcdEntisH2sen0YcIkX7Uw5XrXgz3qvjGFWT6hRdIp/LX7WpQRewfnYgbFtA=";
        // String publicKey =
        // "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCSTVUxoKCloz/LVi+yw4IYlea20v0L8pknjCEmJBl9ycZZdjPEN7VuQoiluM88fW2Wf6heeMFAbbbSbRK8Dm+RFXLB1ehB4qFED3AY1oKSzCO5mZjLTrJ1Ed7uF5PQSFwncyc91GJv7A0VFtC1Lf24J2L/gRiyPoqzkvcFgNXlqwIDAQAB";
        // String privateKey =
        // "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJJNVTGgoKWjP8tWL7LDghiV5rbS/QvymSeMISYkGX3Jxll2M8Q3tW5CiKW4zzx9bZZ/qF54wUBtttJtErwOb5EVcsHV6EHioUQPcBjWgpLMI7mZmMtOsnUR3u4Xk9BIXCdzJz3UYm/sDRUW0LUt/bgnYv+BGLI+irOS9wWA1eWrAgMBAAECgYAIzDe0TUl7zG5Ypu0lXdZj7he6pMNsxYDqPOX9aixSQSD5Xj9MrGDvqXaYKJ3lsFE3vKN+UtqkKjTVQJPg1SlmQ6CAemmFZ6xtHT20Vu3pZlrXO9uncCJKZq7JE3C7UJwtf6KID/8t8hqxPQf8vPH6JCgNMFZK52ETnt6HsS3O4QJBANw8IrDODM9TiDNNDj48/jQDlSPp0kE2YIRowR5OTw+1oKbBuHXqDEvMetoSxyygFwn2k3xvLEpmEJ8KifIeh3ECQQCqD5Myvl5PqSTJ9duWwu2icDKTPykirb0OQvB7p6wi7CeU/D5V4UzfSQZYduFoaGgjUnsxc7jm9TeUntI7BIjbAkEAy76+gT8+zpeCy6Mf6ChZLBmcYisxHq+FvzmCX90me2wWge96DWxHj+BOT21L4lcAuXDqpRXcrb+a5OfFEr93oQJAfarmvm+4p6s5OVjJ/R1sl0XLyc25qxuyAhDrPqVH6cKS+WBw++tyb+m5m7O1m/7TPY7c5E08jMcWXGgEuIIAUwJBAItkiA5t5OX6FccRMwJXgDLckpz+giULb3qg8LwZj1twgM7hU479t6TYuiyn/aJdaADzN5X0qljDnPKYiZ+BDeI=";
//         Map<String,Object> keyMap = genKeyPair();
//         System.out.println("publicKey:"+getPublicKey(keyMap));
//         System.out.println("privateKey:"+getPrivateKey(keyMap));
        // System.out.println("--------");
        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCWR9aSqhIcOuBylSqKofuMdz7BodroGRMbsVvwVFqE/iAbUqy7egb6qn28tuXXpBvaaNxgbsY7r+zjTBXxqKsq/6qUajLXm4lm1UJs8hKYo/6sj9gtmohyiPCLegw3ixFyJnGflHUZpVaYJoW7h5gKoXTlv576+sXvJ9r84ZFxWQIDAQAB";
        String privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJZH1pKqEhw64HKVKoqh+4x3PsGh2ugZExuxW/BUWoT+IBtSrLt6Bvqqfby25dekG9po3GBuxjuv7ONMFfGoqyr/qpRqMtebiWbVQmzyEpij/qyP2C2aiHKI8It6DDeLEXImcZ+UdRmlVpgmhbuHmAqhdOW/nvr6xe8n2vzhkXFZAgMBAAECgYAvwWBkdvsOUX9lqhU8vMDNIE+nBKIlw2IZGMFYm9WwEXludVVCtkJlgaw3Pf/ICPR48X2mf00rRnqGuHT4uFq78JgqqueTeySv9yrtgqhjUf/vfNwO85/s/h7Mg+akQUJfCsO3K+hGozya2JPwPt9hL1XJXPBsMEL9oVmJxKzloQJBAMfoSTarOwZ5uVRB6lAoC0wXMzDVXL1fmuHoyRSD99v/ROQt6b7ONi+xNEellqjOowE+wGp88rdPVnFFm5T4tLUCQQDAcsV/LHi6n+Cw4cVo1PeRo1s4d8Rz2klt/xkGOlaMf03q+ssBsJe+q/pQbCS8xCSZagkTa7kkRoKhy40IXbSVAkEAmQPgJoxinc5GYYwiwkAkhZWshVCLsBiiARGh+KYa7rYyEUZjiL1nFJJbJgxFiejBNI7H0braPh3oWMOjFyBXkQJBAJTzP/DiVr77J0lANS6iRssuCFh4OlEAznyYSKINXRd5ILGSUrWIHKqzKRY93WFM3pbu/v9NDQsKl+jqRsoG3NECQD+jJGwVXOhbJgkX0yvH6IuhPJR9X0O1DxV4hpaCDrLr/BpTH50+nPCB/IEcbMTBQOgU7ZYFIIBHZHNs7wWac80=";
        String date1 = encryptedDataOnJava("abc", publicKey);// 公钥加密密钥A，此处为55555
         System.out.println("加密"+date1);
        String date2 = decryptDataOnJava(date1, privateKey);
        System.out.println("解密"+date2);
        System.out.println("----");

    }


}
