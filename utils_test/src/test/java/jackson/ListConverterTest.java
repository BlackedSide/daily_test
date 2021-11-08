package jackson;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

/**
 * Created by jinpeng.fan
 * Date: 2021/9/27
 * Time: 17:15
 * Project: daily_test
 * Package: jackson
 *
 * @author jinpeng.fan
 */
public class ListConverterTest {
    @Test
    public void listConverter() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        org.codehaus.jackson.map.ObjectMapper objectMapper1 = new org.codehaus.jackson.map.ObjectMapper();
        List<Person> people = Lists.newArrayList(new Person("fan", 25), new Person("yuan", 25));
        String json = objectMapper.writeValueAsString(people);
        System.out.println(json);
        List<Person> convertList = objectMapper.readValue(json, new TypeReference<List<Person>>() {
        });
        System.out.println(convertList);
        System.out.println(convertList.get(0).getName());
    }

    private static class Person {
        String name;
        Integer age;

        public Person() {

        }

        public Person(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }
}
