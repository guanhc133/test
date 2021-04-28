package org.example;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortTest {



    public static void main(String[] args) {
        List<Integer> ll = new ArrayList<>();
        ll.add(10);
        ll.add(2);
        ll.add(20);
        ll.add(20);

        Integer bestNum = 20;

        Collections.sort(ll,new Comparator<Integer>(){

            /**
             * 总结
             * 正序：
             * after < before = -1 互换位置
             * after > before = 1  不换位置
             *
             * 倒序
             * after < before = 1  不换位置
             * after > before = -1  换位置
             * @param o1  after
             * @param o2  before
             * @return
             */
            @Override
            public int compare(Integer o1, Integer o2) {
                //需求1：20永远排在第一，其余按照正序排序
//                 if (o1.equals(bestNum)) {
//                    //换位置
//                    return -1;
//                }
//                if (o2.equals(bestNum)) {
//                    return 1;
//                }
//                return o1.compareTo(o2);

                //需求2：正序排序
//                 if (o1 < o2) {
//                    return -1;
//                } else if (o1 > o2) {
//                    return 1;
//                } else {
//                    return 0;
//                }


                //需求3：倒序排序

//                if (o1 < o2) {   // eg:  顺序 -> o2,o1   如果o1 < o2(且o1在o2后面，因为倒序，大的在o1前面，所以不需要交换位置 返回1)
//                    return 1;
//                } else if (o1 > o2) { // eg:  顺序 -> o2,o1   如果o1 > o2(且o1在o2后面，因为倒序，大的在o2后面，所以需要交换位置 返回-1)
//                    return -1;
//                } else {
//                    return 0;  //相同则不排序
//                }


                //需求4：如果存在10就排最后一位，其余按照倒序排序
                if(o1.equals(10)) {
                    return 1;
                }
                if (o2.equals(10)) {
                    return -1;
                }
                if (o1 < o2) {
                    return 1;
                } else if (o1 > o2) {
                    return -1;
                } else {
                    return 0;
                }

            }
        });
        System.out.println(JSONObject.toJSONString(ll));
    }


}
