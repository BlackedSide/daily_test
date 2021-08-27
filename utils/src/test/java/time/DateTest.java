package time;

import cn.hutool.core.date.DateUtil;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;
import org.springframework.util.StopWatch;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;

public class DateTest {
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");

    private static ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build();

    private static ExecutorService pool = new ThreadPoolExecutor(5, 200, 0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

    private static CountDownLatch countDownLatch = new CountDownLatch(100);

    @Test
    public void simpleDateFormatter() throws InterruptedException {
        Set<String> dates = Collections.synchronizedSet(new HashSet<String>());
        for (int i = 0; i < 100; i++) {
            Calendar calendar = Calendar.getInstance();
            int finalI = i;
            pool.execute(() -> {
                calendar.add(Calendar.DATE, finalI);
                String dateString = simpleDateFormat.format(calendar.getTime());
                dates.add(dateString);
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        System.out.println(dates.size());
    }

    @Test
    public void dateUtils() throws InterruptedException {
        Set<String> dates = Collections.synchronizedSet(new HashSet<>());
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (int i = 0; i < 100; i++) {
            Calendar calendar = Calendar.getInstance();
            int finalI = i;
            pool.execute(() -> {
                calendar.add(Calendar.DATE, finalI);
                String dateString = DateFormatUtils.format(calendar.getTime(), "yy-MM-dd HH:mm:ss");
                dates.add(dateString);
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        stopWatch.stop();
        System.out.println(dates.size() + " cost " + stopWatch.getTotalTimeMillis());
    }

    @Test
    public void newSimpleDateFormat() throws InterruptedException {
        Set<String> dates = Collections.synchronizedSet(new HashSet<>());
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (int i = 0; i < 100; i++) {
            Calendar calendar = Calendar.getInstance();
            int finalI = i;
            pool.execute(() -> {
                SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
                calendar.add(Calendar.DATE, finalI);
                String dateString = format.format(calendar.getTime());
                dates.add(dateString);
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        stopWatch.stop();
        System.out.println(dates.size() + " cost " + stopWatch.getTotalTimeMillis());
    }

    @Test
    public void testDateUtils() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        System.out.println(DateFormatUtils.format(new Date(), "yy-MM-dd HH:mm:ss"));
        try {
            Date date = DateUtils.parseDate("2021-08-20 00:00:00", "yy-MM-dd HH:mm:ss");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeMillis());
    }

    @Test
    public void testSimpleDateFormat() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        System.out.println(new SimpleDateFormat("yy-MM-dd HH:mm:ss").format(new Date()));
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeMillis());
    }

    @Test
    public void hutoolDateUtil() {
        Date nowTime = DateUtil.date();
        Date beginOfDay = DateUtil.beginOfDay(nowTime);
        Date endOfDay = DateUtil.endOfDay(nowTime);
        System.out.println(beginOfDay);
        System.out.println(endOfDay);
        Date yesterday = DateUtil.yesterday();
        System.out.println(yesterday);
        beginOfDay = DateUtil.beginOfDay(yesterday);
        endOfDay = DateUtil.endOfDay(yesterday);
        System.out.println(beginOfDay);
        System.out.println(endOfDay);
    }
}
