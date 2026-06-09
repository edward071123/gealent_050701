package junior.abstractAndInterface;

import junior.abstractAndInterface.abstractExample.*;
import junior.abstractAndInterface.interfaceExample.*;

public class Demo {
    public static void main(String[] args) {
        // 抽象與介面區分

        // ================================================
        // 什麼時候用 Abstract？
        // 當你有共用程式碼：
        // validateUsername();
        // writeLog();
        System.out.println("================================================");
        AUserService[] services = {
            new AMember(),
            new AAdmin()
        };

        for (AUserService service : services) {
            service.registerUser("EdwardA");
            System.out.println(
            service.getUserInfo(1L));
            System.out.println();
        }
        
        // ================================================
        // 什麼時候用 Interface？
        // 當你只是規定規格：
        // 不知道也不關心怎麼做。
        // 例如：
        // EmailUserService
        // LineUserService
        // GoogleUserService
        // 都能自己實作。
        System.out.println("================================================");
        IUserService[] services2 = {
            new IAdmin(),
            new IMember()
        };

        for (IUserService service : services2) {
            service.registerUser("EdwardI");
            System.out.println(service.getUserInfo(1L));
            System.out.println();
        }

    }

}
