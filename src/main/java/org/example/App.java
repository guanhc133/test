package org.example;

import com.alibaba.fastjson.JSONObject;
import org.example.dto.User;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws ParseException {


        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse("C:\\Users\\11622\\Desktop\\filePath.xml");
            // 接口提供对节点的有序集合的抽象，没有定义或约束如何实现此集合。DOM 中的 NodeList 对象是活动的
            // NodeList 中的项可以通过从 0 开始的整数索引进行访问
            NodeList xml = document.getChildNodes();
            for (int i = 0; i < xml.getLength(); i++) {
                Node roots = xml.item(i);
                NodeList persons = roots.getChildNodes();
                for (int j = 0; j < persons.getLength(); j++) {
                    Node person = persons.item(j);
                    NodeList pros = person.getChildNodes();
                    for (int k = 0; k < pros.getLength(); k++) {
                        Node item = pros.item(k);
                        System.out.println(item.getNodeName() + ":" + item.getTextContent());
                    }
                }
            }
            System.out.println("XML解析完毕");
        } catch (Exception e) {
            e.printStackTrace();
        }


        /**
         * lambda match的用法
         */
//        List<String> strs = Arrays.asList("a", "a", "a", "a", "b");
        List<String> strs = Arrays.asList("c", "d", "g", "h", "b");


        boolean aa = strs.stream().anyMatch(str -> str.equals("a"));
        boolean bb = strs.stream().allMatch(str -> str.equals("a"));
        boolean cc = strs.stream().noneMatch(str -> str.equals("a"));
        long count = strs.stream().filter(str -> str.equals("a")).count();
        System.out.println(aa);// TRUE
        System.out.println(bb);// FALSE
        System.out.println(cc);// FALSE
        System.out.println(count);// 4


        List<Boolean> collect = strs.stream().map(String::isEmpty).collect(Collectors.toList());
        System.out.println(collect);

        List<Integer> ll = new ArrayList<Integer>();
        ll.add(1);
        ll.add(2
        );
        ll.add(3);
        ArrayList<Integer> integers = new ArrayList<>(ll);
        List<Integer> ll1 = new ArrayList<Integer>();
        ll1.add(1);
        ll1.add(2);
        ll1.add(4);
//        List<Integer> kk = ll.stream().filter(integer -> {
//            return 1 == integer;
//        }).collect(Collectors.toList());


        for (Integer h : ll1) {
            Integer finalh = h;
            boolean bol = integers.stream().noneMatch(integer -> {
                return integer.equals(finalh);
            });
            if (bol) {
                //相同
                h = 0;
            }
        }
        for (Integer o :ll1) {
            System.out.println(o);
        }

        addZeroForNum("199999",10);
        BigDecimal bigDecimal = null;
        System.out.println(bigDecimal);
        String date = "1993-07-10";
        String date1 = "2099-07-09";
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        Date parse = sd.parse(date);
        Date parse1 = sd.parse(date1);

        System.out.println(getCurrentAge("341226199207105016", parse1));


        /**
         * lambda optional的用法
         */
        try {
            Optional<User> empty = Optional.empty();
//            empty.get();
            boolean present = empty.isPresent();
            System.out.println(present);
            User user = null;
            Optional.of(user);
            Optional<User> user1 = Optional.ofNullable(user);
            User user2 = user1.get();
            System.out.println(user2);
        } catch (Exception e) {
            System.out.println("出现异常：" + e);
        }

        List<Integer> enterpriseIds = new ArrayList<>();
        enterpriseIds.add(1);
        enterpriseIds.add(2);
        List<Integer> existsEnterpriseIds = new ArrayList<>();
        existsEnterpriseIds.add(1);
        existsEnterpriseIds.add(2);
        existsEnterpriseIds.add(3);
        List<Integer> saveEnterpriseIds = enterpriseIds.stream().filter(item -> !existsEnterpriseIds.contains(item)).collect(Collectors.toList());
        List<Integer> deleteEnterpriseIds = existsEnterpriseIds.stream().filter(item -> !enterpriseIds.contains(item)).collect(Collectors.toList());
        System.out.println(JSONObject.toJSONString(saveEnterpriseIds));
        System.out.println(JSONObject.toJSONString(deleteEnterpriseIds));
//        User user = new User();
//        user.setName("sss");
//        List<User> users = Arrays.asList(user);
//        System.out.println(JSONObject.toJSONString(users));
//        Map<String, Object> ratioMap = users.stream().collect(Collectors.toMap(User::getName, Function.identity()));
//        System.out.println(ratioMap);
    }



    public static String addZeroForNum(String str, int strLength) {
        int strLen = str.length();
        if (strLen < strLength) {
            while (strLen < strLength) {
                StringBuffer sb = new StringBuffer();
                sb.append("0").append(str);// 左补0
                // sb.append(str).append("0");//右补0
                str = sb.toString();
                strLen = str.length();
            }
        }
        System.out.println(str);
        return str;
    }

    public static boolean getCurrentAge(String cerNo,Date endDate) throws ParseException {


        String dd = cerNo.substring(6, 14);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        formatter.setLenient(false);
        Date newDate= formatter.parse(dd);
        formatter = new SimpleDateFormat("yyyy-MM-dd");
        String ff = formatter.format(newDate);
        Date birthday = formatter.parse(ff);
        System.out.println(birthday);
        boolean flag = false;
        // 当前时间
        Calendar curr = Calendar.getInstance();
        // 生日
        Calendar born = Calendar.getInstance();
        born.setTime(birthday);
        // 年龄 = 当前年 - 出生年
        int age = curr.get(Calendar.YEAR) - born.get(Calendar.YEAR);
        if (age <= 0) {
            return flag;
        }
        // 如果当前月份小于出生月份: age-1
        // 如果当前月份等于出生月份, 且当前日小于出生日: age-1
        int currMonth = curr.get(Calendar.MONTH);
        int currDay = curr.get(Calendar.DAY_OF_MONTH);
        int bornMonth = born.get(Calendar.MONTH);
        int bornDay = born.get(Calendar.DAY_OF_MONTH);
        if ((currMonth < bornMonth) || (currMonth == bornMonth && currDay <= bornDay)) {
            age--;
        }
        int finalAge = age < 0 ? 0 : age;
        if (finalAge < 45) {
            SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c = Calendar.getInstance();
            c.add(Calendar.YEAR, 20);
            Calendar  from  =  Calendar.getInstance();
            from.setTime(endDate);
            Calendar  to  =  Calendar.getInstance();
            to.setTime(c.getTime());
            int fromYear = from.get(Calendar.YEAR);
            int toYear = to.get(Calendar.YEAR);
            int year = toYear  -  fromYear;
            int ee = (year < 0) ? -year : year;
            if(fromYear > toYear){
                return flag;
            }
        }
        return true;
    }
}
