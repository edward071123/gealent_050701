package junior.demo;

public class CDemo {
    public static void main(String[] args) {
        // 實體化物件 - 透過類別來創造一個物件
        // class -> object
        Car car1 = new Car();

        System.out.println("車子的品牌(還未寫入): " + car1.brand);
        // 寫入物件的屬性
        car1.brand = "Toyota";
        car1.color = "Red";
        car1.year = 2020;
        car1.numberPlate = "ABC-1234";

        // 讀取物件的屬性
        System.out.println("車子的品牌(已經寫入): " + car1.brand);

        // 呼叫(調用)物件的方法
        car1.start();
        car1.stop();
    }
}
