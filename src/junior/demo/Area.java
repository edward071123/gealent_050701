package junior.demo;

public class Area {
    public int length;
    public int width;

    public Area(int length, int width) {
        // 這裡是建構子, 可以用來初始化物件的屬性
        // 這裡的length和width是區域變數, 不是屬性
        this.length = length;
        this.width = width;
    }

    // 多載 (Overloading) - 同一個方法名稱, 但是參數不同

    // 長方形的面積計算
    public int calculateArea() {
        int area = length * width;
        return area;
    }

    // 圓形的面積計算
    public double calculateArea(int r) {
        double area = r * r * 3.14;
        return area;
    }

    // 三角形的面積計算
    public double calculateArea(int base, int height) {
        double area = (base * height) / 2.0;
        return area;
    }
}
