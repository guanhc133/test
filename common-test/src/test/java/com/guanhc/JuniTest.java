package com.guanhc;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.finance.test.msg.send.facade.Request.UserReqDto;
import com.finance.test.msg.send.provider.MsgSendMqProvider;
import com.finance.test.msg.send.quartz.MsgSendJob;
import com.finance.test.msg.send.service.UserService;
import org.dozer.Mapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

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
        userReqDto.setUserName("guan");
        userReqDto.setPassword("1111");
        userReqDto.setEmail("1162290346@qq.com");
        System.out.println(userService.regist(userReqDto));
    }

}
