package time;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeTest {
    @Test
    public void test() {
        LocalDateTime time = LocalDateTime.of(2021, 11, 9, 12, 0, 0);
        time.plusHours(1);
        System.out.println(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(time));
        System.out.println(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(time.plusHours(1)));
    }
}
