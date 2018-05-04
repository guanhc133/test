package com.guanhc;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.finance.test.msg.send.facade.Request.UserReqDto;
import com.finance.test.msg.send.provider.MsgSendMqProvider;
import com.finance.test.msg.send.quartz.MsgSendJob;
import com.finance.test.msg.send.service.UserService;
import com.finance.test.msg.send.util.util.AESUtil;
import com.finance.test.msg.send.util.util.RSAUtil;
import org.dozer.Mapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * 描述：
 * <p>
 * #
 * </p>
 * User: guanhc Date: 2017/5/12 ProjectName:JuniTest Version:
 */
public class JuniTest extends BaseTest{
    protected static final ObjectMapper MAPPER = new ObjectMapper();
    //    @Autowired
//    private MsgSendJob msgSendJob;
//    @Autowired
//    private MsgSendMqProvider msgSendMqProvider;
    @Autowired
    private Mapper dozerMapper;
    @Autowired
    private UserService userService;

    @Test
    public void testHttp() throws Exception{
        Date date = new Date();
        String gg = JSONObject.toJSONString(date);
        String kk = JSONObject.toJSONStringWithDateFormat(date,"yyyy-MM-dd");
        Date aa = JSONObject.parseObject(kk, Date.class);
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        MAPPER.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        String ff = MAPPER.writeValueAsString(date);
//        Date aa = sd.parse(kk);
        System.out.println("aa:"+aa);
        System.out.println(gg);
        System.out.println("kk:"+kk);
        System.out.println("ff:"+ff);
    }

    /**
     * 使用Timer做简单定时任务
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
        Timer myTimer = new Timer();
        System.out.println("任务开始-----");
        myTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("1");
            }
        },0,3000);
        Thread.sleep(10000);//10秒后取消任务
        myTimer.cancel();
        System.out.println("任务结束-----");
    }

//    @Test
//    public void quartzTest(){
//        msgSendJob.start();
//    }

//    @Test
//    public void mqTest(){
//        msgSendMqProvider.msgSendMq();
//    }

    @Test
    public void dateTest(){
        Calendar cld= Calendar.getInstance();
        cld.setTime(new Date());
        cld.add(cld.DATE,1);
        Date date = cld.getTime();
        System.out.println(date);
    }

    @Test
    public void dozerTest(){
        UserReqDto userReqDto = new UserReqDto();
        userReqDto.setUserName(userReqDto.getEmail());
        userReqDto.setPassword("1111");
        userReqDto.setEmail("1162290346@qq.com");
//        System.out.println(userService.regist(userReqDto));
    }

    @Test
    public void AESEncodeTest(){
        //密钥
        String key = "woshishe";
        //待加密原文
        String content = "我是加密内容";
        System.out.println(AESUtil.AESEncode(key, content));
    }

    @Test
    public void AESDncodeTest(){
        //密钥
        String key = "woshise";
        //加密后的内容
        String content = "2QmBuBwpZKWmjTGZuaEIVIZElAdjO6JrzxQWhk3ebxg=";
        System.out.println(AESUtil.AESDncode(key, content));
    }

    /**********************************非对称RSA加密测试************************************/
    private String publicKey = null;
    private String privateKey = null;

    @Before
    public void setUp() throws Exception {
        Map<String, Object> keyMap = RSAUtil.initKey();
        publicKey = RSAUtil.getPublicKey(keyMap);
        privateKey = RSAUtil.getPrivateKey(keyMap);

        System.out.println("公钥 -> " + publicKey);
        System.out.println("私钥 -> " + privateKey);
    }

    @Test
    public void test() throws Exception {
        System.out.println("公钥加密，私钥解密");
        String sourceString = "hi, RSA";

        byte[] encodedData = RSAUtil.encrypt(sourceString.getBytes(), publicKey, true);
        byte[] decodedData = RSAUtil.decrypt(encodedData, privateKey, false);

        String targetString = new String(decodedData);
        System.out.println("加密前: " + sourceString + "，解密后: " + targetString);
        assertEquals(sourceString, targetString);
    }

    @Test
    public void test2() throws Exception {
        System.out.println("私钥签名，公钥验证签名");
        String sourceString = "hello, RSA sign";
        byte[] data = sourceString.getBytes();

        // 产生签名
        String sign = RSAUtil.sign(data, privateKey);
        System.out.println("签名 -> " + sign);

        // 验证签名
        boolean status = RSAUtil.verify(data, publicKey, sign);
        System.out.println("状态 -> " + status);
        assertTrue(status);
    }

    @Test
    public void test3() throws Exception {
        System.out.println("私钥加密，公钥解密");
        String sourceString = "hello, reRSA";
        byte[] data = sourceString.getBytes();

        byte[] encodedData = RSAUtil.encrypt(data, privateKey, false);
        byte[] decodedData = RSAUtil.decrypt(encodedData, publicKey, true);

        String targetString = new String(decodedData);
        System.out.println("加密前: " + sourceString + "，解密后: " + targetString);
        assertEquals(sourceString, targetString);
    }

}
