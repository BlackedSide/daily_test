import com.google.common.base.Strings;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class StringsTest {
    @Test
    public void padding() {
        String a = "a";
        String b = "bbb";
        String sa = Strings.padStart(a, 5, '*');
        String sb = Strings.padEnd(b, 5, '#');
        System.out.println(a);
        System.out.println(sa);
        System.out.println(b);
        System.out.println(sb);
    }

    @Test
    public void stringUtils() {
        String a = "true";
        System.out.println(StringUtils.equalsIgnoreCase(a, "false"));
    }

    @Test
    public void string() {
        String a = "true";
        System.out.println(a.equalsIgnoreCase("false"));
        System.out.println(a.equalsIgnoreCase(null));
    }
}
