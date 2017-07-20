/**
 * Copyright (c) 2005-2012 https://github.com/zhangkaitao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.finance.test.msg.send.captcha;

import com.octo.captcha.engine.CaptchaEngine;
import com.octo.captcha.service.captchastore.CaptchaStore;
import com.octo.captcha.service.image.DefaultManageableImageCaptchaService;

/**
 * 描述：检验验证码是否在库里存在
 * <p>
 * #
 * </p>
 * User:zhangwen Date: 2016/7/7 ProjectName:bmcp Version:
 */
public class ManageableImageCaptchaService extends DefaultManageableImageCaptchaService {

    public ManageableImageCaptchaService(CaptchaStore captchaStore, CaptchaEngine captchaEngine,
                                           int minGuarantedStorageDelayInSeconds, int maxCaptchaStoreSize,
                                           int captchaStoreLoadBeforeGarbageCollection) {
        super(captchaStore, captchaEngine, minGuarantedStorageDelayInSeconds, maxCaptchaStoreSize,
                captchaStoreLoadBeforeGarbageCollection);
    }

    public boolean hasCaptcha(String id, String userCaptchaResponse) {
        return store.getCaptcha(id).validateResponse(userCaptchaResponse);
    }
}
