package senior.borrowGiveBackSystem.librarySystem;

public class ProgrammingBook extends Book {
    private String language;

    public ProgrammingBook(String number, String title, String author, boolean available, String borrowUser,
            String language) {
        super(number, title, author, available, borrowUser);
        this.language = language;
    }

    @Override
    public String getType() {
        return "P";
    }
}
