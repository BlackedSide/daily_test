package okhttp3;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by jinpeng.fan
 * Date: 2021/9/27
 * Time: 20:46
 * Project: daily_test
 * Package: okhttp3
 *
 * @author jinpeng.fan
 */
public class HttpClient {
    private final AtomicInteger total = new AtomicInteger(0);

    private final OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addInterceptor(chain -> {
                Request request = chain.request();
                Response response = chain.proceed(request);
                try {
                    int tryCount = 0;
                    while (!response.isSuccessful() && tryCount < 3) {
                        total.getAndIncrement();
                        tryCount++;
                        response = chain.proceed(request);
                    }
                    return response;
                } finally {
                    response.close();
                }
            })
            .build();

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
        System.out.println(total);
    }

    public static void main(String[] args) {
        HttpClient httpClient = new HttpClient();
        httpClient.once();
    }
}
