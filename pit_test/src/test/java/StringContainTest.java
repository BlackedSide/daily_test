import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author jinpeng.fan
 * @date 2022/11/23 7:43 PM
 * description
 */
public class StringContainTest {

    @Test
    public void test1() {
        List<String> list = Lists.newArrayList();
        for (int i = 0; i < 1000000; i++) {
            list.add("aa");
        }
        list.add("hh");
        String target = "hh";
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            for (String val : list) {
                if (StringUtils.equals(target, val)) {
                    String result = target;
                }
            }
        }
        System.out.println(System.currentTimeMillis() - start);
        StringUtils.split("a and b", "and");
    }

    @Test
    public void test2() {
        List<String> list = Lists.newArrayList();
        for (int i = 0; i < 1000000; i++) {
            list.add("aa");
        }
        list.add("hh");
        String listJoin = "," + StringUtils.join(list, ",") + ",";
        String target = ",hh,";
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            if (listJoin.contains(target)) {
                String result = target;
            }
        }
        System.out.println(System.currentTimeMillis() - start);
    }

    @Test
    public void test3() {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime time = LocalDateTime.parse("2022-12-05 21:26:00.0");
            System.out.println(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test4() {
        String val = "{\"hotelContactPersonTel\":\"[{\\\"name\\\":\\\"酒店'总机\\\",\\\"tel\\\":\\\"86-10-4008778899\\\"}]\"}";
        val = StringUtils.replace(val, "'", "\\'");
        System.out.println(val);
    }
}
