package design_pattern.factory.store;

import design_pattern.factory.pizza.Pizza;

public class CaliforniaPizzaStore extends AbstractPizzaStore {
    @Override
    protected Pizza createPizza(String type) {
        Pizza pizza;
        switch (type) {
            case "cheese":
                pizza = new CaliforniaStyleCheesePizza();
                break;
            case "pepperoni":
                pizza = new CaliforniaStylePepperoniPizza();
                break;
            case "clam":
                pizza = new CaliforniaStyleClamPizza();
                break;
            case "veggie":
                pizza = new CaliforniaStyleVeggiePizza();
                break;
            default:
                throw new IllegalArgumentException("No such pizza.");
        }
        return pizza;
    }
}
