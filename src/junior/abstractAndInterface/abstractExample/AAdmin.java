package junior.abstractAndInterface.abstractExample;

public class AAdmin extends AUserService {
    @Override
    public String getUserInfo(Long id) {
        return "abstract-管理員資訊:" + id;
    }

    @Override
    public void registerUser(String username) {
        validateUsername(username);
        System.out.println("abstract-建立管理員帳號:" + username);
        writeLog("abstract-管理員建立成功");
    }
    
}
