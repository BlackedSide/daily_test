import com.google.common.base.Strings;
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
}
