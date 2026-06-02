package junior.encapsulationPlus;

public class AutomatedTellerMachine {
    private String account;
    private String password;
    private double balance;

    public AutomatedTellerMachine(String account, String password, double balance) {
        setAccount(account);
        setPassword(password);
        setBalance(balance);
    }

    public String getAccount() {
        return this.account;
    }

    public void setAccount(String account) {
        if (account.length() > 0) {
            this.account = account;
        } else {
            System.out.println("帳號不能為空");
        }
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        if (password.length() > 0) {
            this.password = password;
        } else {
            System.out.println("密碼不能為空");
        }
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        if (balance >= 0) {
            this.balance = balance;
        } else {
            System.out.println("餘額不能為負數");
        }
    }

    public void transaction() {
        // 交易邏輯
        // 輸入帳號密碼
        // 驗證帳號密碼 輸入三次就鎖帳號
        // while loop 讓使用者循環選擇交易項目
        // 1:提領,2:存款,3:餘額查詢,4:結束
        // 提領:防呆 總存款 金額 要大於等於 提領金額
        // 存款:防呆 存款金額要大於0
    }
}
