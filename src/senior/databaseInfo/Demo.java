package senior.databaseInfo;

public class Demo {
    public static void main(String[] args) {
        /*
            執行流程
            Main
                │
                ▼
            StudentRepository
                │
                ▼
            DBConnection
                │
                ▼
            MySQL
        */
        try {
            StudentRepository repository = new StudentRepository();
            

            // repository.findAll();

            // 課堂練習: 請大家設計一個findOne(int id) : 查詢單一同學
            // repository.findOne(2);

            // repository.insert(new Student("Ed", 23));

            // repository.update(new Student(7, "Tom2026" , 30));

            // repository.delete(8);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } 
        
    }
}
