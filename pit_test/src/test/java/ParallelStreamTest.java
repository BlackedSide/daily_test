import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.util.List;

/**
 * AUTHOR fanny
 * DATE 2022/2/28 5:14 PM
 * DESCRIPTION
 */
public class ParallelStreamTest {

    @Test
    public void test() {
        List<Person> people = Lists.newArrayList(
                new Person("Fan", 26),
                new Person("Yuan", 26),
                new Person("Zhang", 25),
                new Person("Wu", 27),
                new Person("Sun", 30),
                new Person("Li", 29)
        );
        people.parallelStream().map(person -> person.getName() + "-Great").forEach(System.out::println);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    static class Person {
        String name;

        int age;
    }
}
