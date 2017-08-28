package com.finance.test.msg.send.userForTest;

/**
 * 描述：动态注入（spring结合aop）演示（xml配置方式）
 * <p>
 * #
 * </p>
 * User: guanhc Date: 2017/8/4 ProjectName:test Version:
 */
public class Aspect {

    public void before(){
        System.out.println("业务执行前方法执行before方法");
    }

    public void after(){
        System.out.println("业务执行后执行after方法");
    }
}
