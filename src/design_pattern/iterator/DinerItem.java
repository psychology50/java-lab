package design_pattern.iterator;

public enum DinerItem {
    DINER1("Diner1", "Diner1 description", true, 2.99),
    DINER2("Diner2", "Diner2 description", false, 2.99),
    DINER3("Diner3", "Diner3 description", true, 3.49),
    DINER4("Diner4", "Diner4 description", true, 3.59),
    DINER5("Diner5", "Diner5 description", true, 3.69);

    private final String name;
    private final String description;
    private final boolean vegetarian;
    private final double price;

    DinerItem(String name, String description, boolean vegetarian, double price) {
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
