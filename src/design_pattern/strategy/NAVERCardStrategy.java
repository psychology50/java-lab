package design_pattern.strategy;

public class NAVERCardStrategy implements PaymentStrategy {
    private String email;
    private String password;

    public NAVERCardStrategy(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public void pay(int amount) {
        System.out.println(amount + "원을 네이버 카드로 결제합니다.");
    }
}
