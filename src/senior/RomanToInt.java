package senior;

public class RomanToInt {
    public static void main(String[] args) {
        System.out.println(romanToInt("MCMXCIV"));
    }
    
    public static int romanToInt(String s) {
        // 用兩個array根據相同index 對應
        // 羅馬字元array
        char symbol[]   = {'I' , 'V' , 'X' , 'L' , 'C' , 'D' , 'M'  };
        // 數字array
        int value[]     = { 1  ,  5  ,  10 ,  50 , 100 , 500 , 1000 };
        // 準備分解單一字元
        String str = new String(symbol);
        // 結果數
        int result = 0;
        // 上一個數
        int lastValue   = 0;
        for (int i = 0; i < s.length(); i++) {
            // 取得字元
            char getOne = s.charAt(i);
            // 搜尋字元陣列內的index
            int getIndex = str.indexOf(getOne);
            // 對應數字陣列的值 且取出
            int getValue = value[getIndex];


            // There are six instances where subtraction is used:
            // 有有六個地方用到了減法
            // I can be placed before V (5) and X (10) to make 4 and 9. 
            // I 在 Ｖ(5) 和 X(10) 前面就會變成 4 跟 9 
            // X can be placed before L (50) and C (100) to make 40 and 90. 
            // X 在 L(50) 和 C(100) 前面就會變成 40 跟 90 
            // C can be placed before D (500) and M (1000) to make 400 and 900.
            // C 在 D(500) 和 M(1000) 前面就會變成 400 跟 900 

            // 開始思考
            // III          => 1 + 1 + 1 = 3 (o)
            // LVIII        => 50 + 5 + 1 + 1 + 1 = 58 (o)
            
            // MCMXCIV      => 1000 +  100 + 1000  +  10 + 100  +  1 + 5  = 2216 (x)
            // M CM XC IV   => 1000 + (100 - 1000) + (10 - 100) + (1 - 5) = 1994 (o)
            //              => 1000 +     900      +     90     +    4    = 1994

            // 如果上一個數字小於目前得到的數字
            if (lastValue < getValue) {
                // 由於上一次result已經先加過lastValue, 所以先扣回來
                result = result - lastValue;
                // 根據減法的規則算出正常應該加進去的數值 (後面(目前得到的) - 前面(上次得到的))
                int subtraction = getValue - lastValue;
                
                result = result + subtraction;
            } else {
                // 正常的話會每次加入
                result += getValue;
            }

            // 這次得到的數用完就變成上次得到的數
            lastValue = getValue;
        }
        
        return result;
    }
}
