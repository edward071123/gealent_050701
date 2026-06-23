package senior.uploadImage.basic;

public class ThreadDemo {
    public static void main(String[] args) {
        // 執行緒介紹

        // |------------------主執行緒(main)-----------------------
        // |
        // |------------------執行緒1(Thread1)---------------------
        // |
        // |------------------執行緒2(Thread2)---------------------

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    System.out.println("執行緒 1 正在執行: " + i);
                    try {
                        // 1000毫秒 = 1秒
                        // 睡一秒鐘
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("執行緒 2 正在執行: " + i);
                try {
                    // 睡兩秒鐘
                    Thread.sleep(1500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // 排隊執行 thread1 全部跑完後 換 thread2
        thread1.run();
        thread2.run();

        // 同步執行 thread1 跟 thread2 同步起跑
        thread1.start();
        thread2.start();

    }
}
