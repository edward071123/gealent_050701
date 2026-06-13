package junior.abstractAndInterface2.interfaceExample;

public class ILinePay implements IPayment {
    @Override
    public void pay(int amount) {
        System.out.println("正在跳轉至 LinePay 應用程式...");
        System.out.println("LinePay 支付成功，金額: " + amount);
    }

}
