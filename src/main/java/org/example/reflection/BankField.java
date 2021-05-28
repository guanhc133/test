package org.example.reflection;

import java.lang.annotation.*;


/**
 *  @author: guanhongcheng
 *  @Date: 2021/5/28 9:39
 *  @Description: 请求参数描述
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Inherited
@Documented
public @interface BankField {

    /**
     * 字段排序
     *
     * @return
     */
    int order() default -1;

    /**
     * 字段长度
     *
     * @return
     */
    int length() default -1;

    /**
     * 字段类型
     * @return
     */
    String type() default "";
}
