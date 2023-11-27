package design_pattern.observer.functional;

import java.util.ArrayList;
import java.util.List;

@FunctionalInterface
interface Observer<E> {
    void added(ConcreteSubject<E> set, E element);
}

interface Subject<E> {
    void addObserver(Observer<E> o);
    void removeObserver(Observer<E> o);
    void notifyElementAdded(E element);
}

class ConcreteSubject<E> implements Subject<E> {
    // 내부 클래스로 리스너를 정의하는 Subject
    private final List<Observer<E>> observers = new ArrayList<>();

    @Override
    public void addObserver(Observer<E> o) {
        synchronized (observers) {
            observers.add(o);
        }
    }

    @Override
    public void removeObserver(Observer<E> o) {
        synchronized (observers) {
            observers.remove(o);
        }
    }

    @Override
    public void notifyElementAdded(E element) {
        List<Observer<E>> snapshot = null;
        synchronized (observers) {
            snapshot = new ArrayList<>(observers);
        }
        snapshot.forEach(o -> o.added(this, element));
    }

    public void add(E element) {
        notifyElementAdded(element);
    }
}

public class Main {
    public static void main(String[] args) {
        ConcreteSubject<String> subject = new ConcreteSubject<>();

        subject.addObserver((set, element) -> System.out.println("added1: " + element));
        subject.addObserver((set, element) -> System.out.println("added2: " + element));

        subject.add("hello");
        subject.add("world");
    }
}
