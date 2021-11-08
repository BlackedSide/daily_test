import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.util.List;
import java.util.Set;

/**
 * Created by jinpeng.fan
 * Date: 2021/10/26
 * Time: 14:10
 * Project: daily_test
 * Package: PACKAGE_NAME
 *
 * @author jinpeng.fan
 */
public class SetTest {

    @Test
    public void lombok() {
        Set<Person> set = Sets.newHashSet();
        Person a = new Person("jinpeng", 25, Lists.newArrayList("play"));
        Person b = new Person("jinpeng", 25, Lists.newArrayList("play"));
        set.add(a);
        set.add(b);
        System.out.println(set);
    }

    @Test
    public void common() {
        Set<Animal> set = Sets.newHashSet();
        Animal a = new Animal("jinpeng", 25, Lists.newArrayList("play"));
        Animal b = new Animal("jinpeng", 25, Lists.newArrayList("play"));
        set.add(a);
        set.add(b);
        System.out.println(set);
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    @Builder
    private static class Person {
        String name;
        Integer age;
        List<String> hobby;
    }

    private static class Animal {
        String name;
        Integer tall;
        List<String> action;

        public Animal(String name, Integer tall, List<String> action) {
            this.name = name;
            this.tall = tall;
            this.action = action;
        }
    }
}
