package junior.abstractAndInterface2;

import junior.abstractAndInterface2.abstractExample.ACreditCardPaymentService;
import junior.abstractAndInterface2.abstractExample.ALinePayPaymentService;
import junior.abstractAndInterface2.abstractExample.AOrderService;

public class Demo {
    public static void main(String[] args) {

        // 使用LinePay付款(ALinePayPaymentService)
        ALinePayPaymentService linePayPayment = new ALinePayPaymentService();
        // new 就是類別(class)已經被生產出來 變成物件(object)
        AOrderService order1 = new AOrderService(linePayPayment);
        order1.createOrderLinePay(1000);

        System.out.println("=================================================");
        // 課堂練習 AOrderService order2 使用信用卡付款(ACreditCardPaymentService)
        ACreditCardPaymentService creditCardPayment = new ACreditCardPaymentService();
        AOrderService order2 = new AOrderService(creditCardPayment);
        order2.createOrderCreditCard(2000);

    }
}
