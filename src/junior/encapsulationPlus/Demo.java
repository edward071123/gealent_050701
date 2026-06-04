package junior.encapsulationPlus;

public class Demo {
    public static void main(String[] args) {
        AutomatedTellerMachine atm1 = new AutomatedTellerMachine("a001", "123", 1000.0);
        System.out.println("Account: " + atm1.getAccount());
        System.out.println("Password: " + atm1.getPassword());
        System.out.println("Balance: " + atm1.getBalance());

        atm1.transaction();
    }
}
