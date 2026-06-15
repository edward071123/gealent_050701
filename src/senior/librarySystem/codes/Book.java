package senior.librarySystem.codes;

public class Book implements IBorrowable {

    private String title;
    private String author;
    private boolean available;

    public Book(String title, String author, boolean available) {
        this.title = title;
        this.author = author;
        this.available = available;
    }

    public void borrow() {
        if (available) {
            available = false;
            System.out.println(title + " 借閱成功");
        } else {
            System.out.println(title + " 已被借走");
        }
    }

    // 多載 Overloading
    public void borrow(String studentName) {
        if (available) {
            available = false;
            System.out.println(studentName + " 借閱 " + title + " 成功");
        } else {
            System.out.println(title + " 已被借走");
        }
    }

    public void returnBook() {
        available = true;
        System.out.println(title + " 歸還成功");
    }

    public String getType() {
        return "B";
    }

    public String getExtraInfo() {
        return "";
    }

    public String toFileString() {
        return getType() + "," + title + "," + author + "," + available + "," + getExtraInfo();
    }

    public void printInfo() {
        System.out.println("書名：" + title);
        System.out.println("作者：" + author);
        System.out.println("可借：" + available);
    }

    public String getTitle() {
        return title;
    }

    public boolean isAvailable() {
        return available;
    }
}
