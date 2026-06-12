package junior.abstractAndInterface2;

import junior.abstractAndInterface2.abstractExample.ACreditCard;
import junior.abstractAndInterface2.abstractExample.ALinePay;
import junior.abstractAndInterface2.abstractExample.AOrder;

public class Demo {
    public static void main(String[] args) {
        


        // 使用LinePay付款(ALinePay) 物件(object)先比喻成模組(module)
        ALinePay linePayPayment = new ALinePay();
        // new 就是類別(class)已經被生產出來 變成物件(object)
        AOrder order1 = new AOrder(linePayPayment);
        order1.createOrderLinePay(1000);

        System.out.println("=================================================");
        // 課堂練習 AOrder order2 使用信用卡付款(ACreditCard)
        ACreditCard creditCardPayment = new ACreditCard();
        AOrder order2 = new AOrder(creditCardPayment);
        order2.createOrderCreditCard(2000);

    }
}
