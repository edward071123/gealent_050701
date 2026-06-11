package junior.abstractAndInterface2.abstractExample;

public class AOrderService {
    private ALinePayPaymentService linePayPaymentService;
    private ACreditCardPaymentService creditCardPaymentService;

    // 建構子注入傳入的物件, 初始化的時候就可以使用LinePay付款
    // 類別去使用另外一個物件(物件注入)
    public AOrderService(ALinePayPaymentService linePayPaymentService) {
        this.linePayPaymentService = linePayPaymentService;
        // this.linePayPaymentService = new ALinePayPaymentService();
    }

    public AOrderService(ACreditCardPaymentService creditCardPaymentService) {
        this.creditCardPaymentService = creditCardPaymentService;
        // this.creditCardPaymentService = new ACreditCardPaymentService();
    }

    public void createOrderLinePay(int amount) {
        System.out.println("建立訂單(LinePay)");
        this.linePayPaymentService.pay(amount);
    }

    public void createOrderCreditCard(int amount) {
        System.out.println("建立訂單(CreditCard)");
        this.creditCardPaymentService.pay(amount);
    }

}
