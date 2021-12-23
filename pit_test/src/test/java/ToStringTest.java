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
 * Date: 2021/10/21
 * Time: 20:36
 * Project: daily_test
 * Package: PACKAGE_NAME
 *
 * @author jinpeng.fan
 */
public class ToStringTest {

    @Test
    public void intParse() {
        String s = "";
        String list = "1,2,3,4,";
        List<Integer> intList = Lists.newArrayList();
//        System.out.println(Integer.parseInt(s));
        for (String tip : list.split(",")) {
            intList.add(Integer.parseInt(tip));
        }
        System.out.println(intList);
    }

    @Test
    public void setToString() {
        Set<String> set = Sets.newHashSet("hallo", "jinpeng");
        System.out.println(set);
    }

    @Test
    public void lombokToString() {
        List<Person> people = Lists.newArrayList(new Person("fan", 25), new Person("yuan", 25));
        System.out.println(people);
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Data
    static class Person {
        String name;
        int age;
    }
}
