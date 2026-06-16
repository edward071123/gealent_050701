package senior.borrowGiveBackSystem.librarySystem;

import java.util.List;
import java.util.Scanner;

public class Demo {
    public static void main(String[] args) throws Exception {
        /*
        librarySystem
            ├── main (主要功能制定)
                ├── IBorrow.java
            ├── db (資料庫)
                ├── books.txt
            ├── librarySystem (借還書系統功能實作)
                ├── Demo.java
                ├── Book.java
                ├── ProgrammingBook.java
                ├── NovelBook.java
                ├── BookRepository.java
                └── LibraryService.java
        */

        // 簡短資料庫路徑
        String dataFilePath = "src/senior/borrowGiveBackSystem/db/books.txt";        
        LibraryService service = null;
        boolean working = true;
        Scanner sc = new Scanner(System.in);

        try {
            service = new LibraryService(dataFilePath);
        } catch (Exception e) {
            working = false;
            System.out.println(e.getMessage());
        }

        while (working == true) {
            System.out.println();
            System.out.println("===== 校園圖書館系統 =====");
            System.out.println("1. 查看所有書籍");
            System.out.println("2. 借書");
            System.out.println("3. 還書");
            System.out.println("0. 離開");
            System.out.print("請輸入操作數字：");

            try {
                if (!sc.hasNextLine()) {
                    System.out.println("偵測到有異常輸入，系統結束");
                    working = false;
                    break;
                }

                String choice = sc.nextLine();
                if (choice.equals("1")) {
                    printBookInfo(service.getAllBooks());

                } else if (choice.equals("2")) {
                    printBookInfo(service.getAllBooks());

                    System.out.print("請輸入借閱人姓名：");
                    String name = sc.nextLine();

                    System.out.print("請輸入書籍編號(數字)：");
                    int number = Integer.parseInt(sc.nextLine());

                    System.out.println("");
                    System.out.println("==========處理中============");
                    System.out.println(service.borrow(number, name));
                    System.out.println("==========處理完畢============");

                } else if (choice.equals("3")) {
                    printBookInfo(service.getAllBooks());

                    System.out.print("請輸入書籍編號(數字)：");
                    int index = Integer.parseInt(sc.nextLine());
                    System.out.println("");
                    System.out.println("==========處理中============");
                    System.out.println(service.giveBack(index));
                    System.out.println("==========處理完畢============");

                } else if (choice.equals("0")) {
                    System.out.println("系統結束");
                    working = false;
                } else {
                    System.out.println("您輸入的數字錯誤,請重新輸入");
                }

            } catch (NumberFormatException e) {
                System.out.println("");
                System.out.println("=====操作錯誤====");
                System.out.println("請輸入數字");

            } catch (Exception e) {
                System.out.println("");
                System.out.println("=====操作錯誤====");
                System.out.println(e.getMessage());
            }

        }

        sc.close();
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
