package basic;

// 變數的基本型別
// 快速輸出(印出):      sout + tab => System.out.println();
// 快速輸出(程式進入點): main + tab => System.out.println();
public class BasicDeclare {
    public static void main(String[] args) throws Exception {
        // int: 整數, 範圍: -2,147,483,648 ~ 2,147,483,647
        int x1 = 2147483647;
        System.out.println("整數(int): " + x1);
        
        // byte: 整數, 範圍: -128 ~ 127
        byte x2 = 127;
        System.out.println("整數(byte): " + x2);

        //  溢位（Overflow）
        x2++;   // +1的寫法
        System.out.println("溢位(Overflow): 整數(byte) + 1 後: " + x2);
        //... → -2 → -1 → 0 → 1 → ... → 126 → 127
        //                                    ↓
        //                                  -128


        // long: 整數, 範圍: -9,223,372,036,854,775,808 ~ 9,223,372,036,854,775,807
        long x3 = 9223372036854775807L;
        System.out.println("整數(long): " + x3);


        // float: 32位元浮點數, 需要加上F
        float weight1 = 49.4F;
        System.out.println("浮點數(float): " + weight1);

        // double: 64位元浮點數, 預設型別
        double weight2 = 49.49;
        System.out.println("浮點數(double): " + weight2);

        // char: 字元, 1個字元, 單引號
        char c1 = 'Y';
        System.out.println("字元(char): " + c1);

        // String: 字串, 多個字元, 雙引號
        String name = "Edward";
        System.out.println("字串(String): " + name);

        // boolean: 布林, true/false
        boolean isStudent = false;
        System.out.println("布林(boolean): " + isStudent);
    }
}
