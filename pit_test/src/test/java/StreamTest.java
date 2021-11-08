import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by funny
 * Date: 2021/11/2
 * Time: 19:51
 * Project: daily_test
 * Package: PACKAGE_NAME
 *
 * @author funny
 */
public class StreamTest {

    @Test
    public void filter() {
        List<Person> list = Lists.newArrayList();
        list.add(new Person("jinpeng", "black"));
        list.add(null);
        Set<String> newList = list.stream()
                .filter(Objects::nonNull)
                .filter(p -> "true".equalsIgnoreCase(p.getName()))
                .map(Person::getNickName)
                .collect(Collectors.toSet());
        System.out.println(newList);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class Person {
        String name;
        String nickName;
    }
}
