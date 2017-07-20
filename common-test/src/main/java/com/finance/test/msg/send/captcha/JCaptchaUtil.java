package com.finance.test.msg.send.captcha;

import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.captchastore.FastHashMapCaptchaStore;

import javax.servlet.http.HttpServletRequest;

/**
 * 描述：JCaptcha工具类
 * <p>
 * #
 * </p>
 * User:zhangwen Date: 2016/7/7 ProjectName:bmcp Version:
 */
public class JCaptchaUtil {
    public static final ManageableImageCaptchaService captchaService
            = new ManageableImageCaptchaService(new FastHashMapCaptchaStore(), new GMailEngine(), 180, 100000, 75000);

    //验证当前请求输入的验证码否正确；并从CaptchaService中删除已经生成的验证码；
    public static boolean validateResponse(HttpServletRequest request, String userCaptchaResponse) {
        if (request.getSession(false) == null) {
            return false;
        }

        boolean validated = false;
        try {
            String id = request.getSession().getId();
            userCaptchaResponse = userCaptchaResponse.toLowerCase();
            validated = captchaService.validateResponseForID(id, userCaptchaResponse).booleanValue();
        } catch (CaptchaServiceException e) {
            e.printStackTrace();
        }
        return validated;
    }
    ////验证当前请求输入的验证码否正确；但不会从CaptchaService中删除已经生成的验证码；可用于ajax请求
    public static boolean hasCaptcha(HttpServletRequest request, String userCaptchaResponse) {
        if (request.getSession(false) == null) return false;
        boolean validated = false;
        try {
            String id = request.getSession().getId();
            validated = captchaService.hasCaptcha(id, userCaptchaResponse);
        } catch (CaptchaServiceException e) {
            e.printStackTrace();
        }
        return validated;
    }
}
