package junior.abstractAndInterface2;

import junior.abstractAndInterface2.abstractExample.*;
import junior.abstractAndInterface2.interfaceExample.*;

public class Demo {
    public static void main(String[] args) {
        // 耦合（Coupling） 指的是：
        // 一個類別對另一個類別的依賴程度。

        // 抽象類別適合處理：
        // 1. 共用欄位：付款名稱、手續費率
        // 2. 共用付款流程：驗證 -> 計算手續費 -> 執行付款 -> 顯示結果
        // 3. 共用方法實作：驗證金額、計算手續費、顯示結果
        // 4. 子類別只實作自己的 processPayment()
        System.out.println("===============抽象類別使用信用卡付款=================");
        AOrderService creditCardOrderService =
                new AOrderService(new ACreditCardPaymentService());
        creditCardOrderService.createOrder(-1);

        System.out.println("===============抽象類別使用LinePay付款=================");
        AOrderService linePayOrderService =
                new AOrderService(new ALinePayPaymentService());
        linePayOrderService.createOrder(1000);

        // 介面
        // 只要實做出自己的功能就好
        System.out.println("===============介面使用LinePay付款=================");
        IOrderService interfaceLinePayOrderService =
                new IOrderService(new ILinePayPaymentService());
        interfaceLinePayOrderService.createOrder(1000);

        System.out.println("===============介面使用信用卡付款=================");
        IOrderService interfaceCreditCardOrderService =
                new IOrderService(new ICreditCardPaymentService());
        interfaceCreditCardOrderService.createOrder(1000);
    }
}
