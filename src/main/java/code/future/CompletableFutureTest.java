package code.future;

import code.future.Operate.LabelService;
import code.future.Operate.OrderService;
import code.future.Operate.WatchRecordService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;

import static java.util.stream.Collectors.toList;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CompletableFutureTest {
    @Test
    public void testSync() {
        long start = System.currentTimeMillis();
        List<RemoteLoader> remoteLoaders = Arrays.asList(new CustomerInfoService(), new LearnRecordService());
        List<String> customerDetail = remoteLoaders.stream().map(RemoteLoader::load).collect(toList());
        System.out.println(customerDetail);
        long end = System.currentTimeMillis();
        System.out.println("总共花费时间:" + (end - start));
    }

    @Test
    public void testFuture() {
        long start = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        List<RemoteLoader> remoteLoaders = Arrays.asList(new CustomerInfoService(), new LearnRecordService());
        List<Future<String>> futures = remoteLoaders.stream()
                .map(remoteLoader -> executorService.submit(remoteLoader::load))
                .collect(toList());

        List<String> customerDetail = futures.stream()
                .map(future -> {
                    try {
                        return future.get();
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(toList());
        System.out.println(customerDetail);
        long end = System.currentTimeMillis();
        System.out.println("总共花费时间:" + (end - start));
    }

    @Test
    public void testParallelStream() {
        long start = System.currentTimeMillis();
        List<RemoteLoader> remoteLoaders = Arrays.asList(new CustomerInfoService(), new LearnRecordService());
        List<String> customerDetail = remoteLoaders.parallelStream().map(RemoteLoader::load).collect(toList());
        System.out.println(customerDetail);
        long end = System.currentTimeMillis();
        System.out.println("总共花费时间:" + (end - start));
    }

    @Test
    public void testParallelStream2() {
        long start = System.currentTimeMillis();
        List<RemoteLoader> remoteLoaders = Arrays.asList(
                new CustomerInfoService(),
                new LearnRecordService(),
                new LabelService(),
                new OrderService(),
                new WatchRecordService());
        List<String> customerDetail = remoteLoaders.parallelStream().map(RemoteLoader::load).collect(toList());
        System.out.println(customerDetail);
        long end = System.currentTimeMillis();
        System.out.println("总共花费时间:" + (end - start));
    }
    /*   @Test
    public void testCompletableFuture() {
        CompletableFuture<String> future = new CompletableFuture<>();
        new Thread(() -> {
            doSomething();
            future.complete("Finish");          //任务执行完成后 设置返回的结果
        }).start();
        System.out.println(future.join());      //获取任务线程返回的结果
    }

    private void doSomething() {
        System.out.println("doSomething...");
    }*/

    @Test
    public void testCompletableFuture() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = new CompletableFuture<>();
        new Thread(() -> {
            try {
                doSomething();
                future.complete("Finish");
            } catch (Exception e) {
                future.completeExceptionally(e);
            }
        }).start();
        System.out.println(future.get());
    }

    private void doSomething() {
        System.out.println("doSomething...");
        throw new RuntimeException("Test Exception");
    }
    @Test
    public void testCompletableFuture2() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture
                .supplyAsync(() -> {
                    doSomething();
                    return "Finish";
                })
                .exceptionally(throwable -> "Throwable exception message:" + throwable.getMessage());
        System.out.println(future.get());
    }
    @Test
    public void testCompletableFuture3() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        List<RemoteLoader> remoteLoaders = Arrays.asList(
                new CustomerInfoService(),
                new LearnRecordService(),
                new LabelService(),
                new OrderService(),
                new WatchRecordService());
        List<CompletableFuture<String>> completableFutures = remoteLoaders
                .stream()
                .map(loader -> CompletableFuture.supplyAsync(loader::load))
                .collect(toList());

        List<String> customerDetail = completableFutures
                .stream()
                .map(CompletableFuture::join)
                .collect(toList());

        System.out.println(customerDetail);
        long end = System.currentTimeMillis();
        System.out.println("总共花费时间:" + (end - start));
    }
    @Test
    public void testCompletableFuture4() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        List<RemoteLoader> remoteLoaders = Arrays.asList(
                new CustomerInfoService(),
                new LearnRecordService(),
                new LabelService(),
                new OrderService(),
                new WatchRecordService());

        ExecutorService executorService = Executors.newFixedThreadPool(Math.min(remoteLoaders.size(), 50));

        List<CompletableFuture<String>> completableFutures = remoteLoaders
                .stream()
                .map(loader -> CompletableFuture.supplyAsync(loader::load, executorService))
                .collect(toList());

        List<String> customerDetail = completableFutures
                .stream()
                .map(CompletableFuture::join)
                .collect(toList());

        System.out.println(customerDetail);
        long end = System.currentTimeMillis();
        System.out.println("总共花费时间:" + (end - start));
    }
}
