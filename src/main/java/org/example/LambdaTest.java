package org.example;

import com.alibaba.fastjson.JSONObject;
import org.example.dto.Transaction;
import org.example.dto.User;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LambdaTest {

    public static void main(String... args) {
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
