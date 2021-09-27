package okhttp3;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
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
    private final AtomicInteger total = new AtomicInteger(0);

    private final OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addInterceptor(chain -> {
                Request request = chain.request();
                Response response = chain.proceed(request);
                int tryCount = 0;
                while (!response.isSuccessful() && tryCount < 3) {
                    total.getAndIncrement();
                    tryCount++;
                    response = chain.proceed(request);
                }
                return response;
            })
            .build();

    @Test
    public void okhttp() throws Exception {
        String api = "http://10.93.240.8:8080/healthcheck.html";
        String api2 = "http://10.0.0.0:8080";
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build();
        ExecutorService pool = new ThreadPoolExecutor(
                5,
                200,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1024),
                threadFactory,
                new ThreadPoolExecutor.AbortPolicy()
        );
        for (int i = 0; i < 1000; i++) {
            Request request = new Request.Builder()
                    .url(api2)
                    .get()
                    .build();
            Call call = okHttpClient.newCall(request);
            if (call.execute().isSuccessful()) {
                System.out.println("ok");
                ;
            } else {
                for (int j = 0; j < 3; j++) {
                    if (call.execute().isSuccessful()) {
                        System.out.println("ok");
                    }
                    Thread.sleep(2000);
                }
                System.out.println("failed");
            }
        }
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
        try {
            if (call.execute().isSuccessful()) {
                System.out.println("ok");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("failed");
        }
        System.out.println(System.currentTimeMillis() - start);
        System.out.println(total);
    }
}
