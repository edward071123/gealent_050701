package junior.abstractAndInterface2.abstractExample;

public class ALinePay extends APayment {
    public ALinePay() {
        // 因為父類別建構子有傳入參數所以必須寫出super()
        super("LinePay", 0.01);
    }

    // 必須實作出父類別的抽象方法:processPayment()
    @Override
    public void processPayment(int amount, int fee) {
        System.out.println("正在跳轉至 LinePay 應用程式...");
        System.out.println("LinePay 支付成功，金額: " + amount + "，手續費: " + fee);
    }

}
