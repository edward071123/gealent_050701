package junior.collectionInfo;

import java.util.ArrayList;
import java.util.List;

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

    }
}
