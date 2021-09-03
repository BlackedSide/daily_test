import org.junit.Test;

import java.util.*;

public class CollectorTest {
    @Test
    public void iterRemove() {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            String item = it.next();
            if (Objects.equals("2", item)) {
                it.remove();
            }
        }
        System.out.println(list);
    }

    @Test
    public void forEachRemove() {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        for (String item : list) {
            if (Objects.equals("1", item)) {
                list.remove(item);
            }
        }
        System.out.println(list);
    }
}
