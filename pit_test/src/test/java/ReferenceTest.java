import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * Created by jinpeng.fan
 * Date: 2021/9/30
 * Time: 15:48
 * Project: daily_test
 * Package: PACKAGE_NAME
 *
 * @author jinpeng.fan
 */
public class ReferenceTest {
    @Test
    public void testMap() throws InterruptedException {
        Map<Integer, List<Person>> map = Maps.newConcurrentMap();
        List<Person> people = Lists.newArrayList(
                new Person(1, "fan"),
                new Person(2, "yuan"),
                new Person(3, "wang"),
                new Person(3, "duan"),
                new Person(1, "fan"),
                new Person(2, "yuan"),
                new Person(3, "wang"),
                new Person(3, "duan"),
                new Person(1, "fan"),
                new Person(2, "yuan"),
                new Person(3, "wang"),
                new Person(3, "duan")
        );
        CountDownLatch countDownLatch = new CountDownLatch(12);
        for (Person p : people) {
            if (p.id == 1) {
                countDownLatch.countDown();
                continue;
            }
            new Thread(() -> {
                try {
                    Thread.sleep(1000);
                    insertMap(p, map);
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        countDownLatch.await();
        for (Map.Entry<Integer, List<Person>> entry : map.entrySet()) {
            System.out.println(entry.getKey());
            for (Person p : entry.getValue()) {
                System.out.println(p.name);
            }
        }
    }

    private synchronized void insertMap(Person person, Map<Integer, List<Person>> map) {
        if (map.get(person.id) == null) {
            map.put(person.id, Lists.newArrayList(person));
        } else {
            map.get(person.id).add(person);
        }
    }

    private static class Person {
        Integer id;
        String name;

        public Person(Integer id, String name) {
            this.id = id;
            this.name = name;
        }
    }
}
