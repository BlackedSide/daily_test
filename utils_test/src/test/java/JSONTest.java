import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

/**
 * Created by funny
 * Date: 2021/11/3
 * Time: 21:09
 * Project: daily_test
 * Package: PACKAGE_NAME
 *
 * @author funny
 */
public class JSONTest {
    @Test
    public void jsonTest() {
        List<String> list = Lists.newArrayList("a", "b", "c");
        System.out.println(JSON.toJSON(list));
    }
}
