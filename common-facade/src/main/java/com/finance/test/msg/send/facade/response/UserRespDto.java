package com.finance.test.msg.send.facade.response;

import lombok.Data;

/**
 * 描述：
 * <p>
 * #
 * </p>
 * User: guanhc Date: 2017/7/7 ProjectName:test Version:
 */
@Data
public class UserRespDto {
    private String userName;
    private String password;
    private String email;
    private String captcha;
    private String rememberMe;
}
