/**
 * @author jinpeng.fan
 * @date 2022/5/11 5:36 PM
 * description 事件对象
 */
public class Event {

    private Person person;

    public Event() {}

    public Event(Person person) {
        this.person = person;
    }

    public Person getResource() {
        return person;
    }
}
