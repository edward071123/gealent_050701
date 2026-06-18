package senior.borrowGiveBackSystem.librarySystem;

public class Book {
    private String number;
    private String title;
    private String author;
    private boolean available;
    private String borrowUser;

    public Book(String number, String title, String author, boolean available, String borrowUser) {
        this.number = number;
        this.title = title;
        this.author = author;
        this.available = available;
        this.borrowUser = borrowUser;
    }

    public String getType() {
        return "B";
    }

    public String getNumber() {
        return this.number;
    }

    public String getTitle() {
        return this.title;
    }

    public String getAuthor() {
        return this.author;
    }

    public boolean isAvailable() {
        return this.available;
    }

    public String getBorrowUser() {
        return this.borrowUser;
    }

    public String getExtraInfo() {
        return "";
    }

    public String toFileSting() {
        return getType() + "," + getNumber() + "," + getTitle() + "," + getAuthor() + "," + isAvailable() + ","
                + getExtraInfo() + "," + getBorrowUser();
    }
}
