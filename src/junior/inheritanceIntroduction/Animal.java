package junior.inheritanceIntroduction;

public class Animal {
    public void eat() {
        System.out.println("Animal1 is eating.");
    }

    // final 關鍵字用於表示這個方法不能被覆寫(Override)
    public final void sleep() {
        System.out.println("Animal1 is sleeping.");
    }
}
