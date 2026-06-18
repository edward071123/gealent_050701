package senior.borrowGiveBackSystem.librarySystem;

import java.util.ArrayList;
import java.util.List;

import senior.borrowGiveBackSystem.main.IBorrowGiveBack;

public class LibraryService implements IBorrowGiveBack {
    private BookRepository repository;
    private List<Book> books;

    public LibraryService(String dataFilePath) throws Exception {
        this.repository = new BookRepository(dataFilePath);
        books = repository.loadBooks();
    }

    // 查看所有的書
    public List<Book> getAllBooks() {
        return new ArrayList<>(books);
    }

    // 借
    @Override
    public String borrow(int number, String borrowUser) throws Exception {
        return "";
    }

    // 還
    @Override
    public String giveBack(int number) throws Exception {
        return "";
    }
}
