package math;

import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import org.junit.Test;

import java.util.List;

/**
 * AUTHOR fanny
 * DATE 2022/3/7 11:19 AM
 * DESCRIPTION
 */
public class PercentTest {

    @Test
    public void test() {
        System.out.println(percentStr2Int("70%"));
        System.out.println(percentStr2Int("70.2%"));
        System.out.println(percentStr2Int("0.0%"));
        System.out.println(percentStr2Int("0"));
        System.out.println(percentStr2Int(".1%"));
    }

    public static int percentStr2Int(String percentStr) {
        if (Strings.isNullOrEmpty(percentStr)) {
            return 0;
        }
        List<String> result = Splitter.on(".").splitToList(percentStr);
        String value = result.get(0);
        if (value.endsWith("%")) {
            value = Splitter.on("%").splitToList(value).get(0);
        }
        return Integer.parseInt("".equals(value) ? "0" : value);
    }
}
