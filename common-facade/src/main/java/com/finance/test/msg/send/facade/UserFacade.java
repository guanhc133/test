package com.finance.test.msg.send.facade;

import com.finance.test.msg.send.facade.Request.UserReqDto;
import com.finance.test.msg.send.facade.response.UserRespDto;
import com.finance.test.msg.send.util.model.Response;
import com.github.pagehelper.PageInfo;

/**
 * 描述：
 * <p>
 * #
 * </p>
 * User: guanhc Date: 2017/7/7 ProjectName:test Version:
 */
public interface UserFacade {
    Response<UserRespDto> queryUserInfo(String userName);

    Response<UserRespDto> regist(UserReqDto userReqDto);

    PageInfo<UserRespDto> queryAll(int pageNo,int pageSize);
}
