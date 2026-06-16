package senior.borrowGiveBackSystem.librarySystem;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/* 
 * 只負責讀寫檔案
 */
public class BookRepository {

    private String dataFilePath;

    public BookRepository(String dataFilePath) {
        this.dataFilePath = dataFilePath;
    }
 
    // 讀取
    public List<Book> loadBooks() throws Exception {
        List<Book> books = new ArrayList<>();

        try {
            List<String> lines = Files.readAllLines(Paths.get(this.dataFilePath));

            for (String line : lines) {
                String[] data = line.split(",", -1);
                if (data.length != 7) {
                    throw new Exception("books.txt 格式錯誤: " + line);
                }

                String type         = data[0];
                String number       = data[1];
                String title        = data[2];
                String author       = data[3];
                boolean available   = Boolean.parseBoolean(data[4]);
                String extra        = data[5];
                String borrowUser   = data[6];

                if (type.equals("P")) {
                    books.add(new ProgrammingBook(number, title, author, available, extra, borrowUser));
                } else if (type.equals("N")) {
                    books.add(new NovelBook(number, title, author, available, extra, borrowUser));
                }
            }

        } catch (Exception e) {
            String filePath = Paths.get(this.dataFilePath).toAbsolutePath().toString();
            throw new Exception("讀取 books.txt 失敗: " + e.getMessage()
                    + ", 目前程式讀取檔案的路徑為: " + filePath);
        }

        return books;
    }

    // 寫入(儲存)
    public void saveBooks(List<Book> books) throws Exception{
        List<String> lines = new ArrayList<>();

        for (Book book : books) {
            lines.add(book.toFileString());
        }

        try {
            Files.write(Paths.get(this.dataFilePath), lines);
        } catch (Exception e) {
            throw new Exception("儲存 books.txt 失敗, 原因為: " + e.getMessage());
        }
    }
}
