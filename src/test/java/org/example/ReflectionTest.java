package org.example;

import org.example.reflection.BankService;
import org.example.reflection.CreatOrderApi;
import org.junit.Test;

/**
 * 注解，反射测试
 */
public class ReflectionTest extends AppTest {

    @Test
    public void testbankApi() {
        CreatOrderApi api = new CreatOrderApi();
        api.setOrderId(111);
        api.setSerialNum("908776555");
        BankService bankService = new BankService();
        System.out.println(bankService.sendRequest(api));
    }

}
