package com.finance.test.msg.send.controller.web.model;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 描述：
 * <p>
 * #
 * </p>
 * User: guanhc Date: 2017/7/7 ProjectName:test Version:
 */
@Data
public class ResponseModel implements Serializable{

    private static final long serialVersionUID = -4865406348102195169L;

    private String resStatus;
    private String resMsg;
    private List<Object> result = new ArrayList<Object>();
}
