package junior.abstractInfo;

public class SmsNotification extends Notification {

    public SmsNotification(String recipint) {
        super(recipint);
    }

    @Override // 實作抽象方法
    public void send() {
        System.out.println("寄送Sms通知給: " + getRecipint());
    }

}
