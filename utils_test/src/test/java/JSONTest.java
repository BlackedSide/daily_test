import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.io.Serializable;
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
    public void jsonTest() throws Exception {
        List<String> list = Lists.newArrayList("a", "b", "c");
        System.out.println(JSON.toJSON(list));
        List<Person> list2 = Lists.newArrayList(new Person("zhao", 1), new Person("qian", 2));
        System.out.println(JSON.toJSON(list2));
        Wrapper wrapper = new Wrapper(list2);
        System.out.println(JSON.toJSONString(wrapper));
    }

    @Test
    public void arrayTest() {
        String arr = "[1, 2, 3]";
        System.out.println(JSON.parse(arr));
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class Person implements Serializable {
        String name;
        int age;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class Wrapper implements Serializable {
        List<Person> list;
    }
}
