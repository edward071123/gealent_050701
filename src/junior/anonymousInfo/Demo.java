package junior.anonymousInfo;

public class Demo {
    public static void main(String[] args) {
        // 匿名類別(Anonymous Class)介紹
        // 懶得另外建立一個類別
        
        System.out.println("===============一般物件寫法===============");
        // 1. 一般物件寫法
        Animal dog1 = new Dog();
        dog1.speak();

        System.out.println("===============匿名類別寫法===============");
        // 2. 匿名類別寫法: 懶得另外建立一個類別
        // 通常一次性 不重複使用
        // 通常用作interface獨立實作
        Animal dog2 = new Animal() {
            @Override
            public void speak() {
                System.out.println("汪汪2");
            }
        };
        dog2.speak();

        System.out.println("===============Lambda寫法===============");
        // 3. Lambda寫法: 我連匿名類別都懶得寫, 只想實作一個方法
        // Java 8 開始提供
        // 只有一個interface的方法（Functional Interface）
        // 不可以用在抽象類別上
        Animal dog3 = () -> {
            System.out.println("汪汪3");
        };
        dog3.speak();
    }
}


interface Animal {
    void speak();
}

class Dog implements Animal {

    @Override
    public void speak() {
        System.out.println("汪汪1");
    }
}