package junior;

public class MethodPlus {
    public static void main(String[] args) {
        // 做一個印出金字塔的方法 我要可以輸入金字塔的層數
        printPyramid(5);

        // 羅馬數字轉阿拉伯數字的方法 回傳型態為int
        int result = romanToInt("MCMXCIV");
        System.out.println("羅馬數字 MCMXCIV 轉換為阿拉伯數字為: " + result);

        // findMax 方法 輸入一個整數的陣列 回傳陣列內的最大值
        // findMax(int[] numbers) 回傳型態為int
        int[] numbers = { 1, 5, 3, 9, 2 };
        int max = findMax(numbers);
        System.out.println("陣列內的最大值為: " + max);

    }

    public static void printPyramid(int level) {
        for (int i = 1; i <= level; i++) {
            // 排除偶數
            // (i % 2) => 偶數會得到餘數為0, 奇數會得到餘數為1
            if (i % 2 == 1) {
                System.out.print("第" + i + "層為奇數");

                // 印出空白
                int space = (level - i) / 2;
                for (int s = 1; s <= space; s++) {
                    System.out.print("_");
                }

                // 第i天 每天只能玩i次 (第i層 只能印出i次星星)
                for (int k = 1; k <= i; k++) {
                    System.out.print("*");
                }
                // 換行
                System.out.println("");
            }
        }
    }

    public static int romanToInt(String input) {
        int result = 0;
        int lastValue = 0;
        // 切割 "MCMXCIV" 變成 ['M', 'C', 'M', 'X', 'C', 'I', 'V']
        for (int i = input.length() - 1; i >= 0; i--) {
            // System.out.print(input.charAt(i));
            // charAt(i) 取得單一字元
            char inputChar = input.charAt(i);
            // 呼叫translate方法 輸入單一字元 回傳對應的數值
            int getValue = translate(inputChar);
            if (lastValue > getValue) {
                result -= getValue;
            } else {
                result += getValue;
            }

            lastValue = getValue;
        }
        return result;
    }

    public static int translate(char c) {
        int getValue = 0;
        switch (c) {
            case 'I':
                getValue = 1;
                break;
            case 'V':
                getValue = 5;
                break;
            case 'X':
                getValue = 10;
                break;
            case 'L':
                getValue = 50;
                break;
            case 'C':
                getValue = 100;
                break;
            case 'D':
                getValue = 500;
                break;
            default:
                getValue = 1000;
                break;
        }
        return getValue;
    }

    public static int findMax(int[] numbers) {
        int max = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] > max) {
                max = numbers[i];
            }
        }
        // 回傳最大值
        return max;
    }
}
