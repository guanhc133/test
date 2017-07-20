package com.finance.test.msg.send.controller.validate;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 描述：
 * <p>
 * #
 * </p>
 * User: guanhc Date: 2017/7/4 ProjectName:test Version:
 */
@Controller
@Slf4j
public class CaptchaValidate {

    @RequestMapping("validate")
    public void validate(String captcha) {
        log.info("call CaptchaValidate.validate captcha:{}", captcha);
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        String captchSession = (String) request.getSession().getAttribute("rand");
        try {
            if (StringUtils.equals(captchSession, captcha)) {
                response.getWriter().println("验证码正确");
            }else {
                response.getWriter().println("验证码错误，请刷新重新输入！");
            }
        } catch (IOException io) {
            log.error("call CaptchaValidate.validate io:{}",io);
        }
    }
}
