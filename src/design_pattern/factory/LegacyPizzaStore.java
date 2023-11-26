package design_pattern.factory;

import design_pattern.factory.pizza.*;

public class LegacyPizzaStore {
    public Pizza orderPizza(String type) {
        Pizza pizza = null;
        if (type.equals("cheese")) {
            pizza = new CheesePizza(10);
        }
//        else if (type.equals("pepperoni")) {
//            pizza = new PepperoniPizza(12);
//        }
        else if (type.equals("clam")) {
            pizza = new ClamPizza(11);
        } else if (type.equals("veggie")) {
            pizza = new VeggiePizza(9);
        } else if (type.equals("greek")) {
            pizza = new GreekPizza(8);
        }

        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();

        return pizza;
    }
}

