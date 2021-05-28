package org.example.reflection;


import java.lang.annotation.*;

/**
 * @author: guanhongcheng
 * @Date: 2021/5/28 9:31
 * @Description: 银行接口描述
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
@Documented
public @interface BankApi {

    /**
     * 调用api地址
     *
     * @return
     */
    String apiUrl() default "";


    /**
     * api描述
     * @return
     */
    String desc() default "";

}
