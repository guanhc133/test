package com.guanhc;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.finance.test.msg.send.facade.Request.UserReqDto;
import com.finance.test.msg.send.facade.response.UserRespDto;
//import com.finance.test.msg.send.mapper.CityRegionInfoMapper;
//import com.finance.test.msg.send.model.CityRegionInfo;
//import com.finance.test.msg.send.model.CityRegionInfoExample;
import com.finance.test.msg.send.service.UserService;
import com.finance.test.msg.send.userForTest.Service;
import com.finance.test.msg.send.util.model.Response;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.dozer.Mapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.task.TaskExecutor;
import org.springframework.util.ReflectionUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.lang.reflect.Method;
import java.net.URLDecoder;
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
    @Autowired
    private Service service;
//    @Autowired
//    private CityRegionInfoMapper cityRegionInfoMapper;
    @Autowired
    private ApplicationContext applicationContext;

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
     * 使用TaskExecutor需要配置线程池（本例配置在spring-datasource.xml中）
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

    /**
     * 测试切面插入编程
     */
    @Test
    public void testAspect() {
        service.doSomethingOne();
        service.doSomethingTwo();
    }

    /**
     * 测试Workbook wb = new XSSFWorkbook(in);报错问题
     *
     * @throws Exception
     */
    @Test
    public void testReplace() throws Exception {
        String s = "/E:/tools/test/common-test/target/classes/user/user.xlsx";
        File file = new File(s);
        System.out.println(file);
        InputStream in = new FileInputStream(file);
        Workbook wb = new XSSFWorkbook(in);
        System.out.println(wb);
    }

    /**
     * 获取某文件路径写法
     * 此处以获取spring-mvc.xml为例
     */
    @Test
    public void testQuartz() {
        String cron = "0/5 * * * * ? *";
        String springPath = this.getClass().getClassLoader().getResource("spring").getPath();
        String path = springPath + "/" + "spring-mvc.xml";
        System.out.println(path);
    }

    /**
     * dom解析xml
     *
     * @throws Exception
     */
    @Test
    public void testson() throws Exception {
        Element element = null;
        // 可以使用绝对路劲
        String path = this.getClass().getClassLoader().getResource("user").getPath();
        String xmlpath = path + "/" + "list.xml";
        File f = new File(xmlpath);
        // documentBuilder为抽象不能直接实例化(将XML文件转换为DOM文件)
        DocumentBuilder db = null;
        DocumentBuilderFactory dbf = null;
        try {
            // 返回documentBuilderFactory对象
            dbf = DocumentBuilderFactory.newInstance();
            // 返回db对象用documentBuilderFatory对象获得返回documentBuildr对象
            db = dbf.newDocumentBuilder();
            // 得到一个DOM并返回给document对象
            Document dt = db.parse(f);
            // 得到一个elment根元素
            element = dt.getDocumentElement();
            // 获得根节点
            System.out.println("根元素：" + element.getNodeName());
            // 获得根元素下的子节点
            NodeList childNodes = element.getChildNodes();
            String provinceCode = "";
            String provinceCode2 = "";
            String provinceName = "";
            String provinceName2 = "";
            String townCode = "";
            String townName = "";

            // 遍历这些子节点
            for (int i = 0; i < childNodes.getLength(); i++) {
                // 获得每个对应位置i的结点
                Node node1 = childNodes.item(i);
                if ("dict".equals(node1.getNodeName())) {
                    // 获得<dict>下的节点
                    NodeList nodeDetail = node1.getChildNodes();
                    // 遍历<dict>下的节点（共3个类型节点：key，array，dict）
                    for (int j = 0; j < nodeDetail.getLength(); j++) {
                        // 获得<dict>元素每一个节点
                        Node detail = nodeDetail.item(j);
                        NodeList det;
                        if ("key".equals(detail.getNodeName())) { // 输出code
                            System.out.println("省市: " + detail.getTextContent());
                            provinceCode = detail.getTextContent().substring(0, 6);
                            provinceName = detail.getTextContent().substring(6, detail.getTextContent().length());
                            System.out.println("省市code: " + provinceCode);
                            System.out.println("省市name: " + provinceName);
                            Node detail1 = nodeDetail.item(j + 2);
                            if (!"dict".equals(detail1.getNodeName())) {
                                //落库
                                //一级
                                provinceName2 = detail.getTextContent().substring(6, detail.getTextContent().length() - 1);
//                                this.insert("1", provinceName2, provinceCode, "#");
                                //二级
                                provinceCode2 = detail.getTextContent().substring(0, 2) + detail.getTextContent().substring(3, 4) + "100";
//                                this.insert("2", provinceName, provinceCode2, provinceCode);
                            } else {
                                //一级
                                provinceCode2 = detail.getTextContent().substring(0, 2) + detail.getTextContent().substring(3, 4) + "100";
//                                this.insert("1", provinceName, provinceCode, "#");
                            }

                        } else if ("array".equals(detail.getNodeName())) { // 输出pass
                            det = detail.getChildNodes();
                            for (int k = 0; k < det.getLength(); k++) {
                                Node dd = det.item(k);
                                if (k % 2 == 0) {

                                } else {
                                    System.out.println("区县: " + dd.getTextContent());
                                    townCode = dd.getTextContent().substring(0, 6);
                                    townName = dd.getTextContent().substring(6, dd.getTextContent().length());
                                    System.out.println("区县code: " + townCode);
                                    System.out.println("区县Name: " + townName);
//                                    this.insert("3", townName, townCode, provinceCode2);
                                }
                            }
                            //TODO录入数据（2层结构）
                        } else if ("dict".equals(detail.getNodeName())) {
                            NodeList qq;
                            NodeList dd = detail.getChildNodes();
                            String provinceCode1 = "";
                            String provinceName1 = "";
                            String townCode1 = "";
                            String townName1 = "";
                            for (int r = 0; r < dd.getLength(); r++) {
                                Node ww = dd.item(r);
                                if ("key".equals(ww.getNodeName())) { // 输出code
                                    System.out.println("二级市区: " + ww.getTextContent());
                                    provinceCode1 = ww.getTextContent().substring(0, 6);
                                    provinceName1 = ww.getTextContent().substring(6, ww.getTextContent().length());
                                    System.out.println("二级市区code1: " + provinceCode1);
                                    System.out.println("二级市区name1: " + provinceName1);
//                                    this.insert("2", provinceName1, provinceCode1, provinceCode);
                                } else if ("array".equals(ww.getNodeName())) { // 输出pass
                                    qq = ww.getChildNodes();
                                    for (int k = 0; k < qq.getLength(); k++) {
                                        Node rr = qq.item(k);
                                        if (k % 2 != 0) {
                                            System.out.println("区县: " + rr.getTextContent());
                                            townCode1 = rr.getTextContent().substring(0, 6);
                                            townName1 = rr.getTextContent().substring(6, rr.getTextContent().length());
                                            System.out.println("区县code1: " + townCode1);
                                            System.out.println("区县name1: " + townName1);
//                                            this.insert("3", townName1, townCode1, provinceCode1);
                                        }
                                    }
                                }
                            }
                            //录入数据（3层结构)
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insert(String level, String name, String regionCode, String parentCode) {
//        CityRegionInfo cityRegionInfo = new CityRegionInfo();
//        cityRegionInfo.setCreatedAt(new Date());
//        cityRegionInfo.setCreatedBy("SYS");
//        cityRegionInfo.setLevelType(level);
//        cityRegionInfo.setName(name);
//        cityRegionInfo.setRegionCode(regionCode);
//        cityRegionInfo.setParentCode(parentCode);
//        cityRegionInfoMapper.insert(cityRegionInfo);
    }

    /**
     * 解析json格式文件
     */
    @Test
    public void testParaseJson() {
        String path = this.getClass().getClassLoader().getResource("user").getPath();
//        CityRegionInfoExample cityRegionInfoExample = new CityRegionInfoExample();
//        cityRegionInfoExample.createCriteria().andLevelTypeEqualTo("3");
//        List<CityRegionInfo> list = cityRegionInfoMapper.selectByExample(cityRegionInfoExample);
        String regionCode = "";
        String jsonpath = "";
//        if (null != list && list.size() > 0) {
//            for (int t = 0; t < list.size(); t++) {
//                regionCode = list.get(t).getRegionCode();
                jsonpath = path + "/" + regionCode + ".json";
                String a = this.ReadFile(jsonpath);
                JSONObject json = JSON.parseObject(a);
                if (null != json) {
                    Set<String> set = json.keySet();
                    for (String key : set) {
                        String dd = (String) json.get(key);
//                        CityRegionInfo cityRegionInfo = new CityRegionInfo();
//                        cityRegionInfo.setRegionCode(key);
//                        cityRegionInfo.setLevelType("4");
//                        cityRegionInfo.setParentCode(regionCode);
//                        cityRegionInfo.setName(dd);
//                        cityRegionInfo.setCreatedAt(new Date());
//                        cityRegionInfo.setCreatedBy("SYS");
//                        cityRegionInfoMapper.insert(cityRegionInfo);
                    }
                }
//            }
//        }
    }

    /**
     * 解析json
     *
     * @param Path
     * @return
     */
    public String ReadFile(String Path) {
        BufferedReader reader = null;
        String laststr = "";
        try {
            FileInputStream fileInputStream = new FileInputStream(Path);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            reader = new BufferedReader(inputStreamReader);
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                laststr += tempString;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return laststr;
    }

    /**
     * 反射获取类并执行类方法
     * @throws Exception
     */
    @Test
    public void testReflectMethod() throws Exception{
        String classPath = "com.finance.test.msg.send.service.UserService";
        String mothedName = "regist";
        UserReqDto userReqDto = new UserReqDto();
        userReqDto.setEmail("11@qq.com");
        userReqDto.setUserName("jsj");
        userReqDto.setPassword("8799876");


        //获取userReqDto类全路径
        Class<? extends UserReqDto> classx = userReqDto.getClass();

        Object instance = Class.forName(classPath).newInstance();
        System.out.println(instance);
        //获取userService实例的Class
        Class<? extends Object> ownerClass = Class.forName(classPath).newInstance().getClass();

        //获取方法
        Method method = ReflectionUtils.findMethod(ownerClass, mothedName, classx);
        //ownerClass.getSimpleName()获取的时UserService，须转换成userService
        String name = ownerClass.getSimpleName().substring(0, 1).toLowerCase() + ownerClass.getSimpleName().substring(1);
        //返回UserService
        UserService beanName = (UserService) applicationContext.getBean(name);
        System.out.println(beanName);
        //执行regist方法
        Response<UserRespDto> result = (Response<UserRespDto>) ReflectionUtils.invokeMethod(method, applicationContext.getBean(name), userReqDto);
        System.out.println(result);
    }
}
