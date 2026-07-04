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
            repository.findAll();

            repository.insert(new Student("Ed", 23));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } 
        
    }
}
