package org.example.reflection;

import lombok.Data;

/**
 *  @author: guanhongcheng
 *  @Date: 2021/5/28 9:43
 *  @Description: 模拟创建订单Api请求及参数描述
 */
@Data
@BankApi(apiUrl = "www.baidu.com", desc = "请求创建订单")
public class CreatOrderApi extends AbstractApi {

    @BankField(order = 1,length = 25, type = "String")
    String serialNum;

    @BankField(order = 2,length = 20, type = "Int")
    int orderId;
}
