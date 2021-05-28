package org.example.reflection;

import java.util.Arrays;
import java.util.Comparator;


/**
 *  @author: guanhongcheng
 *  @Date: 2021/5/28 14:33
 *  @Description: 模拟向第三方发送请求
 */
public class BankService {

    public static String sendRequest(AbstractApi api) {
        BankApi annotation = api.getClass().getAnnotation(BankApi.class);
        //获取bankField注解并按照注解排序，拼接参数等
        Arrays.stream(api.getClass().getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(BankField.class))
                .sorted(Comparator.comparing(a -> a.getAnnotation(BankField.class).order()))
                .peek(field -> field.setAccessible(true))
                .forEach(field -> {
                    BankField fieldAnnotation = field.getAnnotation(BankField.class);
                    StringBuilder sb = new StringBuilder();
                    Object value = "";
                    try {
                        //获取当前类的当前字段值
                        value = field.get(api);
                    } catch (IllegalAccessException e) {
                        System.out.println("获取字段值异常" + e);
                    }
                    //拼接字符串
                    //根据字段类型以正确的填充方式格式化字符串
                    switch (fieldAnnotation.type()) {
                        case "String":
                            //不满足30位左边拼接“_”
                            sb.append(String.format("%"+String.valueOf(fieldAnnotation.length())+"s",value.toString()).replace(" ","_"));
                        case "Int":
                            //不满足30位右边拼接0
                            sb.append(String.format("%-"+String.valueOf(fieldAnnotation.length())+"s",value.toString()).replace(" ","0"));
                        default:
                            break;
                    }
                });
        //此处可以有其他逻辑 eg：加密
        return "发送成功！";
    }
}
