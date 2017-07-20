package com.finance.test.msg.send.util.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 描述：
 * <p>
 * #
 * </p>
 * User: guanhc Date: 2017/7/7 ProjectName:test Version:
 */
@Data
public class Response<T> implements Serializable {
    private static final long serialVersionUID = -6873858618566238297L;

    private T result;//调用返回结果
    private boolean success = false;
    private String responseCode;//响应码
    private String responseMsg;//响应信息

    public Response(){

    }

    public Response(T result) {
        this.success = true;
        this.result = result;
    }

    public Response(T result,boolean boo) {
        this.success = boo;
        this.result = result;
    }

    public Response(String responseCode,String responseMsg){
        this.responseCode = responseCode;
        this.responseMsg = responseMsg;
    }

    public Response(T result,String responseCode,String responseMsg){
        this.success = true;
        this.result = result;
        this.responseCode = responseCode;
        this.responseMsg = responseMsg;
    }

}
