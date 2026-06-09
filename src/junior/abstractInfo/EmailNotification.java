package junior.abstractInfo;

public class EmailNotification extends Notification {

    public EmailNotification(String recipint) {
        super(recipint);
    }

    public void send() {
        System.out.println("寄送Email通知給: " + getRecipint());
    }

}
