import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

/**
 * AUTHOR fanny
 * DATE 2022/2/7 12:10 PM
 * DESCRIPTION
 */
public class ListTest {

    @Test
    public void listTest() {
        List<String> result = Lists.newArrayList();
        result.add("Hello");
        result.add(null);
//        result.addAll(null);
        System.out.println(result);
    }
}
