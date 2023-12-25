package design_pattern.proxy.dynamic;

import java.lang.reflect.Proxy;

public class MatchMaking {
    public static void main(String[] args) {
        MatchMaking test = new MatchMaking();
        test.drive();
    }

    public void drive() {
        Person 김철수 = getPersonInstance("김철수", "남자", "게임", 10); // 김철수 등록
        Person ownerProxy = getOwnerProxy(김철수); // 김철수의 소유자 프록시 생성
        System.out.println("Name is " + ownerProxy.getName()); // getter 메서드는 누구나 호출 가능
        ownerProxy.setInterests("Bowling, Go"); // setter 메서드는 소유자만 호출 가능
        System.out.println("Interests set from owner proxy");

        try {
            ownerProxy.setGeekRating(10); // setGeekRating 메서드는 비소유자만 호출 가능 => 예외 발생
        } catch (Exception e) {
            System.out.println("Can't set rating from owner proxy");
        }
        System.out.println("Rating is " + ownerProxy.getGeekRating());

        Person nonOwnerProxy = getNonOwnerProxy(김철수); // 김철수의 비소유자 프록시 생성
        System.out.println("Name is " + nonOwnerProxy.getName()); // getter 메서드는 누구나 호출 가능

        try {
            nonOwnerProxy.setInterests("Bowling, Go"); // setter 메서드는 소유자만 호출 가능 => 예외 발생
        } catch (Exception e) {
            System.out.println("Can't set interests from non owner proxy");
        }
        nonOwnerProxy.setGeekRating(3); // setGeekRating 메서드는 비소유자만 호출 가능
        System.out.println("Rating set from non owner proxy");
        System.out.println("Rating is " + nonOwnerProxy.getGeekRating());
    }

    private Person getPersonInstance(String name, String gender, String interests, int rating) {
        Person person = new PersonImpl();
        person.setName(name);
        person.setGender(gender);
        person.setInterests(interests);
        person.setGeekRating(rating);
        return person;
    }

    Person getOwnerProxy(Person person) {
        return (Person) Proxy.newProxyInstance(
                person.getClass().getClassLoader(),
                person.getClass().getInterfaces(),
                new OwnerInvocationHandler(person));
    }

    Person getNonOwnerProxy(Person person) {
        return (Person) Proxy.newProxyInstance(
                person.getClass().getClassLoader(),
                person.getClass().getInterfaces(),
                new NonOwnerInvocationHandler(person));
    }
}
