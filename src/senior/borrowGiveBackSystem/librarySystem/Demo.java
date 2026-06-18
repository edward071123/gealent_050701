package senior.borrowGiveBackSystem.librarySystem;

import java.util.List;
import java.util.Scanner;

public class Demo {
    public static void main(String[] args) {
        // 1.
        // 把提款機的部分 搬過來 改寫成
        // ===== 校園圖書館系統 =====
        // 1. 查看所有書籍
        // 2. 借書
        // 3. 還書
        // 0. 離開

        // 2.
        // 建立db/book.txt 內容如下面四行
        // P,1,Java入門,張三,false,Java,aa
        // P,2,Python程式設計,王五,true,Python,NULL
        // N,3,哈利波特,J.K.Rowling,true,奇幻,NULL
        // N,4,達文西密碼,丹布朗,true,歷史,NULL

        // 3.
        // 建立Book.java 有以下屬性, 且有getter, setter
        // String type(種類) => 預設B, getType() 方法 => 回傳B
        // int number (編號)
        // String title (書名)
        // String author (作者)
        // boolean available (是否可借)
        // String borrowUser (誰借走)

        // 建立建構子傳入以上屬性的資料進來

        // 4.
        // 建立ProgrammingBook.java(程式類別的書) 繼承Book.java 且有以下屬性
        // String language (程式語言)

        // 覆寫 getType() 方法 => 回傳P
        // 建立建構子傳入以上屬性的資料進來

        // 5.
        // 建立NovelBook.java(小說類別的書) 繼承Book.java 且有以下屬性
        // String category (類別)

        // 覆寫 getType() 方法 => 回傳N
        // 建立建構子傳入以上屬性的資料進來
        //
        boolean working = true; // true 可以一直在系統內運行, false 代表離開系統

        String dataFilePath = "src/senior/borrowGiveBackSystem/db/book.txt";
        LibraryService service = null;
        try {
            service = new LibraryService(dataFilePath);
        } catch (Exception e) {
            working = false;
            System.out.println(e.getMessage());
        }

        Scanner sc = new Scanner(System.in);

        while (working == true) {
            System.out.println("===== 校園圖書館系統 =====");
            System.out.println("1. 查看所有書籍");
            System.out.println("2. 借書");
            System.out.println("3. 還書");
            System.out.println("0. 離開");
            System.out.print("請輸入操作數字：");

            String choice = sc.nextLine();

            try {
                if (choice.equals("1")) {
                    // 查看所有書籍
                    printBookInfo(service.getAllBooks());

                } else if (choice.equals("2")) {
                    // 借書
                    System.out.print("請輸入借閱人姓名：");
                    String name = sc.nextLine();

                    System.out.print("請輸入書籍編號(數字)：");
                    int number = Integer.parseInt(sc.nextLine());

                    System.out.println(service.borrow(number, name));

                } else if (choice.equals("3")) {
                    // 還書
                    System.out.print("請輸入書籍編號(數字)：");
                    int number = Integer.parseInt(sc.nextLine());
                    System.out.println(service.giveBack(number));

                } else if (choice.equals("0")) {
                    System.out.println("===== 離開 =====");
                    working = false;

                } else {
                    System.out.println("===== 輸入錯誤 =====");

                }
            } catch (Exception e) {
                System.out.println("");
                System.out.println("=====操作錯誤====");
                System.out.println(e.getMessage());
            }

        }

        sc.close();

        // String dataFilePath = "src/senior/borrowGiveBackSystem/db/book.txt";
        // try {
        // BookRepository b1 = new BookRepository(dataFilePath);
        // List<Book> books = b1.loadBooks();
        // System.out.println(books.get(1).getTitle());

        // } catch (Exception e) {
        // System.out.println(e.getMessage());
        // }

    }

    public static void printBookInfo(List<Book> books) {
        System.out.println("===== 書籍列表起始 =====");

        for (int i = 0; i < books.size(); i++) {
            System.out.println();
            System.out.println(books.get(i).getInfo());
        }

        System.out.println("===== 書籍列表結束 =====");
    }
}
