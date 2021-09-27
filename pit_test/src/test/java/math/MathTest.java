package math;

import org.junit.Test;

/**
 * Created by jinpeng.fan
 * Date: 2021/9/27
 * Time: 15:38
 * Project: daily_test
 * Package: math
 *
 * @author jinpeng.fan
 */
public class MathTest {
    @Test
    public void ceil() {
        int a = 27;
        int result = (int) Math.ceil((double) a / 4.0);
        System.out.println(result);
    }
}
