package org.example;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * 公私钥加解密
 */
public class RsaEncrypt {

    //保存密钥对
    public static Map<String, String> keyMap = new HashMap<>();


    //生成公私密钥对
    public static void genKeyPair() throws NoSuchAlgorithmException {
        // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
        KeyPairGenerator rsa = KeyPairGenerator.getInstance("RSA");
        //通过RSA初始化密钥生成器
        rsa.initialize(1024, new SecureRandom());
        //生成密钥对保存在keyPair中
        KeyPair keyPair = rsa.generateKeyPair();
        //获取私钥
        PrivateKey aPrivate = keyPair.getPrivate();
        //获取公钥
        PublicKey aPublic = keyPair.getPublic();
        //base64加密公私密钥
        String sPrivate = new String(Base64.getEncoder().encode(aPrivate.getEncoded()));
        String sPublic = new String(Base64.getEncoder().encode(aPublic.getEncoded()));
        keyMap.put("sPrivate", sPrivate);
        keyMap.put("sPublic", sPublic);
    }

    /**
     * 公钥加密
     *
     * @param data      待加密数据
     * @param publicKey 公钥
     * @return
     */
    public static String encrypt(String data, String publicKey) throws Exception {
        //base64解编码
        byte[] decode = Base64.getDecoder().decode(publicKey);
        //获取包装后的公钥
        PublicKey publicKey1 = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decode));
        //获取操作加密对象
        Cipher cipher = Cipher.getInstance("RSA");
        //选择加密模式
        cipher.init(Cipher.ENCRYPT_MODE, publicKey1);
        //执行加密方法
        String encryptData = Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes("UTF-8")));
        return encryptData;
    }


    /**
     * 私钥解密
     *
     * @param data       待解密数据
     * @param privateKey 私钥
     * @return
     */
    public static String decrypt(String data, String privateKey) throws Exception {
        //base64解编码
        byte[] decode = Base64.getDecoder().decode(privateKey);
        PrivateKey privateKey1 = KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decode));
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey1);
        String decryptData = new String(cipher.doFinal(Base64.getDecoder().decode(data.getBytes("UTF-8"))));
        return decryptData;
    }


    public static void main(String[] args) throws Exception {
        genKeyPair();
        String sPrivate = keyMap.get("sPrivate");
        System.out.println(sPrivate);
        String sPublic = keyMap.get("sPublic");
        System.out.println(sPublic);
        String encryptData = encrypt("我是一棵松", sPublic);
        System.out.println(encryptData);
        String decryptData = decrypt(encryptData, sPrivate);
        System.out.println(decryptData);
    }


}
