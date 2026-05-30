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

        // 課堂練習1 - 建立一個類別 Area 用來計算面積
        // 屬性: int length, int width
        // 方法: calculateArea() 回傳型態為int, 計算長方形的面積 length * width
        // 還要實體化物件來測試方法的功能
        // Area area1 = new Area();
        // area1.length = 5;
        // area1.width = 3;
        // int areaResult1 = area1.calculateArea();
        // System.out.println("長方形的面積為: " + areaResult1);

        Area1 area2 = new Area1();
        area2.length = 5;
        area2.width = 3;
        int areaResult2 = area2.calculateArea();
        System.out.println("長方形的面積為: " + areaResult2);

        // 建構子使用
        Car1 car11 = new Car1("BMW", "Blue");
        System.out.println("車子的品牌: " + car11.brand);
        System.out.println("車子的顏色: " + car11.color);

        // 課堂練習2 - 改寫Area類別, 加入建構子來初始化length和width, 並且實體化物件來測試建構子的功能
        Area area1 = new Area(5, 3);
        int areaResult1 = area1.calculateArea();
        System.out.println("長方形的面積為: " + areaResult1);

        // 多載 (Overloading) - 同一個方法名稱, 但是參數不同
        // 長方形的面積計算
        int areaResult3 = area1.calculateArea();
        System.out.println("長方形的面積為: " + areaResult3);

        // 圓形的面積計算
        double areaResult4 = area1.calculateArea(5);
        System.out.println("圓形的面積為: " + areaResult4);

        // 三角形的面積計算
        double areaResult5 = area1.calculateArea(5, 3);
        System.out.println("三角形的面積為: " + areaResult5);
    }

}

// 可以寫進來一個類別, 但是這個類別不能是public的, 因為一個檔案只能有一個public類別
class Area1 {
    public int length;
    public int width;

    public int calculateArea() {
        int area = length * width;
        return area;
    }
}