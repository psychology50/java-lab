package design_pattern.factory.store;

import design_pattern.factory.Ingredient.NYPizzaIngredientFactory;
import design_pattern.factory.Ingredient.Pizza;
import design_pattern.factory.Ingredient.PizzaIngredientFactory;

public class NYPizzaStore extends AbstractPizzaStore {
    @Override
    public Pizza createPizza(String type) {
        Pizza pizza = null;
        PizzaIngredientFactory ingredientFactory = new NYPizzaIngredientFactory();

        if (type.equals("cheese")) {
//            pizza = new CheesePizza(ingredientFactory);
            pizza.setName("New York Style Cheese Pizza");
        } else if (type.equals("pepperoni")) {
//            pizza = new PepperoniPizza(ingredientFactory);
            pizza.setName("New York Style Pepperoni Pizza");
        } else if (type.equals("clam")) {
//            pizza = new ClamPizza(ingredientFactory);
            pizza.setName("New York Style Clam Pizza");
        } else if (type.equals("veggie")) {
//            pizza = new VeggiePizza(ingredientFactory);
            pizza.setName("New York Style Veggie Pizza");
        } else {
            throw new IllegalArgumentException("No such pizza.");
        }
        return pizza;
    }
}
