package design_pattern.iterator.iter;

import design_pattern.iterator.MenuItem;

import java.util.Iterator;

public class DinerMenuIterator implements Iterator {
    private final MenuItem[] items;
    private int position = 0;

    public DinerMenuIterator(MenuItem[] items) {
        this.items = items;
    }

    @Override
    public boolean hasNext() {
        return position < items.length && items[position] != null;
    }

    @Override
    public Object next() {
        MenuItem item = items[position++];
        return item;
    }
}
