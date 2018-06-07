/************************************************************************************
 * Copyright (c) 2018 © Bestpay Co., Ltd.  All Rights Reserved.
 * This software is published under the terms of the Bestpay.
 * Software License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 * <p>
 * File name:      DistributedLockHandler.java
 * Create on:      2018/2/27 16:04
 * Author :        liud
 * <p>
 * ChangeList
 * ----------------------------------------------------------------------------------
 * Date									Editor						ChangeReasons
 * 2018/2/27 16:04			            liud						Create
 ************************************************************************************/
package com.finance.test.msg.send.util.util;

import com.finance.test.msg.send.util.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * <b>redis锁实现</b><br/>
 */
@Component
@Slf4j
public class RedisLockHandler {
    private final static long LOCK_EXPIRE = 10 * 1000L; //单个业务持有锁的时间10s,防止死锁

    private final static long LOCK_TRY_INTERVAL = 30L; //默认30ms尝试一次

    private final static long LOCK_TRY_TIMEOUT = 0 * 1000L; // 默认获取不到就结束

    @Resource(name="strRedisTemplate")
    private RedisTemplate template;

    /**
     * 操作redis获取全局锁
     *
     * @param lock           锁的名称
     * @param timeout        获取的超时时间
     * @param tryInterval    多少ms尝试一次
     * @param lockExpireTime 获取成功后锁的过期时间
     * @return true 获取成功，false获取失败
     */
    private boolean  getLock(Lock lock,long timeout,long tryInterval,long lockExpireTime){
        try{
            if(StringUtils.isEmpty(lock.getName()) || StringUtils.isEmpty(lock.getValue())){
                return false;
            }
            long startTime = System.currentTimeMillis();
            while (true){
                //opsForValue().setIfAbsent:key存在返回false，不存在设置新的key-value
                if(template.opsForValue().setIfAbsent(lock.getName(),lock.getValue())){
                    //获取锁后设置过期时间
                    template.expire(lock.getName(),lockExpireTime,TimeUnit.MILLISECONDS);
                    log.info(Thread.currentThread().getName() + "lock.getName() : get lock");
                    return true;
                } else {
                    //锁已存在
                    log.info(Thread.currentThread().getName() + "lock.getName() : ----> locking is exist!!!");
                }
                //获取锁超时（当前时间-开始获取锁的时间 > 设置的超时时间）
                if(System.currentTimeMillis() - startTime > timeout){
                    return false;
                }
                Thread.sleep(tryInterval);
            }
        } catch (Exception e){
            log.error(e.getMessage());
            return false;
        }
    }


    /**
     * 尝试获取全局锁
     *
     * @param lock 锁的名称
     * @return true 获取成功，false获取失败
     */
    public boolean tryLock(Lock lock) {
        return getLock(lock, LOCK_TRY_TIMEOUT, LOCK_TRY_INTERVAL, LOCK_EXPIRE);
    }


    /**
     * 尝试获取全局锁
     *
     * @param lock    锁的名称
     * @param timeout 获取超时时间 单位ms
     * @return true 获取成功，false获取失败
     */
    public boolean tryLock(Lock lock, long timeout) {
        return getLock(lock, timeout, LOCK_TRY_INTERVAL, LOCK_EXPIRE);
    }


    /**
     * 尝试获取全局锁
     *
     * @param lock        锁的名称
     * @param timeout     获取锁的超时时间
     * @param tryInterval 多少毫秒尝试获取一次
     * @return true 获取成功，false获取失败
     */
    public boolean tryLock(Lock lock, long timeout, long tryInterval) {
        return getLock(lock, timeout, tryInterval, LOCK_EXPIRE);
    }


    /**
     * 尝试获取全局锁
     *
     * @param lock           锁的名称
     * @param timeout        获取锁的超时时间
     * @param tryInterval    多少毫秒尝试获取一次
     * @param lockExpireTime 锁的过期
     * @return true 获取成功，false获取失败
     */
    public boolean tryLock(Lock lock, long timeout, long tryInterval, long lockExpireTime) {
        return getLock(lock, timeout, tryInterval, lockExpireTime);
    }

    /**
     * 释放锁
     */
    public void releaseLock(Lock lock) {
        if (!StringUtils.isEmpty(lock.getName())) {
            log.info(Thread.currentThread().getName() + " : del lock");
            template.delete(lock.getName());
        }
    }

    public void tryLoanLock(Lock lock) {
        boolean lockFlag = this.tryLock(lock);
        if (!lockFlag) {
            // 上锁失败
            log.error(Thread.currentThread().getName() + "退款上锁失败");
            throw new ServiceException("1111","上锁失败");
        }
    }
}
