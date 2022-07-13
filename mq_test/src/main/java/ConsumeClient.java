/**
 * @author jinpeng.fan
 * @date 2022/5/19 6:53 PM
 * description
 */
public class ConsumeClient {

    public static void main(String[] args) throws Exception {
        MqClient client = new MqClient();
        System.out.println(client.consume());
    }
}
