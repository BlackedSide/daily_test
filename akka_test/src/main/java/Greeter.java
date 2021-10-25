import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;

/**
 * Created by jinpeng.fan
 * Date: 2021/10/20
 * Time: 15:26
 * Project: daily_test
 * Package: PACKAGE_NAME
 *
 * @author jinpeng.fan
 */
public class Greeter extends AbstractActor {

    public static Props props(String message, ActorRef printerActor) {
        return Props.create(Greeter.class, () -> new Greeter(message, printerActor));
    }

    public static class WhoToGreet {
        public final String who;

        public WhoToGreet(String who) {
            this.who = who;
        }
    }

    public static class Greet {
        public Greet() {

        }
    }

    private final String message;
    private final ActorRef printerActor;
    private String greeting = "";

    public Greeter(String message, ActorRef printerActor) {
        this.message = message;
        this.printerActor = printerActor;
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(WhoToGreet.class, wtg -> {
                    this.greeting = message + ", " + wtg.who;
                })
                .match(Greet.class, x -> {
                    printerActor.tell(new Printer.Greeting(greeting), getSelf());
                })
                .build();
    }
}
