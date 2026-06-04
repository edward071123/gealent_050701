package junior.polymorphism;

public class HomeWork {
    // 課後練習: 飲料店點餐結帳系統

    // 父類別: Drink
    // 有三個子類別: BlackTea, MilkTea, Coffee
    // 每杯飲料都要有以下屬性皆是private 且用封裝處理(getter/setter)
    // 飲料名稱: name(String)
    // 飲料價格: price(double)
    // 甜度: sugarLevel(int), 0:無糖, 1:半糖, 2:全糖
    // 冰塊: iceLevel(int), 0:去冰, 1:微冰, 3:正常冰
    // Drink 有一個方法: calculatePrice(), 子類別都要覆寫
    // BlackTea: 基本價格 30 元
    // MilkTea: 基本價格 45 元，如果加珍珠 + 10元, ＊所以有多屬性,加珍珠:addPearl(boolean)
    // Coffee: 基本價格 55 元，如果大杯 + 15元, ＊所以有多屬性, 大杯:largeSize(boolean)

    // 建立一個類別: OrderService
    // 裡面兩個建構子
    // OrderService(Drink drink) => 代表只點一杯, 印出飲料 x 1 金額:xx
    // OrderService(Drink drink, int quantity) => 代表點了多杯, 印出飲料 x quantity 金額:xx

    // Main(程式進入點) 內用多型 + array + for 印出
    // 名稱, 甜度, 冰塊, 價格
    // 用飲料的價位 且判斷 飲料超過60元 後面接著輸出"高價飲料", 否則為"一般飲料"
    // 總共訂了幾杯
    // 總金額

    // 輸出結果如下:
    // ==========
    // 名稱：紅茶
    // 甜度：2
    // 冰塊：1
    // 價格：30
    // 一般飲料

    // ==========
    // 名稱：奶茶
    // 甜度：3
    // 冰塊：2
    // 價格：55
    // 一般飲料

    // ==========
    // 名稱：咖啡
    // 甜度：0
    // 冰塊：0
    // 價格：70
    // 高價飲料

    // ==========
    // 奶茶 x1 金額:45
    // 紅茶 x3 金額:90
    // 咖啡 x2 外帶 金額:140

    // ==========
    // 總金額：155 元

}
