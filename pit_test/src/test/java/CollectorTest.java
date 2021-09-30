import com.google.common.collect.Lists;
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
        list.add("3");
        list.add("4");
        for (String item : list) {
            if (Objects.equals("3", item)) {
                list.remove(item);
            }
        }
        System.out.println(list);
    }

    @Test
    public void nullList() {
        List<String> list = Lists.newArrayList();
        for (String s : list) {
            System.out.println(s);
        }
    }
}
