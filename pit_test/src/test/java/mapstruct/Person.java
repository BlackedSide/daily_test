package mapstruct;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author jinpeng.fan
 * @date 2022/7/13 11:57 AM
 * description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Person {

    private List<String> strings;

    private List<Child> children;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Child {

        private String name;

        private List<Integer> favors;
    }
}
