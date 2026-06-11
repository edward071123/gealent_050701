package junior.abstractAndInterface1;

public class Demo {
    public static void main(String[] args) {
        // 抽象 + 介面的混用
        Iphone i14 = new Iphone("iPhone 14");
        i14.powerOn();
        i14.display();
        i14.charge();

        Android pixel10pro = new Android("pixel 10 pro");
        pixel10pro.powerOn();
        pixel10pro.display();
        pixel10pro.charge();

    }
}
