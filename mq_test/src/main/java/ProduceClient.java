/**
 * @author jinpeng.fan
 * @date 2022/5/19 6:50 PM
 * description
 */
public class ProduceClient {

    public static void main(String[] args) throws Exception {
        MqClient client = new MqClient();
        client.produce("Hello World");
    }
}
