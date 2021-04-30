package org.example;

import org.example.design.observer.service.impl.LotteryServiceImpl;
import org.junit.Test;

public class ObserverTest {

    @Test
    public void testObserver() {
        LotteryServiceImpl service = new LotteryServiceImpl();
        String draw = service.draw("111111111");
        System.out.println(draw);
    }
}
