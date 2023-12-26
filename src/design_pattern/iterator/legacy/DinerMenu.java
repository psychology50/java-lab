package design_pattern.iterator.legacy;

import design_pattern.iterator.DinerItem;
import design_pattern.iterator.Menu;
import design_pattern.iterator.MenuItem;
import design_pattern.iterator.iter.DinerMenuIterator;

import java.util.Iterator;
import java.util.stream.Stream;

public class DinerMenu implements Menu {
    static final int MAX_ITEMS = 6;
    int numberOfItems = 0;
    MenuItem[] menuItems;

    public DinerMenu() {
        menuItems = new MenuItem[MAX_ITEMS];

        Stream.of(DinerItem.values()).forEach(item -> {
            addItem(item.getName(), item.getDescription(), item.isVegetarian(), item.getPrice());
        });
    }

    public void addItem(String name, String description, boolean vegetarian, double price) {
        if (numberOfItems >= MAX_ITEMS) {
            System.err.println("Sorry, menu is full! Can't add item to menu");
        } else {
            menuItems[numberOfItems++] = new MenuItem(name, description, vegetarian, price);
        }
    }

//    public MenuItem[] getMenuItems() {
//        return menuItems;
//    }

    @Override
    public Iterator createIterator() {
        return new DinerMenuIterator(menuItems);
    }
}
