package design_pattern.factory.pizza;

public class CheesePizza implements Pizza {
    private final int price;

    public CheesePizza(int price) {
        this.price = price;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public void prepare() {
        System.out.println("Preparing " + getClass().getSimpleName());
    }
}
