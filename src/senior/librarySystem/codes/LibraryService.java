package senior.librarySystem.codes;

import java.util.List;

public class LibraryService {

    private BookRepository repository = new BookRepository();
    private List<Book> books;

    public LibraryService() {
        books = repository.loadBooks();
    }

    public void showAllBooks() {
        System.out.println("===== 書籍列表起始 =====");

        for (int i = 0; i < books.size(); i++) {
            System.out.println();
            System.out.println((i + 1) + ".");
            books.get(i).printInfo();
        }

        System.out.println("===== 書籍列表結束 =====");
    }

    public void borrowBook(int index, String studentName) {
        if (index < 1 || index > books.size()) {
            System.out.println("書籍不存在");
            return;
        }

        Book book = books.get(index - 1);
        book.borrow(studentName);

        repository.saveBooks(books);
    }

    public void returnBook(int index) {
        if (index < 1 || index > books.size()) {
            System.out.println("書籍不存在");
            return;
        }

        Book book = books.get(index - 1);
        book.returnBook();

        repository.saveBooks(books);
    }
}
