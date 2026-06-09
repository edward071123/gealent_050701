package junior.abstractAndInterface.interfaceExample;

public interface IUserService {
    // 取得使用者資訊
    public String getUserInfo(Long id);

    // 註冊使用者
    public void registerUser(String username);
}
