package okhttp3;

import com.github.rholder.retry.Retryer;
import com.github.rholder.retry.RetryerBuilder;
import com.github.rholder.retry.StopStrategies;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.junit.Test;

import java.util.Objects;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by jinpeng.fan
 * Date: 2021/9/27
 * Time: 19:53
 * Project: daily_test
 * Package: okhttp3
 *
 * @author jinpeng.fan
 */
public class HttpClientTest {

    private final OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(2L, TimeUnit.SECONDS)
            .retryOnConnectionFailure(false)
            .build();

    private final Retryer<Boolean> retryer = RetryerBuilder.<Boolean>newBuilder()
            .retryIfException()
            .retryIfResult(aBoolean -> Objects.equals(aBoolean, false))
            .withStopStrategy(StopStrategies.stopAfterAttempt(3))
//            .withAttemptTimeLimiter(AttemptTimeLimiters.fixedTimeLimit(2L, TimeUnit.SECONDS))
            .build();

    @Test
    public void retry() {
        Callable<Boolean> callable = () -> {
            Request request = new Request.Builder()
                    .url("http://10.0.0.0:8080")
//                    .url("https://www.baidu.com")
                    .get()
                    .build();
            Call call = okHttpClient.newCall(request);
            try (Response response = call.execute()) {
                return response.isSuccessful();
            } catch (Exception e) {
                return false;
            }
        };
        long start = System.currentTimeMillis();
        try {
            retryer.call(callable);
            System.out.println("ok");
        } catch (Exception e) {
            System.out.println("failed");
            e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis() - start);
    }

    @Test
    public void batchRetry() {
        Callable<Boolean> callable = () -> {
            Request request = new Request.Builder()
                    .url("https://www.baidu.com")
                    .get()
                    .build();
            Call call = okHttpClient.newCall(request);
            try (Response response = call.execute()) {
                return response.isSuccessful();
            } catch (Exception e) {
                return false;
            }
        };
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("demo-pool").build();
        ExecutorService pool = new ThreadPoolExecutor(
                200,
                500,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1000),
                threadFactory,
                new ThreadPoolExecutor.AbortPolicy()
        );
        AtomicInteger failed = new AtomicInteger(0);
        long start = System.currentTimeMillis();
        CountDownLatch countDownLatch = new CountDownLatch(1000);
        for (int i = 0; i < 1000; i++) {
            pool.execute(() -> {
                try {
                    retryer.call(callable);
                    System.out.println("ok");
                } catch (Exception e) {
                    System.out.println("failed");
                    failed.getAndIncrement();
                }
                countDownLatch.countDown();
            });
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis() - start);
        System.out.println(failed.get());
    }

    @Test
    public void okhttp() {
        String api = "http://10.93.240.8:8080/healthcheck.html";
        String api2 = "http://10.0.0.0:8080";
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("demo-pool").build();
        ExecutorService pool = new ThreadPoolExecutor(
                100,
                200,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1000),
                threadFactory,
                new ThreadPoolExecutor.AbortPolicy()
        );
        AtomicInteger failed = new AtomicInteger(0);
        long start = System.currentTimeMillis();
        CountDownLatch countDownLatch = new CountDownLatch(1000);
        for (int i = 0; i < 1000; i++) {
            pool.execute(() -> {
                Request request = new Request.Builder()
                        .url(api)
                        .get()
                        .build();
                Call call = okHttpClient.newCall(request);
                try (Response response = call.execute()) {
                    if (response.isSuccessful()) {
                        System.out.println("ok");
                    }
                } catch (Exception e) {
                    failed.getAndIncrement();
                    System.out.println("failed");
                }
                countDownLatch.countDown();
            });
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis() - start);
        System.out.println(failed.get());
    }

    @Test
    public void once() {
        String api = "http://10.93.240.8:8080/healthcheck.html";
        String api2 = "http://10.0.0.0:8080";
        Request request = new Request.Builder()
                .url(api2)
                .get()
                .build();
        Call call = okHttpClient.newCall(request);
        long start = System.currentTimeMillis();
        try (Response response = call.execute()) {
            if (response.isSuccessful()) {
                System.out.println("ok");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("failed");
        }
        System.out.println(System.currentTimeMillis() - start);
    }
}
