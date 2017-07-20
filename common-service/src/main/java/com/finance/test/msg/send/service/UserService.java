package com.finance.test.msg.send.service;

import com.finance.test.msg.send.facade.Request.UserReqDto;
import com.finance.test.msg.send.facade.UserFacade;
import com.finance.test.msg.send.facade.response.UserRespDto;
import com.finance.test.msg.send.manager.UserManager;
import com.finance.test.msg.send.model.UserInfo;
import com.finance.test.msg.send.model.UserInfoExample;
import com.finance.test.msg.send.util.enums.TestBizCode;
import com.finance.test.msg.send.util.exception.ServiceException;
import com.finance.test.msg.send.util.model.Response;
import lombok.extern.slf4j.Slf4j;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * 描述：
 * <p>
 * #
 * </p>
 * User: guanhc Date: 2017/7/7 ProjectName:test Version:
 */
@Service
@Slf4j
public class UserService implements UserFacade {

    @Autowired
    private UserManager userManager;
    @Autowired
    private Mapper dozerMapper;

    @Override
    public Response<UserRespDto> queryUserInfo(String userName) {
        log.info("call UserService.queryUserInfo,userName:{}", userName);
        UserRespDto userRespDto = new UserRespDto();
        Response<UserRespDto> userRespDtoResponse = new Response<UserRespDto>();
        try {
            if (StringUtils.isEmpty(userName)) {
                throw new ServiceException(TestBizCode.BIZ_CODE_400003.getBizCode(), TestBizCode.BIZ_CODE_400003.getBizMsg());
            }
            UserInfoExample example = new UserInfoExample();
            example.createCriteria().andUserNameEqualTo(userName);
            List<UserInfo> userInfo = userManager.queryUserInfo(example);
            if (null != userInfo && userInfo.size() > 0) {
                userRespDto.setUserName(userInfo.get(0).getUserName());
                userRespDtoResponse.setResult(userRespDto);
            } else {
                throw new ServiceException(TestBizCode.BIZ_CODE_400005.getBizCode(), TestBizCode.BIZ_CODE_400005.getBizMsg());
            }
        } catch (ServiceException se) {
            log.info("call UserService.queryUserInfo,se:{}", se);
            return new Response<UserRespDto>(se.getBizCode(), se.getBizMsg());
        } catch (Exception e) {
            log.info("call UserService.queryUserInfo,e:{}", e);
            return new Response<UserRespDto>(TestBizCode.BIZ_CODE_500001.getBizCode(), TestBizCode.BIZ_CODE_500001.getBizMsg());
        }
        return userRespDtoResponse;
    }

    @Override
    public Response<UserRespDto> regist(UserReqDto userReqDto) {
        log.info("call UserService.queryUserInfo,userReqDto:{}", userReqDto);
        Response<UserRespDto> resp = new Response<UserRespDto>();
        UserRespDto userRespDto = new UserRespDto();
        try {
            UserInfo userInfo = (UserInfo) dozerMapper.map(userReqDto, UserInfo.class);
            UserInfoExample example = new UserInfoExample();
            example.createCriteria().andUserNameEqualTo(userInfo.getUserName());
            List<UserInfo> userList = userManager.queryUserInfo(example);
            if (null == userList && userList.size() == 0) {
                userManager.regist(userInfo);
            } else {
                throw new ServiceException(TestBizCode.BIZ_CODE_400004.getBizCode(), TestBizCode.BIZ_CODE_400004.getBizMsg());
            }
            //组装返回参数
            userRespDto.setUserName(userInfo.getUserName());
            resp.setResult(userRespDto);
        } catch (ServiceException se) {
            log.error("call UserService.regist,se:{}", se);
            return new Response<UserRespDto>(se.getBizCode(), se.getBizMsg());
        } catch (Exception e) {
            log.info("call UserService.regist,e:{}", e);
            return new Response<UserRespDto>(TestBizCode.BIZ_CODE_500001.getBizCode(), TestBizCode.BIZ_CODE_500001.getBizMsg());
        }
        return resp;
    }

    /**
     * 更新用户
     *
     * @param userReqDto
     * @return
     */
    public Response<String> updateUserInfo(UserReqDto userReqDto) {
        log.info("call UserService.queryUserInfo,userReqDto:{}", userReqDto);
        try {
            if (StringUtils.isNotEmpty(userReqDto.getUserName())) {
                UserInfoExample example = new UserInfoExample();
                example.createCriteria().andUserNameEqualTo(userReqDto.getUserName());
                List<UserInfo> userInfoList = userManager.queryUserInfo(example);
                if (null == userInfoList && userInfoList.size() == 0) {
                    throw new ServiceException(TestBizCode.BIZ_CODE_400005.getBizCode(), TestBizCode.BIZ_CODE_400005.getBizMsg());
                }
                UserInfo userInfo = dozerMapper.map(userReqDto, UserInfo.class);
                userManager.updateUserInfo(userInfo);
            } else {
                throw new ServiceException(TestBizCode.BIZ_CODE_400003.getBizCode(), TestBizCode.BIZ_CODE_400005.getBizMsg());
            }
        } catch (ServiceException se) {
            log.error("call UserService.updateUserInfo,se:{}", se);
            return new Response<String>(se.getBizCode(), se.getBizMsg());
        } catch (Exception e) {
            log.error("call UserService.updateUserInfo,e:{}", e);
            return new Response<String>(TestBizCode.BIZ_CODE_500001.getBizCode(), TestBizCode.BIZ_CODE_500001.getBizMsg());
        }
        return new Response<String>("更新成功");
    }

    /**
     * 校验密码
     *
     * @param pass
     * @return
     */
    public Response<String> checkPass(String pass) {
        log.info("call UserService.checkPass,pass:{}", pass);
//        try {

//        } catch (ServiceException se) {
//            log.error("call UserService.checkPass,se:{}", se);
//            return new Response<String>(se.getBizCode(), se.getBizMsg());
//        } catch (Exception e) {
//            log.error("call UserService.checkPass,e:{}", e);
//            return new Response<String>(TestBizCode.BIZ_CODE_500001.getBizCode(), TestBizCode.BIZ_CODE_500001.getBizMsg());
//        }
        return null;
    }

    /**
     * 校验用户名密码是否有效
     *
     * @param userName
     * @param password
     * @return
     */
    public Response<UserRespDto> queryUserIsExist(String userName, String password) {
        log.info("call UserService.queryUserInfo,userName:{},password:{}", userName, password);
        UserRespDto userRespDto = null;
        Response<UserRespDto> resp = null;
        try {
            UserInfoExample example = new UserInfoExample();
            example.createCriteria().andUserNameEqualTo(userName).andPasswordEqualTo(password);
            List<UserInfo> userList = userManager.queryUserInfo(example);
            if (null != userList && userList.size() > 0) {
                userRespDto = dozerMapper.map(userList.get(0), UserRespDto.class);
                resp.setResult(userRespDto);
            } else {
                throw new ServiceException(TestBizCode.BIZ_CODE_400006.getBizCode(), TestBizCode.BIZ_CODE_400006.getBizMsg());
            }
        } catch (ServiceException se) {
            log.error("call UserService.updateUserInfo,se:{}", se);
            return new Response<UserRespDto>(Boolean.FALSE, se.getBizCode(), se.getBizMsg());
        } catch (Exception e) {
            log.error("call UserService.updateUserInfo,e:{}", e);
            return new Response<UserRespDto>(Boolean.FALSE, TestBizCode.BIZ_CODE_500001.getBizCode(), TestBizCode.BIZ_CODE_500001.getBizMsg());
        }
        return resp;
    }
}
