import com.google.common.collect.Lists;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by jinpeng.fan
 * Date: 2021/10/18
 * Time: 11:50
 * Project: daily_test
 * Package: PACKAGE_NAME
 *
 * @author jinpeng.fan
 */
public class TimestampTest {
    @Test
    public void timestamp2Date() {
        long timestamp = 1634271348L;
        Integer timestamp2 = 1634271348;
        Date date = new Date(timestamp2 * 1000L);
        String timing = dateFormat(date);
        System.out.println(timing);
    }

    @Test
    public void datePlus() {
        Date date = new Date();
        System.out.println(new Date(date.getTime() + 3600000));
    }

    @Test
    public void systemTimeFormat() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long nowTime = System.currentTimeMillis();
        long futureTime = nowTime + 8 * 60 * 60000L;
        System.out.println(simpleDateFormat.format(nowTime));
        System.out.println(simpleDateFormat.format(futureTime));
    }

    @Test
    public void speedCompare() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<String> list = Lists.newArrayList();
        long start1 = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            String value = simpleDateFormat.format(System.currentTimeMillis() * 1000L);
            list.add(value);
        }
        System.out.println(System.currentTimeMillis() - start1);
        list.clear();
        long start2 = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            String value = simpleDateFormat.format(new Date());
            list.add(value);
        }
        System.out.println(System.currentTimeMillis() - start2);
        System.out.println(System.getProperty("java.class.path"));
        System.out.println(System.getProperty("user.dir"));
    }

    private String dateFormat(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }
}
