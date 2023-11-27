package design_pattern.strategy;

public class ClientStrategy {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();

        Item item1 = new Item("컴퓨터", 1000000);
        Item item2 = new Item("키보드", 50000);

        cart.addItem(item1);
        cart.addItem(item2);

        // 카카오 카드로 결제
        cart.pay(new KAKAOCardStrategy("홍길동", "1234-1234-1234-1234", "123", "12/24"));

        // 네이버 카드로 결제
        cart.pay(new NAVERCardStrategy("a@a.com", "1234"));
    }
}
