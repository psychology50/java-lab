package design_pattern.strategy;

public class KAKAOCardStrategy implements PaymentStrategy {
    private String name;
    private String cardNumber;
    private String cvc;
    private String dateOfExpiry;

    public KAKAOCardStrategy(String name, String cardNumber, String cvc, String dateOfExpiry) {
        this.name = name;
        this.cardNumber = cardNumber;
        this.cvc = cvc;
        this.dateOfExpiry = dateOfExpiry;
    }

    @Override
    public void pay(int amount) {
        System.out.println(amount + "원을 카카오 카드로 결제합니다.");
    }
}
