package design_pattern.factory.Ingredient;

public interface PizzaIngredientFactory {
    Dough createDough();
    Sauce createSauce();
    Cheese createCheese();
    Veggies[] createVeggies();
    Pepparoni createPepparoni();
    Clams createClams();
}
