package junior.abstractAndInterface2;

import junior.abstractAndInterface2.abstractExample.ALinePayPaymentService;
import junior.abstractAndInterface2.abstractExample.AOrderService;

public class Demo {
    public static void main(String[] args) {
        ALinePayPaymentService linePayPayment = new ALinePayPaymentService();

        // new 就是類別(class)已經被生產出來 變成物件(object)
        AOrderService order1 = new AOrderService(linePayPayment);
        order1.createOrder(1000);

    }
}
