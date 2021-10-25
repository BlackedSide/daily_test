import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

/**
 * Created by jinpeng.fan
 * Date: 2021/10/25
 * Time: 19:50
 * Project: daily_test
 * Package: PACKAGE_NAME
 *
 * @author jinpeng.fan
 */
public class WrapperClassNullPointTest {
    @Test
    public void integerNull() {
        Person p = new Person();
        p.setName("fan");
        int a = 3;
        System.out.println(a == p.getAge());
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    @Builder
    private static class Person {
        String name;
        Integer age;
    }
}
