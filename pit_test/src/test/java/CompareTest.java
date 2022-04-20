import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * @author jinpeng.fan
 * @date 2022/4/12 2:26 PM
 * description
 */
public class CompareTest {

    @Test
    public void test() {
        Result r1 = Result.builder().id("1").timing(LocalDateTime.of(2022, 1, 1, 20, 0, 0)).build();
        Result r2 = Result.builder().id("2").timing(LocalDateTime.MAX).build();
        List<Result> results = Lists.newArrayList(r1, r2);
        Result selected = results.stream().max(Comparator.comparing(Result::getTiming)).orElse(null);
        if (Objects.nonNull(selected)) {
            System.out.println(selected.getId() + " " + selected.getTiming());
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Result {

        private String id;

        private LocalDateTime timing;
    }
}
