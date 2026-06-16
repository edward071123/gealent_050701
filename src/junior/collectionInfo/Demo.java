package junior.collectionInfo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Demo {
    public static void main(String[] args) {
        /*
         * Collection（集合）可以一次存放很多筆資料。
         * 跟陣列相對比:
         * 陣列需要先劃出倉庫大小
         * 集合則像是有彈性的袋子
         */

        // ==================== 1. List ====================
        /*
         * 1. 有順序。
         * 2. 有索引，索引從 0 開始。
         * 3. 可以保存重複資料。
         * 
         * 可能的使用情境：
         * 1. 購物車商品，商品可能重複。
         * 2. 學生點名名單，需要保留順序。
         * 3. 播放清單，需要按照加入順序播放。
         */

        // List<String> fruits = new ArrayList<String>();
        List<String> fruits = new ArrayList<>();
        System.out.println("=====List0=====");
        System.out.println("List全部: " + fruits);
        System.out.println("List 目前長度: " + fruits.size());

        fruits.add("apple");
        fruits.add("banana");
        System.out.println("=====List1=====");
        System.out.println("List全部: " + fruits);
        System.out.println("List index 0: " + fruits.get(0));
        System.out.println("List index 1: " + fruits.get(1));
        System.out.println("List 目前長度: " + fruits.size());

        fruits.add("banana");
        System.out.println("=====List2=====");
        System.out.println("List全部: " + fruits);
        System.out.println("List 目前長度: " + fruits.size());

        fruits.remove("banana");
        System.out.println("=====List3=====");
        System.out.println("List全部: " + fruits);
        System.out.println("List 目前長度: " + fruits.size());

        fruits.set(0, "orange");
        System.out.println("=====List4=====");
        System.out.println("List全部: " + fruits);
        System.out.println("List 目前長度: " + fruits.size());

        fruits.add("apple");
        for (String one : fruits) {
            System.out.println("foreach: " + one);
        }

        fruits.clear();
        System.out.println("=====List5=====");
        System.out.println("List全部: " + fruits);
        System.out.println("List 目前長度: " + fruits.size());

        System.out.println("===============================================");
        // ==================== 2. Set ====================
        /*
         * Set 的特性：
         * 1. 不會保存重複資料。
         * 2. 沒有索引，不能使用 get(0)。
         * 
         * 可能的使用情境：
         * 1. 會員帳號，每個帳號不能重複。
         * 2. 商品標籤，不需要出現相同標籤。
         * 3. 活動簽到名單，同一個人只能簽到一次。
         */
        // Set<String> studentNames = new HashSet<String>();
        Set<String> studentNames = new HashSet<>();
        studentNames.add("小明");
        studentNames.add("小華");
        studentNames.add("小明");
        studentNames.add("小華");

        System.out.println("=====Set0=====");
        System.out.println("Set全部: " + studentNames);
        System.out.println("Set 目前長度: " + studentNames.size());
        studentNames.remove("小華");

        System.out.println("=====Set1=====");
        System.out.println("Set全部: " + studentNames);
        System.out.println("Set 目前長度: " + studentNames.size());

        System.out.println("=====Set2=====");
        System.out.println("Set全部: " + studentNames);
        System.out.println("Set 目前長度: " + studentNames.size());
        System.out.println("Set 是否有小明： " + studentNames.contains("小明"));

    }
}
