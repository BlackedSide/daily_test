import org.junit.Test;

import java.util.Optional;

/**
 * Created by funny
 * Date: 2021/10/28
 * Time: 14:32
 * Project: daily_test
 * Package: PACKAGE_NAME
 *
 * @author funny
 */
public class JavaOptionalTest {

    @Test
    public void javaOptional() {
        System.out.println(Optional.of(null).orElse("yes"));
    }

    @Test
    public void guavaOptional() {
        System.out.println(Optional.ofNullable(null).orElse("yes"));
    }
}
