package junior.abstractInfo;

public class Demo {
    public static void main(String[] args) {
        // 抽象: abstract 介紹
        // 想像是書的目錄
        // 也有屬性 跟 方法 但是方法需要被實作

        Notification email = new EmailNotification("aa@gmail.com");
        email.send();
        email.log();

        Notification sms = new SmsNotification("0911222333");
        sms.send();
        sms.log();
    }
}
