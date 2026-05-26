package junior;

public class MethodInfo {
    public static void main(String[] args) {
        printHello1();

        printHello2(4);
        printHello2(5);
        printHello2(6);

        printHello3("Edward");
        printHello3("David");

        printHello4("Allen", 7);
        printHello4("Ben", 8);

        String[] names = { "Edward1", "David1", "Allen1" };
        printHello5(names);
    }

    // 方法(Method)的定義 其他語言或許是function
    // void : 沒有回傳值
    // static : 靜態的, 不需要實體化物件就可以使用
    // 前綴字 靜態 回傳型態 方法名稱(輸入型別 輸入參數) {
    // 方法內容
    // }

    public static void printHello1() {
        System.out.println("Hello, World1!");
        System.out.println("Hello, World2!");
        System.out.println("Hello, World3!");
    }

    public static void printHello2(int num) {
        System.out.println("Hello, World" + num + "!");
    }

    // 課堂練習1: 寫一個方法printHello3, 輸入一個字串, 印出 "Hello, " + 輸入的字串 + "!"
    public static void printHello3(String name) {
        System.out.println("Hello, World " + name + "!");
    }

    public static void printHello4(String name, int num) {
        System.out.println("Hello, World " + name + " " + num + "!");
    }

    // 課堂練習2: 寫一個方法printHello5, 輸入一個字串的陣列, 印出 "Hello, " + 陣列內的每個字串 + "!"
    // 字串的陣列: String[] names = {"Edward", "David", "Allen"};
    public static void printHello5(String[] names) {
        for (int i = 0; i < names.length; i++) {
            System.out.println("Hello, " + names[i] + "!");
        }
    }

}
