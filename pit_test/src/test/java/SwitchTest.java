import com.google.common.base.Strings;
import org.junit.Test;

public class SwitchTest {
    @Test
    public void switchTest() {
        try {
            switchMethod(null);
        } catch (IllegalArgumentException ex) {
            System.out.println("输入不能为空");
        }
    }

    private void switchMethod(String param) {
        if (Strings.isNullOrEmpty(param)) {
            throw new IllegalArgumentException("输入不能为空");
        }
        switch (param) {
            case "sth":
                System.out.println("It's sth");
                break;
            case "null":
                System.out.println("It's null");
                break;
            default:
                System.out.println("default");
        }
    }
}
