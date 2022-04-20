import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.util.List;
import java.util.Map;

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

    @Test
    public void maxInteger() {
        RuleList rule1 = RuleList.builder().usage(1).ruleMap(Maps.newHashMap()).build();
        RuleList rule2 = RuleList.builder().usage(2).ruleMap(Maps.newHashMap()).build();
        rule1.getRuleMap().put("envUpdate", "100");
        rule1.getRuleMap().put("pmoClose", "50");
        rule2.getRuleMap().put("envUpdate", "120");
        rule2.getRuleMap().put("pmoClose", "60");
        List<RuleList> ruleList = Lists.newArrayList(rule1, rule2);
        System.out.println(ruleList.stream()
                .filter(item -> Integer.valueOf(3).equals(item.getUsage()))
                .map(item -> Integer.parseInt(item.getRuleMap().get("envCreate")))
                .max(Integer::compareTo).orElse(0));
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    private static class RuleList {
        private Integer usage;

        private Map<String, String> ruleMap;
    }
}
