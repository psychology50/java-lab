package design_pattern.factory.Ingredient;

public class NYPizzaIngredientFactory implements PizzaIngredientFactory {
    @Override
    public Dough createDough() {
        return new ThinCrustDough();
    }

    @Override
    public Sauce createSauce() {
        return new MarinaraSouce();
    }

    @Override
    public Cheese createCheese() {
        return new ReggianoCheese();
    }

    @Override
    public Veggies[] createVeggies() {
        return new Veggies[] {new Garlic(), new Onion(), new Mushroom(), new RedPepper()};
    }

    @Override
    public Pepparoni createPepparoni() {
        return new SlicedPepparoni();
    }

    @Override
    public Clams createClams() {
        return new FreshClams();
    }
}
