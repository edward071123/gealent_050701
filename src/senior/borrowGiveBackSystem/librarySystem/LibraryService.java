package senior.borrowGiveBackSystem.librarySystem;

import java.util.ArrayList;
import java.util.List;

import senior.borrowGiveBackSystem.main.IBorrow;

/* 
 * 只負責借書/還書規則
 */
public class LibraryService implements IBorrow {

    private BookRepository repository;
    private List<Book> books;

    public LibraryService(String dataFilePath) throws Exception {
        this.repository = new BookRepository(dataFilePath);
        books = repository.loadBooks();
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(books);
    }

    private Book checkBook(int number) throws Exception {
        if (number < 1) {
            throw new Exception("輸入的書籍編號異常");
        }
        
        Book targetBook = null;

        for (Book book : books) {
            if (Integer.parseInt(book.getNumber()) == number) {
                targetBook = book;
                break;
            }
        }

        if (targetBook == null) {
            throw new Exception("書籍不存在");
        }
        
        return targetBook;
    }

    @Override
    public String borrow(int number, String borrowUser) throws Exception {
        if (borrowUser.isEmpty()) {
            throw new Exception("借閱人姓名不能為空");
        }

        if (borrowUser.equals("NULL")) {
            throw new Exception("借閱人姓名不能為NULL");
        }
        
        Book targetBook = checkBook(number);

        if (!targetBook.isAvailable()) {
            return "編號: " + number + ",書名: " + targetBook.getTitle() + " 已被" + targetBook.getBorrowUser() + "借走";
        } 

        // 更改為不可借的狀態
        targetBook.setAvailable(false);
        // 更改誰借走
        targetBook.setBorrowUser(borrowUser);
        repository.saveBooks(books);
        return borrowUser + " 借閱 編號: " + number + ",書名: " + targetBook.getTitle() + " 成功";
    }

    @Override
    public String giveBack(int number) throws Exception {
        Book targetBook = checkBook(number);
        
        if (targetBook.isAvailable()) {
            return "編號: " + number + ",書名: " + targetBook.getTitle() + "並無借出";
        }
        
        // 更改為不可借的狀態
        targetBook.setAvailable(true);
        // 更改無人借走
        targetBook.setBorrowUser("NULL");
        repository.saveBooks(books);
        return "編號: " + number + ",書名: " + targetBook.getTitle()  + " 歸還成功";
        
    }
}
