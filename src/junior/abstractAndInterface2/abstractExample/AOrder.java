package junior.abstractAndInterface2.abstractExample;

public class AOrder {
    private ALinePay linePay;
    private ACreditCard creditCard;

    // 多載建構子注入傳入的物件, 初始化的時候就可以使用LinePay付款
    // 建構子注入傳入的物件, 初始化的時候就可以使用LinePay付款
    // 類別去使用另外一個物件(物件注入)
    public AOrder(ALinePay linePay) {
        this.linePay = linePay;
        // this.linePay = new ALinePay();
    }

    // 多載建構子注入傳入的物件, 初始化的時候就可以使用信用卡付款
    // 建構子注入傳入的物件, 初始化的時候就可以使用信用卡付款
    public AOrder(ACreditCard creditCard) {
        this.creditCard = creditCard;
        // this.creditCard = new ACreditCard();
    }

    // LinePay呼叫pay()
    public void createOrderLinePay(int amount) {
        System.out.println("建立訂單(LinePay)");
        this.linePay.pay(amount);
    }

    // 信用卡呼叫pay()
    public void createOrderCreditCard(int amount) {
        System.out.println("建立訂單(CreditCard)");
        this.creditCard.pay(amount);
    }

}
