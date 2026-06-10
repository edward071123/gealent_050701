package junior.exceptionInfo;

import java.util.List;

public class Demo {
    public static void main(String[] args) {
        runExample("正常提款", "300");
        runExample("輸入非數字", "三百");
        runExample("輸入錯誤金額", "-100");
        runExample("提款金額超過餘額", "1500");
    }

    private static void runExample(String title, String amountText) {
        System.out.println("================ " + title + " ================");

        AtmService atm = new AtmService(1000);
        List<String> messages = atm.withdraw(amountText);

        for (String message : messages) {
            System.out.println(message);
        }
    }
}
