package junior.abstractAndInterface.abstractExample;

public abstract class AUserService {

    public void validateUsername(String username) {
        if (username == null || username.isEmpty()) {
            System.out.println("abstract-帳號不可空白");
        }
    }

    public void writeLog(String message) {
        System.out.println("[abstract-LOG] " + message);
    }

    public abstract String getUserInfo(Long id);

    public abstract void registerUser(String username);
}