import com.google.common.primitives.Ints;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class IntsTest {
    @Test
    public void asList() {
        int[] array = new int[]{1, 2, 3};
        System.out.println(Arrays.asList(array));
        System.out.println(Ints.asList(array));

        List<Integer> list = Ints.asList(array);
        System.out.println(list);
        list.forEach(System.out::println);
        // Ints.asList()返回一个不可变的数组
        list.add(4);
    }
}
