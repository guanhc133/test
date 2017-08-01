package com.guanhc;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.finance.test.msg.send.facade.Request.UserReqDto;
import com.finance.test.msg.send.provider.MsgSendMqProvider;
import com.finance.test.msg.send.quartz.MsgSendJob;
import com.finance.test.msg.send.service.UserService;
import org.dozer.Mapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 描述：
 * <p>
 * #
 * </p>
 * User: guanhc Date: 2017/5/12 ProjectName:JuniTest Version:
 */
public class JuniTest extends BaseTest {
    protected static final ObjectMapper MAPPER = new ObjectMapper();
    //    @Autowired
//    private MsgSendJob msgSendJob;
//    @Autowired
//    private MsgSendMqProvider msgSendMqProvider;
    @Autowired
    private TaskExecutor executor;
    @Autowired
    private Mapper dozerMapper;
    @Autowired
    private UserService userService;

    @Test
    public void testHttp() throws Exception {
        Date date = new Date();
        String gg = JSONObject.toJSONString(date);
        String kk = JSONObject.toJSONStringWithDateFormat(date, "yyyy-MM-dd");
        Date aa = JSONObject.parseObject(kk, Date.class);
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        MAPPER.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        String ff = MAPPER.writeValueAsString(date);
//        Date aa = sd.parse(kk);
        System.out.println("aa:" + aa);
        System.out.println(gg);
        System.out.println("kk:" + kk);
        System.out.println("ff:" + ff);
    }

    /**
     * 使用Timer做简单定时任务
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        Timer myTimer = new Timer();
        System.out.println("任务开始-----");
        myTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("1");
            }
        }, 0, 3000);
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
    public void dateTest() {
        Calendar cld = Calendar.getInstance();
        cld.setTime(new Date());
        cld.add(cld.DATE, 1);
        Date date = cld.getTime();
        System.out.println(date);
    }

    @Test
    public void dozerTest() {
        UserReqDto userReqDto = new UserReqDto();
        userReqDto.setUserName("guan");
        userReqDto.setPassword("1111");
        userReqDto.setEmail("1162290346@qq.com");
        System.out.println(userService.regist(userReqDto));
    }

    /**
     * 获取文件路径
     */
    @Test
    public void testClassLoder() {
        System.out.println(JuniTest.class.getClassLoader().getResource("spring").getPath());
    }

    @Test
    public void testJson() {
        String a = "[\n" +
                "    {\n" +
                "        \"id\": 1,\n" +
                "        \"name\": \"Tony老师聊shell——环境变量配置文件\",\n" +
                "        \"picSmall\": \"http://img.mukewang.com/55237dcc0001128c06000338-300-170.jpg\",\n" +
                "        \"picBig\": \"http://img.mukewang.com/55237dcc0001128c06000338.jpg\",\n" +
                "        \"description\": \"为你带来shell中的环境变量配置文件\",\n" +
                "        \"learner\": 12312\n" +
                "    }]";
        Set b = JSON.parseObject(a, new TypeReference<Set>() {
        }.getType());
        System.out.println(b);
    }

    @Test
    public void testMapJson() {
        Map map = new HashMap();
        map.put("a", "b");
        map.put("n", "d");
        map.put("f", "b");
        System.out.println(map);
    }

    /**
     * 使用TaskExecutor需要配置线程池（本例配置在rabbitMq中）
     */
    @Test
    public void testTaskExcutor() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    System.out.println(i);
                }
            }
        });
    }
}
