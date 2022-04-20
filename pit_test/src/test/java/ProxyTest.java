import lombok.NoArgsConstructor;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author jinpeng.fan
 * @date 2022/4/14 8:03 PM
 * description
 */
public class ProxyTest {

    @Test
    public void test() {
        JdkProxy jdkProxy = new JdkProxy(new Person());
        Root person = (Root) Proxy.newProxyInstance(jdkProxy.getClass().getClassLoader(), new Class[]{Root.class}, jdkProxy);
        person.say();
        Person cglibPerson = (Person) new CglibProxy(new Person()).getProxy();
        cglibPerson.say();
    }

    public interface Root {
        void say();
    }

    @NoArgsConstructor
    private static class Person implements Root {
        @Override
        public void say() {
            System.out.println("This is Person.");
        }
    }

    private static class JdkProxy implements InvocationHandler {

        private final Object bean;

        public JdkProxy(Object bean) {
            this.bean = bean;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("Hello~");
            return method.invoke(bean, args);
        }
    }

    private static class CglibProxy implements MethodInterceptor {

        private final Enhancer enhancer = new Enhancer();

        private final Object bean;

        public CglibProxy(Object bean) {
            this.bean = bean;
        }

        public Object getProxy() {
            enhancer.setSuperclass(bean.getClass());
            enhancer.setCallback(this);
            return enhancer.create();
        }

        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            System.out.println("Yes!");
            return method.invoke(bean, objects);
        }
    }
}
