import com.google.common.collect.Maps;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

/**
 * @author jinpeng.fan
 * @date 2022/4/20 10:27 AM
 * description
 */
public class EqualsTest {

    @Test
    @SuppressWarnings("all")
    public void test() {
        Map<String, String> map = Maps.newHashMapWithExpectedSize(1);
        map.put("one", new String("1"));
        Assert.assertTrue("1" == map.get("one"));
    }
}
