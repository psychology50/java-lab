package design_pattern.factory.pizza;

public class ClamPizza implements Pizza {
    private final int price;

    public ClamPizza(int price) {
        this.price = price;
    }

    @Override
    public int getPrice() {
        return price;
    }
}
