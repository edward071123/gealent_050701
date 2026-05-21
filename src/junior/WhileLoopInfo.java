package junior;

import java.util.Scanner;

public class WhileLoopInfo {
    public static void main(String[] args) {
        // 先實體化一個Scanner物件
        // Scanner sc = new Scanner(System.in);

        // System.out.println("請輸入一個數字");
        // int getInputNum = sc.nextInt();
        // System.out.println("您輸入的數字為:" + getInputNum);

        // // 專門用來吃掉殘留的 Enter 鍵！
        // sc.nextLine();

        // System.out.println("請輸入一組文字");
        // String getInputText = sc.nextLine();
        // System.out.println("您輸入的文字為:" + getInputText);

        // sc.close();

        // while(執行條件) {

        // }

        // System.out.println("for 迴圈開始");
        // for (int i = 0; i <= 10; i++) {
        // System.out.println(i);
        // }

        // System.out.println("for 迴圈結束");
        // System.out.println("while 迴圈開始");
        // int z = 0;
        // while (z <= 10) {
        // System.out.println(z);
        // z++;
        // }
        // System.out.println("while 迴圈結束");

        // 設定結束的起始條件: true:真的結束, false:還沒結束
        // boolean end = false;

        // Scanner sc = new Scanner(System.in);
        // // 總存款金額
        // int totalMoney = 0;
        // while (end == false) {
        // System.out.println("請輸入要辦理項目,1:提領,2:存款,3:餘額查詢,4:結束");
        // int getInputNum = sc.nextInt();

        // if (getInputNum == 1) {
        // // 防呆 總存款金額 要大於等於 提領金額
        // if (totalMoney >= 1000) {
        // totalMoney -= 1000;
        // System.out.println("您已提領1000元成功");
        // } else {
        // System.out.println("您的餘額不足");
        // }
        // } else if (getInputNum == 2) {
        // totalMoney += 1000;
        // System.out.println("您已存款1000元成功");
        // } else if (getInputNum == 3) {
        // System.out.println("您的餘額為" + totalMoney + "元");
        // } else if (getInputNum == 4) {
        // System.out.println("謝謝您的使用");
        // end = true;
        // } else {
        // System.out.println("您輸入的數字錯誤,請重新輸入");
        // }

        // System.out.println("這回合已經結束");
        // }

        // sc.close();

        // 課堂練習 用while 迴圈 讓使用者 輸入七個不重複的數 (1 ~ 100) 存到陣列 再用for 迴圈印出這七個數字
        Scanner sc2 = new Scanner(System.in);
        // while的起始條件
        int count = 0;

        int[] numbers = new int[7];

        while (count < 7) {
            System.out.println("請輸入第" + (count + 1) + "個數字");
            int getInputNum = sc2.nextInt();
            // 防呆 檢查一：檢查數字有沒有在 1 ~ 100 的範圍內
            if (getInputNum >= 1 && getInputNum <= 100) {
                System.out.println("您輸入的數字為:" + getInputNum);
                numbers[count] = getInputNum;
                count++;
            } else {
                System.out.println("您輸入的數字不在1 ~ 100 的範圍內,請重新輸入");
            }

        }

        for (int z = 0; z < numbers.length; z++) {
            System.out.print(numbers[z] + ",");
        }
        sc2.close();
    }
}
