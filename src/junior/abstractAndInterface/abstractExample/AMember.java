package junior.abstractAndInterface.abstractExample;

public class AMember extends AUserService {
    @Override
    public String getUserInfo(Long id) {
        return "abstract-會員資訊:" + id;
    }

    @Override
    public void registerUser(String username) {
        validateUsername(username);
        System.out.println("abstract-新增會員:" + username);
        writeLog("abstract-會員註冊成功");
    }
}
