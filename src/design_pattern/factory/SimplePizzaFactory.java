package design_pattern.factory;

import design_pattern.factory.pizza.*;

public class SimplePizzaFactory {
    public Pizza createPizza(String type) {
        Pizza pizza = null;
        if (type.equals("pepperoni")) {
            pizza = new PepperoniPizza(10);
        } else if (type.equals("clam")) {
            pizza = new ClamPizza(10);
        } else if (type.equals("veggie")) {
            pizza = new VeggiePizza(10);
        } else if (type.equals("greek")) {
            pizza = new GreekPizza(10);
        } else if (type.equals("cheese")) {
            pizza = new CheesePizza(10);
        }
        return pizza;
    }
}
