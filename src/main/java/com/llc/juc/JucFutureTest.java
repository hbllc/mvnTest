package com.llc.juc;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

/**
 * @author lilichuan
 */
@Slf4j
public class JucFutureTest {

    public static void main(String[] args) throws Exception {

        try {
            test7();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("main Exception:{}", e.getMessage());
        }
    }

    public static void test1() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<String> future = executorService.submit(() -> {
            System.out.println(Thread.currentThread().getName() + ": start1");
            return "world";
        });

        System.out.println(future.get());
        executorService.shutdown();

    }

    public static void test2() throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        CountDownLatch c = new CountDownLatch(2);

        Future<String> submit = executorService.submit(() -> {
            System.out.println(Thread.currentThread().getName() + ": start1");
            c.countDown();
            return "hello";
        });

        Future<String> submit1 = executorService.submit(() -> {
            System.out.println(Thread.currentThread().getName() + ": start2");
            c.countDown();
            return "world";
        });

        System.out.println(submit1.get());
        System.out.println(submit.get());

        c.await();
        executorService.shutdown();


    }

    public static void test3(){

        ExecutorService executor = Executors.newFixedThreadPool(2);
        List<Integer> processInstanceIds = List.of(1,2,3,4);
        
        List<CompletableFuture<DataResp>> futures =
                processInstanceIds.stream().
                        map(item -> {
                            return CompletableFuture.supplyAsync(() -> getDataResp(item));
                        }).collect(Collectors.toList());

        List<DataResp> tasks = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                .thenApply(v -> futures.stream().map(CompletableFuture::join)
                        .filter(Objects::nonNull).collect(Collectors.toList())).join();
        executor.shutdown();
        log.info("结果数据：{}",tasks);

    }

    private static DataResp getDataResp(Integer id){
        log.info("id:{}",id);
        if(id == 3){
            throw new RuntimeException("测试抛出异常");
        }
        log.info("threadName:{},id:{}",Thread.currentThread().getName(),id);
        return new DataResp(id,"name_" + id);

    }

    public static void test4() throws ExecutionException, InterruptedException, TimeoutException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        CompletableFuture<Long> longCompletableFuture = CompletableFuture.supplyAsync(() -> {
            log.info(Thread.currentThread().getName() + " start");
            return 0L;
        }, executorService).thenApply(item -> {
            log.info(Thread.currentThread().getName() + " thenApply start" + item);
            long l = 1 / item;
            return item + 1;
        }).thenApply(item -> {
            log.info(Thread.currentThread().getName() + " thenApply start" + item);
            return item + 1;
        }).exceptionally(e -> {
            log.error("exceptionally:{}", e.getMessage());
            return 0L;
        }).whenComplete((item, e) -> {
            log.info("whenComplete:{}", item);
            log.warn("whenComplete", e);
            long l = 1 / item;
        }).handle((item, e) -> {
            log.info("handle:{}", item);
            long l = 1/ item;
            log.warn("handle", e);
            return item;
        });


        System.out.println(longCompletableFuture.get(100000,TimeUnit.MICROSECONDS));

    }

    public static void test6(){
        CompletableFuture<List<String>> test = test(List.of(1,2));
        CompletableFuture<List<String>> test1 = test(List.of(3,4));

        log.info("main,time:{}",System.currentTimeMillis());
        CompletableFuture<List<String>> listCompletableFuture = test.thenCombine(test1, (item1, item2) -> {
            log.info("main inner start,time:{}",System.currentTimeMillis());
            item1.addAll(item2);
            log.info("main inner end,time:{}",System.currentTimeMillis());
            return item1;
        });
        log.info("main complete start,time:{}",System.currentTimeMillis());
        List<String> join = listCompletableFuture.join();
        log.info("main complete end,time:{}",System.currentTimeMillis());
        join.forEach(System.out::println);


    }

    private static CompletableFuture<List<String>> test(List<Integer> list) {
        log.info("test:{},time:{}",list,System.currentTimeMillis());
         return CompletableFuture.supplyAsync(() -> {
            List<String> s = list.stream().map(String::valueOf).collect(Collectors.toList());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return s;
        });
    }

    public static void test7(){


        CompletableFuture<Object> test1 = CompletableFuture.supplyAsync(() -> {
            log.info("test start111111");
            return 1;
        });
        CompletableFuture<Object> test = CompletableFuture.supplyAsync(() -> {
            log.info("test start");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            log.info("test end");
            throw new NullPointerException("test");
        });
        log.info("complete end");
//        System.out.println(CompletableFuture.allOf(test, test1).join());
        System.out.println(CompletableFuture.anyOf(test, test1).join());


    }


}


