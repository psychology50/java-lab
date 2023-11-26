package design_pattern.factory.pizza;

public class VeggiePizza implements Pizza {
    private final int price;

    public VeggiePizza(int price) {
        this.price = price;
    }

    @Override
    public int getPrice() {
        return price;
    }
}
