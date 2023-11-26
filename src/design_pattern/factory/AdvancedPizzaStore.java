package design_pattern.factory;

import design_pattern.factory.pizza.Pizza;

public class AdvancedPizzaStore {
    private final SimplePizzaFactory factory;

    public AdvancedPizzaStore(SimplePizzaFactory factory) {
        this.factory = factory;
    }

    public Pizza orderPizza(String type) {
        Pizza pizza = factory.createPizza(type);
        prepareForSale(pizza);
        return pizza;
    }

    private void prepareForSale(Pizza pizza) {
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
    }
}
