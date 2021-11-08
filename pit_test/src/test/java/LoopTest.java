import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

/**
 * Created by funny
 * Date: 2021/11/3
 * Time: 16:11
 * Project: daily_test
 * Package: PACKAGE_NAME
 *
 * @author funny
 */
public class LoopTest {

    @Test
    public void testForEach() {
        List<String> container = Lists.newArrayList();
        for (int i = 0; i < 1000; i++) {
            container.add(String.valueOf(i * 50));
        }
        Base base = new Base();
        long start = System.currentTimeMillis();
        for (String s : base.getList()) {
            if (container.contains(s)) {
                System.out.println(s);
            }
        }
        System.out.printf("耗时：%sms\n", System.currentTimeMillis() - start);
        System.out.println(count);
    }

    private static int count = 0;

    static class Base {
        List<String> list = Lists.newArrayList();

        {
            for (int i = 0; i < 10000; i++) {
                this.list.add(String.valueOf(i * 10));
            }
        }

        public List<String> getList() {
            count++;
            return list;
        }
    }
}
