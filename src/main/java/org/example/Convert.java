package org.example;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import org.example.dto.Child;
import org.example.dto.User;

import java.util.HashMap;
import java.util.Map;

public class Convert {

    public static Object mapToObject(Map<String, Object> map, Class<?> beanClass)
            throws Exception {
        if (map == null) {
            return null;
        }
        Object obj = beanClass.newInstance();
        Object o = BeanUtil.mapToBean(map, beanClass,true, CopyOptions.create());
        return o;
    }

    public static void main(String[] args) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "mingcehng");
        map.put("address", "dizhi");
        Child child = new Child();
        child.setBirth("2021");
        map.put("child", child);
        User o = (User) mapToObject(map, User.class);
        System.out.println(o.toString());
    }
}
