import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.Set;

/**
 * @author jinpeng.fan
 * @date 2022/6/30 6:21 PM
 * description
 */
public class StringUtilTest {
    
    @Test
    public void test() {
        String owner = "jinpeng.fan";
        Set<String> finalOwners = Sets.newHashSet(Splitter.on(",").trimResults().split(owner));
        finalOwners.add("yangyangryan.ma");
        finalOwners.add("kailiang.wu");
        finalOwners.add("feiguang.cao");
        String finalOwnersStr = StringUtils.join(finalOwners, ",");
        System.out.println(finalOwnersStr);
        String finalOwnersStr2 = Joiner.on(",").join(finalOwners);
        System.out.println(finalOwnersStr2);
    }
}
