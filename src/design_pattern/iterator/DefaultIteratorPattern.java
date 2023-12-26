package design_pattern.iterator;

import java.util.Iterator;

interface Aggregate {
    Iterator createIterator();
}

class ConcreteIterator implements Iterator {
    private final ConcreteAggregate aggregate;
    private int nextIndex = -1;

    public ConcreteIterator(ConcreteAggregate aggregate) {
        this.aggregate = aggregate;
    }

    @Override
    public boolean hasNext() {
        return nextIndex < aggregate.getItems().length - 1;
    }

    @Override
    public Object next() {
        return aggregate.getItems()[++nextIndex];
    }
}

class ConcreteAggregate implements Aggregate {
    private Object[] items;
    int index = -1;

    public ConcreteAggregate(int size) {
        this.items = new Object[size];
    }

    public void add(Object item) {
        if (index >= items.length)
            throw new ArrayIndexOutOfBoundsException();
        items[++index] = item;
    }

    @Override
    public Iterator createIterator() {
        return new ConcreteIterator(this);
    }

    public Object[] getItems() {
        return items;
    }
}

public class DefaultIteratorPattern {
    public static void main(String[] args) {
        ConcreteAggregate aggregate = new ConcreteAggregate(3);
        aggregate.add("A");
        aggregate.add("B");
        aggregate.add("C");

        Iterator iterator = aggregate.createIterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " -> ");
        }
    }
}
