package design_pattern.factory.store;

import design_pattern.factory.pizza.AbstractPizza;
import design_pattern.factory.pizza.NYStyleCheesePizza;
import design_pattern.factory.pizza.Pizza;

public class NYPizzaFactory extends AbstractPizzaStore {
    @Override
    protected AbstractPizza createPizza(String type) {
        AbstractPizza pizza;
        switch (type) {
            case "cheese":
                pizza = new NYStyleCheesePizza();
                break;
            case "pepperoni":
                pizza = new NYStylePepperoniPizza();
                break;
            case "clam":
                pizza = new NYStyleClamPizza();
                break;
            case "veggie":
                pizza = new NYStyleVeggiePizza();
                break;
            default:
                throw new IllegalArgumentException("No such pizza.");
        }
        return pizza;
    }
}
