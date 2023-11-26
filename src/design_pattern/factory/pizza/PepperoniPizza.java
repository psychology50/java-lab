package design_pattern.factory.pizza;

public class PepperoniPizza implements Pizza {
    private final int price;

    public PepperoniPizza(int price) {
        this.price = price;
    }

    @Override
    public int getPrice() {
        return price;
    }
}
