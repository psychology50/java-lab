package design_pattern.observer;

import java.util.ArrayList;
import java.util.List;

interface Subject {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
    Object getUpdate(Observer o);
}

interface Observer {
    void update();
}

class Topic implements Subject {
    private List<Observer> observers;
    private String message;

    public Topic() {
        this.observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer o) {
        if (o == null) throw new NullPointerException("Null Observer");
        if (observers.contains(o)) throw new IllegalArgumentException("Observer already registered");
        synchronized (observers) {
            observers.add(o);
        }
    }

    @Override
    public void removeObserver(Observer o) {
        synchronized (observers) {
            observers.remove(o);
        }
    }

    @Override
    public void notifyObservers() {
        List<Observer> observersLocal = null;
        synchronized (observers) {
            observersLocal = new ArrayList<>(this.observers);
        }
        for (Observer o : observersLocal) o.update();
    }

    @Override
    public Object getUpdate(Observer o) {
        return this.message;
    }

    public void postMessage(String msg) {
        System.out.println("Message Posted to Topic: " + msg);
        this.message = msg;
        notifyObservers();
    }
}

class MyTopicSubscriber implements Observer {
    private String name;
    private Subject topic;

    public MyTopicSubscriber(String name, Subject topic) {
        this.name = name;
        this.topic = topic;
    }

    @Override
    public void update() {
        String msg = (String) topic.getUpdate(this);
        if (msg == null) System.out.println(name + ":: No new message");
        else System.out.println(name + ":: Consuming message::" + msg);
    }
}

public class Main {
    public static void main(String[] args) {
        Topic topic = new Topic();

        Observer obj1 = new MyTopicSubscriber("Obj1", topic);
        Observer obj2 = new MyTopicSubscriber("Obj2", topic);
        Observer obj3 = new MyTopicSubscriber("Obj3", topic);

        topic.registerObserver(obj1);
        topic.registerObserver(obj2);
        topic.registerObserver(obj3);

        obj1.update();

        topic.postMessage("New Message");
    }
}
