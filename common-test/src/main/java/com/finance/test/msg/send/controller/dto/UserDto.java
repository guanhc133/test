package com.finance.test.msg.send.controller.dto;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 描述：
 * <p>
 * #
 * </p>
 * User: guanhc Date: 2017/6/7 ProjectName:test Version:
 */
@Data
public class UserDto implements Serializable{
    private static final long serialVersionUID = -8059598150508630963L;
    private String userName;
    private String password;
    private String email;
}
