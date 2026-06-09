package junior.abstractAndInterface.interfaceExample;

public class IMember implements IUserService {
    @Override
    public String getUserInfo(Long id) {
        return "interface-會員資訊:" + id;
    }

    @Override
    public void registerUser(String username) {
        System.out.println("interface-會員註冊:" + username);
    }
}
