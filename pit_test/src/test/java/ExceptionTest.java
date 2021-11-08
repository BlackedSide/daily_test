import org.junit.Test;

/**
 * Created by jinpeng.fan
 * Date: 2021/10/27
 * Time: 10:59
 * Project: daily_test
 * Package: PACKAGE_NAME
 *
 * @author jinpeng.fan
 */
public class ExceptionTest {

    @Test
    public void runtimeException() {
        Integer a = 3;
        Integer res = illegalArgsException(a);
        System.out.println(res);
    }

    private Integer illegalArgsException(Integer a) {
        if (a.equals(0)) {
            return 0;
        }
        throw new IllegalArgumentException("参数不合法！");
    }
}
