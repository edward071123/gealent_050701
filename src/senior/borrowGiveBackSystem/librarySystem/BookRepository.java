package senior.borrowGiveBackSystem.librarySystem;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class BookRepository {
    // 檔案路徑
    private String dataFilePath;

    public BookRepository(String dataFilePath) {
        this.dataFilePath = dataFilePath;
    }

    // 讀檔
    public List<Book> loadBooks() throws Exception {
        List<Book> books = new ArrayList<>();

        try {
            // 讀book.txt的檔案每一行 存到lines這個集合內
            List<String> lines = Files.readAllLines(Paths.get(this.dataFilePath));

            for (String oneLine : lines) {
                // 字串切割
                String[] data = oneLine.split(",", -1);

                String type = data[0];
                String number = data[1];
                String title = data[2];
                String author = data[3];
                boolean available = Boolean.parseBoolean(data[4]);
                String extra = data[5];
                String borrowUser = data[6];
            }

        } catch (Exception e) {
            String filePath = Paths.get(this.dataFilePath).toAbsolutePath().toString();
            throw new Exception("讀取 books.txt 失敗: " + e.getMessage()
                    + ", 目前程式讀取檔案的路徑為: " + filePath);
        }

        return books;
    }

    // 寫入
}
