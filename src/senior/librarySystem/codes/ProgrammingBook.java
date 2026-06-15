package senior.librarySystem.codes;

public class ProgrammingBook extends Book {

    private String language;

    public ProgrammingBook(String title, String author, boolean available, String language) {
        super(title, author, available);
        this.language = language;
    }

    @Override
    public String getType() {
        return "P";
    }

    @Override
    public String getExtraInfo() {
        return language;
    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("類型：程式書");
        System.out.println("語言：" + language);
    }
}
