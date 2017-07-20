package com.finance.test.msg.send.util.enums;

/**
 * 描述：
 * <p>
 * #
 * </p>
 * User: guanhc Date: 2017/7/4 ProjectName:test Version:
 */
public enum TestBizCode {
    BIZ_CODE_500001("BIZ_CODE_500001","DB操作异常"),

    BIZ_CODE_200001("BIZ_CODE_200001","业务操作成功"),

    BIZ_CODE_400002("BIZ_CODE_400002","参数有误"),
    BIZ_CODE_400003("BIZ_CODE_400003", "用户名不能为空"),
    BIZ_CODE_400004("BIZ_CODE_400004", "用户名已被注册"),
    BIZ_CODE_400005("BIZ_CODE_400005", "用户不存在"),
    BIZ_CODE_400006("BIZ_CODE_400006", "用户名或密码错误");




    private String bizCode;
    private String bizMsg;

    TestBizCode(String bizCode, String bizMsg) {
        this.bizCode = bizCode;
        this.bizMsg = bizMsg;
    }

    public String getBizCode() {
        return bizCode;
    }

    public void setBizCode(String bizCode) {
        this.bizCode = bizCode;
    }

    public String getBizMsg() {
        return bizMsg;
    }

    public void setBizMsg(String bizMsg) {
        this.bizMsg = bizMsg;
    }

}
