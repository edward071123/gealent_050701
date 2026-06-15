package senior.librarySystem.codes;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        /*
        librarySystem
            ├── db
                ├── books.txt
            ├── codes
                ├── Main.java
                ├── Book.java
                ├── ProgrammingBook.java
                ├── NovelBook.java
                ├── Borrowable.java
                ├── BookRepository.java
                └── LibraryService.java
        */

        Scanner sc = new Scanner(System.in);
        LibraryService service = new LibraryService();

        while (true) {
            System.out.println();
            System.out.println("===== 校園圖書館系統 =====");
            System.out.println("1. 查看所有書籍");
            System.out.println("2. 借書");
            System.out.println("3. 還書");
            System.out.println("0. 離開");
            System.out.print("請選擇：");

            String choice = sc.nextLine();

            if (choice.equals("1")) {
                service.showAllBooks();

            } else if (choice.equals("2")) {
                service.showAllBooks();
                try {
                    System.out.print("請輸入借閱人姓名：");
                    String name = sc.nextLine();

                    System.out.print("請輸入書籍編號：");
                    int index = Integer.parseInt(sc.nextLine());
                    
                    service.borrowBook(index, name);

                } catch (NumberFormatException e) {
                    System.out.println("請輸入數字");
                }

            } else if (choice.equals("3")) {
                service.showAllBooks();
                try {
                    System.out.print("請輸入書籍編號：");
                    int index = Integer.parseInt(sc.nextLine());

                    service.returnBook(index);

                } catch (NumberFormatException e) {
                    System.out.println("請輸入數字");
                }

            } else if (choice.equals("0")) {
                System.out.println("系統結束");
                break;

            } else {
                System.out.println("選項錯誤");
            }
        }

        sc.close();
    }
}
