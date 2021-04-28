package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class FutureTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //共50线程，每个线程阻塞5S，其中第二个线程8秒，同时并发50线程，总耗时两个case差不多，但是出数据快慢有区别
        /**
         * <一>
         * 1. 用List收集任务结果 (List记录每个submit返回的Future)
         * 2. 循环查看结果, Future不一定完成, 如果没有完成, 那么调用get会租塞
         * 3. 如果排在前面的任务没有完成, 那么就会阻塞, 这样后面已经完成的任务就没法获得结果了, 导致了不必要的等待时间.
         *    更为严重的是: 第一个任务如果几个小时或永远完成不了, 而后面的任务几秒钟就完成了, 那么后面的任务的结果都将得不到处理
         *
         * 导致: 已完成的任务可能得不到及时处理
         */
//        case1();
        //跟case1区别为 case1直到当前线程取到值否则一直阻塞等待
        //case2处理较快的线程先取值，慢的放最后，用户体验上好些，比如加载页面，用case1可能先加载2条数据出来，剩下的48条阻塞一会才出来
        //用case2的话，49条会提前加载出来，剩下的一个慢的最后加载
        /**
         * <三> 使用ExecutorCompletionService管理异步任务
         * 1. Java中的ExecutorCompletionService<V>本身有管理任务队列的功能
         *    i. ExecutorCompletionService内部维护列一个队列, 用于管理已完成的任务
         *    ii. 内部还维护列一个Executor, 可以执行任务
         *
         * 2. ExecutorCompletionService内部维护了一个BlockingQueue, 只有完成的任务才被加入到队列中
         *
         * 3. 任务一完成就加入到内置管理队列中, 如果队列中的数据为空时, 调用take()就会阻塞 (等待任务完成)
         *    i. 关于完成任务是如何加入到完成队列中的, 请参考ExecutorCompletionService的内部类QueueingFuture的done()方法
         *
         * 4. ExecutorCompletionService的take/poll方法是对BlockingQueue对应的方法的封装, 关于BlockingQueue的take/poll方法:
         *    i. take()方法, 如果队列中有数据, 就返回数据, 否则就一直阻塞;
         *    ii. poll()方法: 如果有值就返回, 否则返回null
         *    iii. poll(long timeout, TimeUnit unit)方法: 如果有值就返回, 否则等待指定的时间; 如果时间到了如果有值, 就返回值, 否则返回null
         *
         * 解决了已完成任务得不到及时处理的问题
         */
        case2();
    }

    private static void case1() throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(50);
        List<Future<String>> result = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            int finalI = i;
            result.add(service.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    if (finalI == 2) {
                        Thread.sleep(8000);
                    } else {
                        Thread.sleep(5000);
                    }
                    return "11";
                }
            }));
        }
        int count = 0;
        long l = System.currentTimeMillis();
        System.out.println("处理开始，时间："+l);
        for (Future<String> res : result) {
            System.out.println(res.get());
            count++;
        }
        System.out.println("处理结束,时间：" + (System.currentTimeMillis() - l));
        System.out.println("总数："+ count);
        service.shutdown();
    }

    private static void case2() throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(50);
        ExecutorCompletionService<String> completionService = new ExecutorCompletionService<String>(service);
        List<Future<String>> result = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            int finalI = i;
            completionService.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    if (finalI == 2) {
                        Thread.sleep(8000);
                    } else {
                        Thread.sleep(5000);
                    }
                    return "11";
                }
            });
        }
        int count = 0;
        long l = System.currentTimeMillis();
        System.out.println("处理开始，时间："+l);
        while (count < 50) {
            Future<String> take = completionService.take();
            String s = take.get();
            //开始业务处理
            System.out.println("返回结果：" + s);
            count++;
        }
        System.out.println("处理结束,时间：" + (System.currentTimeMillis() - l));
        System.out.println("总数："+ count);
        service.shutdown();
    }

}
