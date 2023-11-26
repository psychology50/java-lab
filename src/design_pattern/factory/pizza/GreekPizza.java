package design_pattern.factory.pizza;

public class GreekPizza implements Pizza {
    private final int price;

    public GreekPizza(int price) {
        this.price = price;
    }

    @Override
    public int getPrice() {
        return price;
    }
}
