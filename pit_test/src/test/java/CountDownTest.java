import org.junit.Test;

import java.util.concurrent.CountDownLatch;

/**
 * Created by jinpeng.fan
 * Date: 2021/9/30
 * Time: 16:29
 * Project: daily_test
 * Package: PACKAGE_NAME
 *
 * @author jinpeng.fan
 */
public class CountDownTest {
    @Test
    public void countDown() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(20);
        for (int i = 0; i < 20; i++) {
            if (i % 2 == 0) {
                countDownLatch.countDown();
                continue;
            }
            new Thread(() -> {
                System.out.println("hallo");
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();
        System.out.println("end");
    }
}
