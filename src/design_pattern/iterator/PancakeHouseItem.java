package design_pattern.iterator;

public enum PancakeHouseItem {
    PANCAKE1("Pancake1", "Pancake1 description", true, 2.99),
    PANCAKE2("Pancake2", "Pancake2 description", false, 2.99),
    PANCAKE3("Pancake3", "Pancake3 description", true, 3.49),
    PANCAKE4("Pancake4", "Pancake4 description", false, 3.59),
    PANCAKE5("Pancake5", "Pancake5 description", false, 3.69);

    private final String name;
    private final String description;
    private final boolean vegetarian;
    private final double price;

    PancakeHouseItem(String name, String description, boolean vegetarian, double price) {
        this.name        = name;
        this.description = description;
        this.vegetarian  = vegetarian;
        this.price       = price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public double getPrice() {
        return price;
    }
}
