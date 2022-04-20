import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @author jinpeng.fan
 * @date 2022/3/31 7:03 PM
 * description
 */
public class CopyTest {

    @Test
    public void assign() {
        Person a = Person.builder().name("Fan").toy(new Toy()).build();
        a.getToy().setName("Gold");
        Person b = a;
        b.getToy().setName("Silver");
        System.out.println(a);
        System.out.println(b);
    }

    @Test
    public void shallowCopy() throws InvocationTargetException, IllegalAccessException {
        Person a = Person.builder().name("Fan").toy(new Toy("Gold")).build();
        Person b = Person.builder().build();
        BeanUtils.copyProperties(b, a);
        a.getToy().setName("Silver");
        System.out.println(a + a.getToy().getName());
        System.out.println(b + b.getToy().getName());
        Toy c = new Toy("Gold");
        Toy d = new Toy("Silver");
        BeanUtils.copyProperties(d, c);
        System.out.println(c.getName());
        System.out.println(d.getName());
    }

    @Test
    public void listCopy() {
        Person a = Person.builder().name("Fan").toy(new Toy("Gold")).build();
        Person b = Person.builder().name("Yuan").toy(new Toy("Silver")).build();
        List<Person> people = Lists.newArrayList(a, b);
        System.out.println(people);
        List<Person> personList = Lists.newArrayList(people);
        personList.get(0).getToy().setName("Good");
        System.out.println(people);
        System.out.println(personList);
    }

    @Test
    public void listCopy2() {
        Toy a = Toy.builder().name("Gold").build();
        Toy b = Toy.builder().name("Silver").build();
        List<Toy> toys = Lists.newArrayList(a, b);
        System.out.println(toys);
        List<Toy> toyList = Lists.newArrayList(toys);
        a.setName("Good");
        System.out.println(toys);
        System.out.println(toyList);
    }

    @Test
    public void deepClone() throws CloneNotSupportedException {
        Person a = Person.builder().name("Fan").toy(new Toy("Gold")).build();
        Person b = a.clone();
        b.getToy().setName("Good");
        System.out.println(a);
        System.out.println(b);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Person implements Cloneable {
        private String name;

        private Toy toy;

        @Override
        public Person clone() throws CloneNotSupportedException {
            Person clone = (Person) super.clone();
            clone.setName(getName());
            clone.setToy(Toy.builder().name(getToy().getName()).build());
            return clone;
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Toy {
        private String name;
    }
}
