package okhttp3;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.junit.Test;

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
            .readTimeout(6L, TimeUnit.SECONDS)
            .connectTimeout(6L, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build();

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
