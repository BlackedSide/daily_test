import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class JoinerTest {
    Logger logger = LoggerFactory.getLogger(JoinerTest.class);

    @Test
    public void joinerTest() {
        List<Integer> list = Lists.newArrayList(1, 3, 5, null, 7);
        logger.info("Joiner result : {}", Joiner.on("|").skipNulls().join(list));
    }

    @Test
    public void joinMap() {
        String host = "www.baidu.com";
        Map<String, String> map = Maps.newHashMap();
        map.put("name", "jinpeng.fan");
        map.put("id", "1");

        String queryString = host + "?" + Joiner.on("&").withKeyValueSeparator("=").join(map);
        System.out.println(queryString);

    }
}
