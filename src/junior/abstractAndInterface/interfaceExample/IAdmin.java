package junior.abstractAndInterface.interfaceExample;

public class IAdmin implements IUserService {
    
    @Override
    public String getUserInfo(Long id) {
        return "interface-管理員資訊:" + id;
    }

    @Override
    public void registerUser(String username) {
        System.out.println("interface-管理員註冊:" + username);
    }
}
