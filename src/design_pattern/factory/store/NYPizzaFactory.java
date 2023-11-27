package design_pattern.factory.store;

import design_pattern.factory.Ingredient.Pizza;
import design_pattern.factory.pizza.AbstractPizza;
import design_pattern.factory.pizza.NYStyleCheesePizza;

public class NYPizzaFactory extends AbstractPizzaStore {
    @Override
    protected Pizza createPizza(String type) {
        Pizza pizza = null;
        switch (type) {
            case "cheese":
//                pizza = new NYStyleCheesePizza();
                break;
            case "pepperoni":
//                pizza = new NYStylePepperoniPizza();
                break;
            case "clam":
//                pizza = new NYStyleClamPizza();
                break;
            case "veggie":
//                pizza = new NYStyleVeggiePizza();
                break;
            default:
                throw new IllegalArgumentException("No such pizza.");
        }
        return pizza;
    }
}
