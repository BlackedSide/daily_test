import akka.actor.ActorRef;
import akka.actor.ActorSystem;

import java.io.IOException;

/**
 * Created by jinpeng.fan
 * Date: 2021/10/20
 * Time: 19:34
 * Project: daily_test
 * Package: PACKAGE_NAME
 *
 * @author jinpeng.fan
 */
public class AkkaQuickStart {
    public static void main(String[] args) {
        // 运行Actors的容器
        final ActorSystem system = ActorSystem.create("helloakka");
        try {
            final ActorRef printerActor = system.actorOf(Printer.props(), "printerActor");
            final ActorRef howdyGreeter = system.actorOf(Greeter.props("Howdy", printerActor));
            final ActorRef helloGreeter = system.actorOf(Greeter.props("Hello", printerActor));
            final ActorRef goodDayGreeter = system.actorOf(Greeter.props("GoodDay", printerActor));

            howdyGreeter.tell(new Greeter.WhoToGreet("Akka"), ActorRef.noSender());
            howdyGreeter.tell(new Greeter.Greet(), ActorRef.noSender());

            howdyGreeter.tell(new Greeter.WhoToGreet("Lightbend"), ActorRef.noSender());
            howdyGreeter.tell(new Greeter.Greet(), ActorRef.noSender());

            helloGreeter.tell(new Greeter.WhoToGreet("Play"), ActorRef.noSender());
            helloGreeter.tell(new Greeter.Greet(), ActorRef.noSender());

            goodDayGreeter.tell(new Greeter.WhoToGreet("Java"), ActorRef.noSender());
            goodDayGreeter.tell(new Greeter.Greet(), ActorRef.noSender());

            System.out.println(">>> Press enter to exit <<<");
            System.in.read();
        } catch (IOException e) {

        } finally {
            system.terminate();
        }
    }
}
