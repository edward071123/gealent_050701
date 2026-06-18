package senior.borrowGiveBackSystem.librarySystem;

public class NovelBook extends Book {
    private String category;

    public NovelBook(String number, String title, String author, boolean available, String borrowUser,
            String category) {
        super(number, title, author, available, borrowUser);
        this.category = category;
    }

    @Override
    public String getType() {
        return "N";
    }

    @Override
    public String getExtraInfo() {
        return this.category;
    }
}
