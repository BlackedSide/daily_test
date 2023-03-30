/**
 * @author jinpeng.fan
 * @date 2023/3/29 8:45 PM
 * description
 */
public class CanRetryException extends RuntimeException {

    public CanRetryException(String msg) {
        super(msg);
    }
}
