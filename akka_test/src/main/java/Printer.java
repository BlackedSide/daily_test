import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 * Created by jinpeng.fan
 * Date: 2021/10/20
 * Time: 15:33
 * Project: daily_test
 * Package: PACKAGE_NAME
 *
 * @author jinpeng.fan
 */
public class Printer extends AbstractActor {

    public static Props props() {
        return Props.create(Printer.class, () -> new Printer());
    }

    public static class Greeting {
        public final String message;

        public Greeting(String message) {
            this.message = message;
        }
    }

    private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    public Printer() {

    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(Greeting.class, greeting -> {
                    log.info(greeting.message);
                })
                .build();
    }
}
