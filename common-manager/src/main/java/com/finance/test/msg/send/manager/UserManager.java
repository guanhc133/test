package com.finance.test.msg.send.manager;

import com.finance.test.msg.send.mapper.UserInfoMapper;
import com.finance.test.msg.send.model.UserInfo;
import com.finance.test.msg.send.model.UserInfoExample;
import com.finance.test.msg.send.util.enums.TestBizCode;
import com.finance.test.msg.send.util.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 描述：dal操作
 * <p>
 * #
 * </p>
 * User: guanhc Date: 2017/7/4 ProjectName:test Version:
 */
@Slf4j
@Component
public class UserManager {

    @Autowired
    private UserInfoMapper userInfoMapper;

    public List<UserInfo> queryUserInfo(UserInfoExample example) {
        log.info("call UserLoginManager.queryUserIfo,example=【{}】", example);
        List<UserInfo> userInfo = null;

        try {
            userInfo = userInfoMapper.selectByExample(example);
        } catch (Exception e) {
            log.error("call CustomerOperatorManager.getTCustomerOperator err,e={}", e);
            throw new ServiceException(TestBizCode.BIZ_CODE_500001);
        }
        return userInfo;
    }

    /**
     * 增加用户
     *
     * @param userInfo
     * @return
     */
    public int regist(UserInfo userInfo) {
        log.info("call UserLoginManager.registUser,userInfo=【{}】", userInfo);
        try {
            return userInfoMapper.insert(userInfo);
        } catch (Exception e) {
            log.error("call CustomerOperatorManager.registUser err,e={}", e);
            throw new ServiceException(TestBizCode.BIZ_CODE_500001);
        }
    }


    /**
     * 更新用户信息
     *
     * @param userInfo
     * @return
     */
    public int updateUserInfo(UserInfo userInfo) {
        log.info("call UserLoginManager.updateUserInfo,userInfo=【{}】", userInfo);
        try {
            return userInfoMapper.updateUserInfo(userInfo);
        } catch (Exception e) {
            log.error("call CustomerOperatorManager.registUser err,e={}", e);
            throw new ServiceException(TestBizCode.BIZ_CODE_500001);
        }
    }

    /**
     * 查询全部用户
     *
     * @return
     */
    public List<UserInfo> queryAll() {
        try {
            return userInfoMapper.queryAll();
        } catch (Exception e) {
            log.error("call CustomerOperatorManager.registUser err,e={}", e);
            throw new ServiceException(TestBizCode.BIZ_CODE_500001);
        }
    }
}
