package junior.polymorphism;

public class Demo {
    public static void main(String[] args) {
        // 多型的介紹
        Employee emp1 = new Employee("Alice", 40000);
        emp1.calculateSalary();

        SalesEmployee emp2 = new SalesEmployee("Bob", 30000, 5000);
        emp2.calculateSalary();

        PartimeEmployee emp3 = new PartimeEmployee("Charlie", 200, 80);
        emp3.calculateSalary();
    }
}
