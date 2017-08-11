package com.guanhc;

import com.finance.test.msg.send.facade.Request.DozerTestDto;
import com.finance.test.msg.send.facade.Request.UserReqDto;
import org.dozer.Mapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 描述：
 * <p>
 * #
 * </p>
 * User: guanhc Date: 2017/8/10 ProjectName:test Version:
 */
public class DozerTest extends BaseTest {
    @Autowired
    private Mapper mapper;

    /**
     * 两对象内的字段数量，名称不同
     * 做dozer转换，在dozer_config.xml配置具体字段转换
     */
    @Test
    public void testDozer(){
        DozerTestDto dozerTestDto = new DozerTestDto();
        dozerTestDto.setName("ss");
        dozerTestDto.setPass("ss");
        UserReqDto userReqDto = mapper.map(dozerTestDto, UserReqDto.class);
        System.out.println(userReqDto);
    }
}
