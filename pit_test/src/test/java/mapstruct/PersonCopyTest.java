package mapstruct;

import com.google.common.collect.Lists;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

/**
 * @author jinpeng.fan
 * @date 2022/7/13 12:05 PM
 * description
 */
public class PersonCopyTest {

    @Test
    public void test() throws IOException {
        Person person = Person.builder()
                .strings(Lists.newArrayList("1"))
                .children(Lists.newArrayList(Person.Child.builder()
                        .name("Key")
                        .favors(Lists.newArrayList(1))
                        .build()))
                .build();
        Person personCopy = PersonMapper.INSTANCE.deepCopy(person);
        ObjectMapper objectMapper = new ObjectMapper();
        String personCopyStr = objectMapper.writeValueAsString(person);
        Person personCopyFromJson = objectMapper.readValue(personCopyStr, Person.class);
        person.getStrings().add("2");
        person.getChildren().get(0).setName("Value");
        person.getChildren().add(Person.Child.builder()
                .name("Wang")
                .build());
        System.out.println(personCopy);
        System.out.println(personCopyFromJson);
        System.out.println(person);
    }
}
