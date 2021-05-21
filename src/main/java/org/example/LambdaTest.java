package org.example;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import org.example.dto.Transaction;
import org.example.dto.User;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class LambdaTest {

    public static void main(String... args) {

//        case1();

//        case2();
//        case3();

        case4();


    }

    private static void case4() {
        IntStream.rangeClosed(1, 10)
                .peek(i -> {
                    System.out.println("第一次peek");
                    System.out.println(i);
                })
                .filter(i -> i > 5)
                .peek(i -> {
                    System.out.println("第二次peek");
                    System.out.println(i);
                })
                .filter(i -> i % 2 == 0)
                .forEach(i -> {
                    System.out.println("最终结果");
                    System.out.println(i);
                });

        /**
         * 流的生命周期有三个阶段：
         * 起始生成阶段。
         * 中间操作会逐一获取元素并进行处理。 可有可无。所有中间操作都是惰性的，因此，流在管道中流动之前，任何操作都不会产生任何影响。
         * 终端操作。通常分为 最终的消费 （foreach 之类的）和 归纳 （collect）两类。还有重要的一点就是终端操作启动了流在管道中的流动。
         */
        Stream<String> stream = Stream.of("hello", "felord.cn");
        //不会打印
//        stream.peek(System.out::println);
        //.collect启动流  可打印
        stream.peek(System.out::println).collect(Collectors.toList());
    }

    private static void case2() {
        /**
         * 相当于for循环   parallel并行处理
         * 1到10的数字流
         *
         * 类似于
         * IntStream.range(1,10) 表示1-9的数字流
         */
        IntStream.rangeClosed(1, 10).parallel().forEach(i -> {
            long l = System.currentTimeMillis();
            System.out.println("处理开始，时间："+l);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {

            }
            System.out.println(i);
            System.out.println("处理结束,时间：" + (System.currentTimeMillis() - l));
        });

        /**
         * 返回特定数字1，2，3
         */
        IntStream.of(1, 2, 3).forEach(i -> System.out.println(i));
    }

    private static void case3() {
        List<User> userList = new ArrayList<>();
        User user = new User();
        user.setName("1");
        user.setAddress("3");
        List<String> ss = new ArrayList<>();
        ss.add("a");
        ss.add("b");
        ss.add("c");
        user.setLl(ss);
        User user1 = new User();
        user1.setName("2");
        user1.setAddress("4");
        List<String> ss1 = new ArrayList<>();
        ss1.add("a");
        ss1.add("d");
        ss1.add("e");
        user.setLl(ss1);
        userList.add(user);
        userList.add(user1);
        //根据姓名分组统计地址列表
        Map<String, List<String>> collect1 = userList.stream().collect(groupingBy(item -> item.getName(), mapping(item -> item.getAddress(), toList())));
        System.out.println(collect1);


        Map<String, Integer> map = ImmutableMap.of("0", 3, "1", 8, "0.29", 7, "1.67", 3);
        //根据key倒序
        List<Map.Entry<String, Integer>> collect = map.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByKey().reversed()).collect(Collectors.toList());
        System.out.println(collect);
    }

    private static void case1() {
        User raoul = new User("Raoul", "Cambridge");
        User mario = new User("Mario", "Milan");
        User alan = new User("Alan", "Cambridge");
        User brian = new User("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 300),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        /**
         * filter  :  返回的必须是boolen类型
         * map     : 根据最终获取的类型决定返回的类型（比如：对象，字符串，集合等）
         */


        //找出2011年发生的所有交易，并按交易额排序
        List<Transaction> collect = transactions.stream().filter(transaction -> transaction.getYear() == 2011).sorted(Comparator.comparing(Transaction::getValue).thenComparing(transaction -> transaction.getYear())).collect(Collectors.toList());
        System.out.println(JSONObject.toJSONString(collect));
        //交易员在哪些不同的城市工作过
        List<String> collect1 = transactions.stream().map(transaction -> transaction.getTrader().getAddress()).distinct().collect(Collectors.toList());
        System.out.println(JSONObject.toJSONString(collect1));
        //List转Map
//        Map<Integer, Integer> collect2 = transactions.stream().collect(Collectors.toMap(Transaction::getValue, Transaction::getValue));
//        System.out.println(JSONObject.toJSONString(collect2));
        //查找所有来自剑桥的交易员，并按姓名排序
//        transactions.stream().filter(transaction -> transaction.getTrader().getAddress().equals("Cambridge")).sorted(Comparator.comparing(Transaction::getTrader))
        List<User> cambridge = transactions.stream().map(transaction -> transaction.getTrader()).filter(user -> user.getAddress().equals("Cambridge")).sorted(Comparator.comparing(User::getName)).collect(Collectors.toList());
        System.out.println(JSONObject.toJSONString(cambridge));
        //返回所有交易员的姓名字符串，并按字母顺序排序
        //reduce : "sss", (n1, n2) -> n1 + n2   "sss"拼接在 n1 + n2 之前
        String sorted = transactions.stream().map(transaction -> transaction.getTrader().getName()).sorted().reduce("我是拼接字符串", (n1, n2) -> n1 + n2);
        System.out.println(sorted);
        //有没有交易员在米兰工作的？
        boolean milan = transactions.stream().map(transaction -> transaction.getTrader().getAddress()).anyMatch(address -> address.equals("Milan"));
        boolean milan1 = transactions.stream().anyMatch(transaction -> transaction.getTrader().getAddress().equals("Milan"));
        System.out.println(milan);
        //打印生活在剑桥的交易员的所有交易额
        long cambridge1 = transactions.stream().filter(transaction -> transaction.getTrader().getAddress().equals("Cambridge")).map(transaction -> transaction.getValue()).reduce(0, (n1, n2) -> n1 + n2);
        System.out.println(cambridge1);
        //所有交易中，最高的交易额是多少
        Integer reduce = transactions.stream().map(transaction -> transaction.getValue()).reduce(0, Integer::max);
        System.out.println(reduce);
        //找到交易额最小的交易
        Integer min = transactions.stream().map(transaction -> transaction.getValue()).min((n1, n2) -> n1 - n2).get();
        System.out.println(min);


        //reduce案例
        Integer sum = Stream.of(10, 5, 3, 2, 1, 0).reduce(9, LambdaTest::sumTest);
        System.out.println(sum);
    }

    public static Integer sumTest(int a1, int a2) {
        return a1 + a2;
    }

}
