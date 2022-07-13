import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author jinpeng.fan
 * @date 2022/5/19 6:35 PM
 * description
 */
public class Broker {

    private final static int MAX_SIZE = 3;

    private final static ArrayBlockingQueue<String> messageQueue = new ArrayBlockingQueue<>(MAX_SIZE);

    public static void produce(String msg) {
        if (messageQueue.offer(msg)) {
            System.out.printf("成功投递消息，msg=>%s，queue.size=>%s\n", msg, messageQueue.size());
        } else {
            System.out.println("消息队列已满！");
        }
    }

    public static String consume() {
        String msg = messageQueue.poll();

        if (msg != null) {
            System.out.printf("已经消费消息，msg=>%s，queue.size=>%s\n", msg, messageQueue.size());
        } else {
            System.out.println("消息队列没有消息！");
        }
        System.out.println("============");
        return msg;
    }
}
