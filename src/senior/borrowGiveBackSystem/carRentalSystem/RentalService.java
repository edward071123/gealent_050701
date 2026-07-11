package senior.borrowGiveBackSystem.carRentalSystem;
import java.util.ArrayList;
import java.util.List;

import senior.borrowGiveBackSystem.carRentalSystem.models.Car;
import senior.borrowGiveBackSystem.main.IBorrowGiveBack;

public class RentalService implements IBorrowGiveBack {

    private List<Car> cars; // 儲存所有車輛的列表
    
    public RentalService() {
        // 初始化租車服務
    }

    // 查詢所有車輛的房間
    public List<Car> getAllCars() {
        // 可參照 src/junior/collectionInfo/Demo.java
        cars = new ArrayList<>();
        // 返回所有車輛的列表
        return cars; // 這裡應該返回實際的車輛列表
    }

    // 辦理租車業務的房間
    @Override
    public String borrow(int number, String borrowUser) throws Exception {
        // 實現租車邏輯
        return "租車成功: 車輛編號 " + number + " 已租給 " + borrowUser;
    }

    // 辦理還車業務的房間
    @Override
    public String giveBack(int number) throws Exception {
        // 實現還車邏輯
        return "還車成功: 車輛編號 " + number + " 已歸還";
    }

}
