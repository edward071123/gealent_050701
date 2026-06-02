package junior.inheritanceIntroduction;

public class Cat extends Animal {

    @Override
    public void eat() {
        super.eat(); // 呼叫父類別的 eat 方法
        // System.out.println("Cat1 is eating.");
    }
}
