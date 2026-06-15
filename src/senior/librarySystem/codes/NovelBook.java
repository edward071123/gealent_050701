package senior.librarySystem.codes;

public class NovelBook extends Book {

    private String category;

    public NovelBook(String title, String author, boolean available, String category) {
        super(title, author, available);
        this.category = category;
    }

    @Override
    public String getType() {
        return "N";
    }

    @Override
    public String getExtraInfo() {
        return category;
    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("類型：小說");
        System.out.println("分類：" + category);
    }
}
