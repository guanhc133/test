/************************************************************************************
 * Copyright (c) 2018 © Bestpay Co., Ltd.  All Rights Reserved.
 * This software is published under the terms of the Bestpay.
 * Software License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 * <p>
 * File name:      Lock.java
 * Create on:      2018/2/27 16:07
 * Author :        liud
 * <p>
 * ChangeList
 * ----------------------------------------------------------------------------------
 * Date									Editor						ChangeReasons
 * 2018/2/27 16:07			            liud						Create
 ************************************************************************************/
package com.finance.test.msg.send.util.util;

/**
 * <b>锁</b><br/>
 */
public class Lock {
    private String name;
    private String value;


    public Lock(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
