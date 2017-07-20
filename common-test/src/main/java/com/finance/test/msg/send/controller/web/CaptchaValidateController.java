package com.finance.test.msg.send.controller.web;

import com.finance.test.msg.send.service.CaptchaValidateService;
import com.finance.test.msg.send.util.model.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述：
 * <p>
 * #
 * </p>
 * User: guanhc Date: 2017/7/18 ProjectName:test Version:
 */
@RestController
@Slf4j
@RequestMapping(value = "captchaValidate/")
public class CaptchaValidateController extends AbstractController {

    @Autowired
    private CaptchaValidateService captchaValidateService;

    @RequestMapping("captchaValidate")
    public Response<String> captchaValidate(String captcha) {
        log.info("call CaptchaValidateController.captchaValidate,captcha:{}", captcha);
        Response<String> resp = null;
        resp = captchaValidateService.captchaValidate(captcha);
        return resp;
    }

}
