package design_pattern.iterator.legacy;

import design_pattern.iterator.Menu;
import design_pattern.iterator.MenuItem;
import design_pattern.iterator.PancakeHouseItem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class PancakeHouseMenu implements Menu {
    ArrayList<MenuItem> menuItems;

    public PancakeHouseMenu() {
        menuItems = new ArrayList<>();

        Stream.of(PancakeHouseItem.values()).forEach(item -> {
            menuItems.add(new MenuItem(item.getName(), item.getDescription(), item.isVegetarian(), item.getPrice()));
        });
    }

//    public ArrayList<MenuItem> getMenuItems() {
//        return menuItems;
//    }

    @Override
    public Iterator createIterator() {
        return menuItems.iterator();
    }

    public void addItem(String name, String description, boolean vegetarian, double price) {
        menuItems.add(new MenuItem(name, description, vegetarian, price));
    }
}
