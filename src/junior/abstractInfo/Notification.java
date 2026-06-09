package junior.abstractInfo;

// 抽象類別
public abstract class Notification {
    private String recipint;

    public Notification(String recipint) {
        setRecipint(recipint);
    }

    // 抽象方法: 子類別繼承必須實作的方法, 比喻為書的目錄
    public abstract void send();

    public void log() {
        System.out.println("已發出通知給:" + getRecipint());
    }

    public void setRecipint(String recipint) {
        this.recipint = recipint;
    }

    public String getRecipint() {
        return this.recipint;
    }

}
