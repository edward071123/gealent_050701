package junior.exceptionInfo;

public class Atm {

    // 提款
    public void withdraw(int amount) {
        int balance = 100;
        if (amount <= 0) {
            // System.out.println("金額不能小於0");
            // return;
            throw new IllegalArgumentException("金額不能小於0");
        }

        if (amount > balance) {
            // System.out.println("餘額不足");
            // return;
            throw new ArithmeticException("餘額不足");
        }

        System.out.println("可以提領");
    }
}
