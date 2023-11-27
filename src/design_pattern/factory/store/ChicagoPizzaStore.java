package design_pattern.factory.store;


import design_pattern.factory.Ingredient.Pizza;

public class ChicagoPizzaStore extends AbstractPizzaStore {
    @Override
    protected Pizza createPizza(String type) {
        Pizza pizza = null;
        switch (type) {
            case "cheese":
//                pizza = new ChicagoStyleCheesePizza();
                break;
            case "pepperoni":
//                pizza = new ChicagoStylePepperoniPizza();
                break;
            case "clam":
//                pizza = new ChicagoStyleClamPizza();
                break;
            case "veggie":
//                pizza = new ChicagoStyleVeggiePizza();
                break;
            default:
                throw new IllegalArgumentException("No such pizza.");
        }
        return pizza;
    }
}
