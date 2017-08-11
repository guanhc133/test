package com.finance.test.msg.send.facade.Request;

import lombok.Data;

import java.io.Serializable;

/**
 * 描述：
 * <p>
 * #
 * </p>
 * User: guanhc Date: 2017/8/10 ProjectName:test Version:
 */
@Data
public class DozerTestDto implements Serializable{
    private static final long serialVersionUID = 8180489629941792580L;
    private String name;
    private String pass;

}
