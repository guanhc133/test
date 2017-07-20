package com.finance.test.msg.send.service;

import com.finance.test.msg.send.facade.response.UserRespDto;
import com.finance.test.msg.send.manager.CaptchaValidateManager;
import com.finance.test.msg.send.util.enums.TestBizCode;
import com.finance.test.msg.send.util.exception.ServiceException;
import com.finance.test.msg.send.util.model.Response;
import com.finance.test.msg.send.util.util.GetSession;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 描述：
 * <p>
 * #
 * </p>
 * User: guanhc Date: 2017/7/18 ProjectName:test Version:
 */
@Service
@Slf4j
public class CaptchaValidateService {

    @Autowired
    private CaptchaValidateManager captchaValidateManager;

    /**
     * 验证码校验
     *
     * @param captcha
     * @return
     */
    public Response<String> captchaValidate(String captcha) {
        log.info("call CaptchaValidateService.captchaValidate,captcha:{}",   captcha);
        Response<String> resp = null;
        String sessionCap = GetSession.getSession();
        if (null != sessionCap) {
            if (StringUtils.equals(captcha, sessionCap)) {
                return new Response<String>("校验成功");
            }
        }
        return new Response<String>("校验失败", Boolean.FALSE);
    }

}
