package senior;

public class Pyramid {
    public static void main(String[] args) {
        int max = 7;
        //// 第一步先思考 由上而下印出想要的層數 確認沒問題後在第二步
        // for (int i = 1; i <= max; i++) {
        //     System.out.println("*");
        // }

        // // 第二步 用10天每天都能玩10次的雙迴圈思考
        // for (int i = 1; i <= max; i++) {
        //     for (int k = 1; k <= max; k++) {
        //         System.out.println("" + i + "x" + k);
        //     }
        // }

        // // 第三步 開始橫向排列 結束後換行
        // for (int i = 1; i <= max; i++) {
        //     for (int k = 1; k <= max; k++) {
        //         System.out.print("" + i + "x" + k);
        //     }
        //     // 換行
        //     System.out.println("");
        // }

        // // 第四步 階梯式 開始去除不要的
        // for (int i = 1; i <= max; i++) {
        //     for (int k = 1; k <= i; k++) {
        //         System.out.print("" + i + "x" + k);
        //     }
        //     // 換行
        //     System.out.println("");
        // }

        // // 第五步 把數值改成 *
        // for (int i = 1; i <= max; i++) {
        //     for (int k = 1; k <= i; k++) {
        //         System.out.print("*");
        //     }
        //     // 換行
        //     System.out.println("");
        // }

        // // 第六步 去除偶數排
        // for (int i = 1; i <= max; i++) {
        //     // 層數要奇數才印出
        //     if (i % 2 != 0) {
        //         for (int k = 1; k <= i; k++) {
        //             System.out.print("*");
        //         }
        //         // 換行
        //         System.out.println("");
        //     }
        // }

        // // 第七步 前面開始印出空白, 先用下底線(_)代替空白先來開發驗證 開始推導印出空白的公式
        // //
        // // 最大數 金字塔  空白 星星                ( max - i ) / 2
        // // max:3 _*      1   1    3 - 1 ? 1  => ( 3   - 1 ) / 2 = 1
        // // max:3 ***     0   3    3 - 3 ? 0  => ( 3   - 1 ) / 2 = 0
        // // 
        // // max:5 __*     2   1    5 - 1 ? 2  => ( 5   - 1 ) / 2 = 2
        // // max:5 _***    1   3    5 - 3 ? 1  => ( 5   - 3 ) / 2 = 1
        // // max:5 *****   0   5    5 - 5 ? 0  => ( 5   - 7 ) / 2 = 0
        // //
        // // max:7 ___*    3   1    7 - 1 ? 3  => ( 7   - 1 ) / 2 = 3
        // // max:7 __***   2   3    7 - 3 ? 2  => ( 7   - 3 ) / 2 = 2
        // // max:7 _*****  1   5    7 - 5 ? 1  => ( 7   - 5 ) / 2 = 1
        // // max:7 ******* 0   7    7 - 7 ? 0  => ( 7   - 7 ) / 2 = 0
        // // 
        // // 根據上面的樣式 用最大數跟印出星星數 要如何才能得到每層所需的空白數？

        for (int i = 1; i <= max; i++) {
            // 層數要奇數才印出
            if (i % 2 != 0) {
                // 印出空白
                // 先找出所需的空白數
                int space = (max - i) / 2;
                for (int k = 1; k <= space; k++) {
                    System.out.print("_");
                }

                // 印出*
                for (int k = 1; k <= i; k++) {
                    System.out.print("*");
                }
                // 換行
                System.out.println("");
            }
        }
    }
}
