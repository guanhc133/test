package com.finance.test.msg.send.util.exception;

import com.finance.test.msg.send.util.enums.TestBizCode;

import java.io.Serializable;

/**
 * 描述：
 * <p>
 * #
 * </p>
 * User: guanhc Date: 2017/7/4 ProjectName:test Version:
 */
public class ServiceException extends RuntimeException implements Serializable{

    private static final long serialVersionUID = 8865293636951109826L;
    private TestBizCode testBizCode;
    private String bizCode;
    private String bizMsg;

    public ServiceException(String message, TestBizCode testBizCode) {
        super(message);
        setBizErrorCode(testBizCode);
    }

    public ServiceException(TestBizCode testBizCode) {
        this(testBizCode.getBizMsg(), testBizCode);
    }

    public ServiceException(String bizCode, String bizMsg) {
        super(bizMsg);
        this.bizCode = bizCode;
        this.bizMsg = bizMsg;
    }

    public void setBizErrorCode(TestBizCode testBizCode) {
        this.testBizCode = testBizCode;
    }

    public String getBizCode() {
        if (testBizCode != null) {
            return testBizCode.getBizCode();
        }
        return bizCode;
    }

    public String getBizMsg() {
        if (testBizCode != null) {
            return testBizCode.getBizMsg();
        }
        return bizMsg;
    }

}
