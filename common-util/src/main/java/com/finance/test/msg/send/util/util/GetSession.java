package com.finance.test.msg.send.util.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 描述：
 * <p>
 * #
 * </p>
 * User: guanhc Date: 2017/7/18 ProjectName:test Version:
 */
@Slf4j
@Component
public class GetSession {
    public static String getSession(){
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String captcha = (String) req.getSession().getAttribute("rand");
        log.info("call GetSession.getSession.captcha:{}", captcha);
        return captcha;
    }
}
