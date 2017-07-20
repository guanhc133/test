package com.finance.test.msg.send.controller.web;

import com.alibaba.fastjson.JSONObject;
import com.finance.test.msg.send.controller.web.model.ResponseModel;
import com.finance.test.msg.send.util.constant.Paramters;
import com.finance.test.msg.send.util.enums.TestBizCode;
import com.finance.test.msg.send.util.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 描述：
 * <p>
 * #
 * </p>
 * User: guanhc Date: 2017/7/7 ProjectName:test Version:
 */
@Slf4j
public abstract class AbstractController {

    protected String responseJson(ServiceException serviceException) {
        ResponseModel model = new ResponseModel();
        model.setResStatus(Paramters.testId + "" + serviceException.getBizMsg());
        model.setResMsg(serviceException.getBizMsg());
        model.setSuccess(Boolean.FALSE);
        return JSONObject.toJSON(model).toString();
    }

    protected String responseJson() {
        ResponseModel model = new ResponseModel();
        model.setResStatus(Paramters.testId + "" + TestBizCode.BIZ_CODE_500001.getBizMsg());
        model.setResMsg(TestBizCode.BIZ_CODE_500001.getBizMsg());
        model.setSuccess(Boolean.FALSE);
        return JSONObject.toJSON(model).toString();
    }

    protected String responseJson(List<Object> list, boolean isSuccess, String testCode, String testMsg) {
        ResponseModel model = new ResponseModel();
        if (isSuccess) {
            model.setResMsg(Paramters.testId + "" + TestBizCode.BIZ_CODE_200001.getBizMsg());
            model.setResult(list);
        } else {
            model.setResMsg(Paramters.testId + "" + testCode);
            model.setResMsg(testMsg);
        }
        return JSONObject.toJSON(model).toString();
    }
}
