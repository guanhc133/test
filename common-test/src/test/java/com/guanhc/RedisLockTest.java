package com.guanhc;

import com.finance.test.msg.send.util.util.Lock;
import com.finance.test.msg.send.util.util.RedisLockHandler;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/************************************************************************************
 * Copyright (c) 2017 © Bestpay Co., Ltd.  All Rights Reserved.
 * This software is published under the terms of the Bestpay.
 * Software License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 * <p>
 * File name:      
 * Create on:      2018/6/7
 * Author :        官红诚
 * <p>
 * ChangeList
 * -----------------------------------------------------------------------------
 * Date                Editor        ChangeReasons
 * 2018/6/7            官红诚         Create
 ************************************************************************************/
public class RedisLockTest extends BaseTest {

    /*********************测试redis分布锁方法*************************/

    @Autowired
    private RedisLockHandler redisLockHandler;

    @Test
    public void testTryLock(){
        Lock lock = new Lock("LOCK_KEY", "OK");
        try {
            redisLockHandler.tryLock(lock);
        } catch (Exception e) {
            System.out.println("系统异常");
        }finally {
            if (lock != null) {
                redisLockHandler.releaseLock(lock);
            }
        }
    }
}
