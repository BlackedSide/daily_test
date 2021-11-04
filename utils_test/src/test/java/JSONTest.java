import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

/**
 * Created by funny
 * Date: 2021/11/3
 * Time: 21:09
 * Project: daily_test
 * Package: PACKAGE_NAME
 *
 * @author funny
 */
public class JSONTest {
    @Test
    public void jsonTest() {
        List<String> list = Lists.newArrayList("a", "b", "c");
        System.out.println(JSON.toJSON(list));
        List<Person> list2 = Lists.newArrayList(new Person("zhao", 1), new Person("qian", 2));
        System.out.println(JSON.toJSONString(list2));
    }

    @Test
    public void arrayTest() {
        String arr = "[1, 2, 3]";
        System.out.println(JSON.parse(arr));
    }

    static class Person {
        String name;
        int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }
}
