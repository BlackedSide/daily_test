/**
 * @author jinpeng.fan
 * @date 2022/5/11 5:35 PM
 * description 事件源
 */
public class Person {

    private final String name;

    private PersonListener personListener;

    public Person(String name) {
        this.name = name;
    }

    public void eat() {
        personListener.eat(new Event(this));
    }

    public void sleep() {
        personListener.sleep(new Event(this));
    }

    public void registerListener(PersonListener personListener) {
        this.personListener = personListener;
    }

    @Override
    public String toString() {
        return "Person " + name;
    }

    public static void main(String[] args) {
        Person person = new Person("Fan");
        person.registerListener(new PersonListener() {
            @Override
            public void eat(Event event) {
                System.out.println(event.getResource() + " eating...");
            }

            @Override
            public void sleep(Event event) {
                System.out.println(event.getResource() + " sleeping...");
            }
        });
        person.eat();
    }
}
