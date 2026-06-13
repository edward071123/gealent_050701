package junior.abstractAndInterface2.interfaceExample;

public class ICreditCard implements IPayment {

    @Override
    public void pay(int amount) {
        System.out.println("正在進行信用卡授權驗證...");
        System.out.println("信用卡扣款成功，金額: " + amount);
    }

}
