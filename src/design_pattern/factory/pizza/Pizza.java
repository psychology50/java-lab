package design_pattern.factory.pizza;

public interface Pizza {
    int getPrice();
    default void prepare() {
        System.out.println("Preparing " + getClass().getSimpleName());
    }
    default void bake() {
        System.out.println("Baking " + getClass().getSimpleName());
    }
    default void cut() {
        System.out.println("Cutting " + getClass().getSimpleName());
    }
    default void box() {
        System.out.println("Boxing " + getClass().getSimpleName());
    }
}
