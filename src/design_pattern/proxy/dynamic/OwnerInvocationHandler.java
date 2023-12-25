package design_pattern.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

// InvocationHandler 인터페이스를 구현해야 한다.
public class OwnerInvocationHandler implements InvocationHandler {
    Person person; // Subject 객체 composition

    public OwnerInvocationHandler(Person person) {
        this.person = person;
    }

    // invoke 메소드는 Proxy 객체의 모든 메소드 호출을 처리한다. (proxy method 호출될 때마다 호출된다.)
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
            if (method.getName().startsWith("get")) { // get 메소드는 호출할 수 있다.
                return method.invoke(person, args);
            } else if (method.getName().equals("setGeekRating")) { // setGeekRating 메소드는 호출할 수 없다.
                throw new IllegalAccessException();
            } else if (method.getName().startsWith("set")) { // set 메소드는 호출할 수 있다.
                return method.invoke(person, args);
            }
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null; // 다른 메서드가 호출된다면 null을 반환한다.
    }
}
