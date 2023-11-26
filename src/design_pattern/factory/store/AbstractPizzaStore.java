package design_pattern.factory.store;


import design_pattern.factory.Ingredient.Pizza;

public abstract class AbstractPizzaStore {
    public final Pizza orderPizza(String type) {
        Pizza pizza = createPizza(type);

        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();

        return pizza;
    }

    protected abstract Pizza createPizza(String type);
}
