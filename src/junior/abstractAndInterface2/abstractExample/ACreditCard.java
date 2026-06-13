package junior.abstractAndInterface2.abstractExample;

public class ACreditCard extends APayment {
    public ACreditCard() {
        // 因為父類別建構子有傳入參數所以必須寫出super()
        super("信用卡", 0.03);
    }

    // 必須實作出父類別的抽象方法:processPayment()
    @Override
    public void processPayment(int amount, int fee) {
        System.out.println("正在進行信用卡授權驗證...");
        System.out.println("信用卡扣款成功，金額: " + amount + "，手續費: " + fee);
    }

}
