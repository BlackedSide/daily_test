import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author jinpeng.fan
 * @date 2022/5/19 6:46 PM
 * description
 */
public class MqClient {

    public void produce(String message) throws Exception {
        Socket socket = new Socket(InetAddress.getLocalHost(), BrokerServer.SERVER_PORT);
        try (PrintWriter out = new PrintWriter(socket.getOutputStream())) {
            out.println(message);
            out.flush();
        }
    }

    public String consume() throws Exception {
        Socket socket = new Socket(InetAddress.getLocalHost(), BrokerServer.SERVER_PORT);
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream())) {
            out.println("CONSUME");
            out.flush();
            return in.readLine();
        }
    }
}
