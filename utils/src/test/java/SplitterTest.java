import com.google.common.base.Splitter;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class SplitterTest {

    @Test
    public void splitString() {
        String s = ", a , b ,";
        System.out.println(Arrays.toString(s.split("[,]")));

        List<String> list = Splitter.on(",").trimResults().omitEmptyStrings().splitToList(s);
        System.out.println(list);
    }

    @Test
    public void splitToMap() {
        String s1 = "name=jinpeng.fan&id=1";
        Map<String, String> map = Splitter.on('&').withKeyValueSeparator('=').split(s1);
        System.out.println(map);
    }
}
