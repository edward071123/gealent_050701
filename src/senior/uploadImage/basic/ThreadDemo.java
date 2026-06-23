package senior.uploadImage.basic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadDemo {
    public static void main(String[] args)  throws InterruptedException {
        // 多進程, 多線程 在系統運作的介紹
        //                         ┌──────────┐
        //                         │Process   │
        //                         │┌────────┐│
        //             ┌──────────┐││ Thread ││┌──────────┐
        //             │Process   ││└────────┘││Process   │
        //             │┌────────┐││┌────────┐││┌────────┐│
        // ┌──────────┐││ Thread ││││ Thread ││││ Thread ││
        // │Process   ││└────────┘││└────────┘││└────────┘│
        // │┌────────┐││┌────────┐││┌────────┐││┌────────┐│
        // ││ Thread ││││ Thread ││││ Thread ││││ Thread ││
        // │└────────┘││└────────┘││└────────┘││└────────┘│
        // └──────────┘└──────────┘└──────────┘└──────────┘
        // ┌──────────────────────────────────────────────┐
        // │               Operating System               │
        // └──────────────────────────────────────────────┘

        //========================================================
        // // 一般的寫法
        // Thread thread1 = new Thread(new Runnable() {
        //     @Override
        //     public void run() {
        //         for (int i = 0; i < 5; i++) {
        //             System.out.println("執行緒 A 正在執行: " + i);
        //                 try {
        //                     Thread.sleep(1000);
        //                 } catch (InterruptedException e) {
        //                     e.printStackTrace();
        //                 }
        //         }
        //     }
        // });


        // // lambda
        // Thread thread2 = new Thread(() -> {
        //     for (int i = 0; i < 5; i++) {
        //         System.out.println("執行緒 B 正在執行: " + i);
        //         try {
        //             Thread.sleep(1500);
        //         } catch (InterruptedException e) {
        //             e.printStackTrace();
        //         }
        //     }
        // });
        // thread1.start();
        // thread2.start();
        
        //========================================================
        // //  main 也是一個thread
        // System.out.println("main-thread start...");
        // Thread thread3 = new Thread() {
        //     public void run() {
        //         System.out.println("thread3 run...");
        //         try {
        //             Thread.sleep(2000);
        //         } catch (InterruptedException e) {}
        //         System.out.println("thread3 end.");
        //     }
        // };
        // thread3.start();
        // try {
        //     Thread.sleep(1000);
        // } catch (InterruptedException e) {}
        // System.out.println("main-thread end...");

        //========================================================
        // // 中斷執行緒
        // Thread thread4 = new Thread(() -> {
        //     while (!Thread.currentThread().isInterrupted()) {
        //         try {
        //             System.out.println("執行緒執行中...");
        //             Thread.sleep(1000);
        //         } catch (InterruptedException e) {
        //             System.out.println("sleep 被 interrupt 中斷了");

        //             // 重要：InterruptedException 會清掉 interrupt 狀態
        //             // 所以這裡手動補回去，讓 while 條件可以結束
        //             Thread.currentThread().interrupt();
        //         }
        //     }

        //     System.out.println("執行緒結束");
        // });

        // thread4.start();

        // Thread.sleep(3000);

        // System.out.println("main 呼叫 interrupt()");
        // thread4.interrupt();

        // thread4.join(); // 等待執行緒結束
        // System.out.println("main 結束");

        //========================================================
        // ExecutorService：執行緒池
        // 情境：使用者一次選了 8 張圖片，但我們只開 3 條執行緒同時上傳。
        // 好處：不用每張圖片都 new Thread，可以控制同時工作的數量。
        ExecutorService executor = Executors.newFixedThreadPool(3);

        String[] imageNames = {
                "cat.jpg",
                "dog.png",
                "family.jpeg",
                "travel01.jpg",
                "travel02.jpg",
                "avatar.gif",
                "food.png",
                "receipt.jpg"
        };

        for (int i = 0; i < imageNames.length; i++) {
            final int index = i + 1;
            final String imageName = imageNames[i];

            executor.submit(() -> {
                String threadName = Thread.currentThread().getName();
                System.out.println(threadName + " 開始上傳第 " + index + " 張：" + imageName);

                try {
                    // 模擬每張圖片大小不同，所以上傳時間不同。
                    Thread.sleep(700 + index * 150);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println(threadName + " 上傳中斷：" + imageName);
                    return;
                }

                System.out.println(threadName + " 完成上傳第 " + index + " 張：" + imageName);
            });
        }

        // 不再接收新任務，但已送出的任務會繼續做完。
        executor.shutdown();

        // main 等待執行緒池完成，最多等 10 秒。
        boolean finished = executor.awaitTermination(10, TimeUnit.SECONDS);

        if (finished) {
            System.out.println("全部任務完成");
        } else {
            System.out.println("等待逾時，強制停止未完成任務");
            executor.shutdownNow();
        }
    }

}
