package senior.librarySystem.codes;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class BookRepository {

    private final String FILE_NAME = "src/senior/librarySystem/db/books.txt";

    public List<Book> loadBooks() {
        List<Book> books = new ArrayList<>();

        try {
            List<String> lines = Files.readAllLines(Paths.get(FILE_NAME));

            for (String line : lines) {
                String[] data = line.split(",");

                String type = data[0];
                String title = data[1];
                String author = data[2];
                boolean available = Boolean.parseBoolean(data[3]);
                String extra = data[4];

                if (type.equals("P")) {
                    books.add(new ProgrammingBook(title, author, available, extra));
                } else if (type.equals("N")) {
                    books.add(new NovelBook(title, author, available, extra));
                }
            }

        } catch (Exception e) {
            // System.out.println(
            //     Paths.get(FILE_NAME).toAbsolutePath()
            // );
            System.out.println("讀取 books.txt 失敗，請確認檔案是否存在");
        }

        return books;
    }

    public void saveBooks(List<Book> books) {
        List<String> lines = new ArrayList<>();

        for (Book book : books) {
            lines.add(book.toFileString());
        }

        try {
            Files.write(Paths.get(FILE_NAME), lines);
        } catch (Exception e) {
            System.out.println("儲存 books.txt 失敗");
        }
    }
}
