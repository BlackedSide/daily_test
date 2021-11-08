import com.google.common.base.CharMatcher;
import org.junit.Test;

public class CharMatcherTest {
    @Test
    public void charMatcher() {
        String s = "abcd1234";
        String s1 = "a1b2c3d4";
        String s2 = "aa1,3bbb4sla";
        CharMatcher charMatcher = CharMatcher.anyOf("abcd");
        System.out.println(charMatcher.or(CharMatcher.is('2')).removeFrom(s));
        System.out.println(charMatcher.removeFrom(s1));
        System.out.println(charMatcher.removeFrom(s2));
        System.out.println(charMatcher.retainFrom(s2));
    }
}
