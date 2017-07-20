package com.finance.test.msg.send.util.util;

import java.util.Date;
import java.util.UUID;

/**
 * 描述：
 * <p>
 * #
 * </p>
 * User: guanhc Date: 2017/7/10 ProjectName:test Version:
 */
public class GeneratorUtil {

    /**
     * 随机生成Token
     */
    public static String generatorToken() {
        //截取6位uuid
        String uuid = UUID.randomUUID().toString().replaceAll("_", "").substring(0,6);
        String token = uuid + new Date().getTime();
        return token;
    }
}
