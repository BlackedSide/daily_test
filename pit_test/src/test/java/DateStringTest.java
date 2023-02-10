import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author jinpeng.fan
 * @date 2022/11/18 4:37 PM
 * description
 */
public class DateStringTest {

    @Test
    public void test1() {
        long begin = System.currentTimeMillis();
        String date1 = "2022-11-17 09:00:00";
        String date2 = "2022-11-18 10:00:00";
        for (int i = 0; i < 1000000; i++) {
            int res = StringUtils.compare(date1, date2);
        }
        System.out.println(System.currentTimeMillis() - begin);
    }

    @Test
    public void test2() throws ParseException {
        String date1 = "2022-11-17 09:00:00";
        String date2 = "2022-11-18 10:00:00";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long begin = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            boolean res = sdf.parse(date1).getTime() > sdf.parse(date2).getTime();
        }
        System.out.println(System.currentTimeMillis() - begin);
    }
}
