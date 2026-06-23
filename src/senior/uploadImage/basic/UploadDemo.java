package senior.uploadImage.basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class UploadDemo {
    public static void main(String[] args) throws IOException{


        // targetFile 是複製後的新位置。
        // 例如 sourceFile 是 /Users/xxx/a.png，
        // targetFile 就會是 src/senior/uploadImage/uploads/a.png。

        File sourceFile = new File("/Users/edwardchung/Desktop/q1.png");
        File uploadDir = new File("src/senior/uploadImage/uploads");
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
        File targetFile = new File(uploadDir, sourceFile.getName());

        // 每次讀取 4096 bytes，不要一次把整張圖片全部讀進記憶體。
        byte[] buffer = new byte[4096];
        long totalSize = sourceFile.length();
        long copiedSize = 0;

        FileInputStream fis = null;
        FileOutputStream fos = null;

        try {
            // FileInputStream：從原始圖片讀取資料。
            fis = new FileInputStream(sourceFile);

            // FileOutputStream：把資料寫到 uploads 裡的新檔案。
            fos = new FileOutputStream(targetFile);

            int len;

            // fis.read(buffer) 會把資料讀進 buffer。
            // 回傳值 len 表示這次實際讀到幾個 bytes。
            // 如果回傳 -1，代表檔案讀完了。
            while ((len = fis.read(buffer)) != -1) {
                Thread.sleep(100);
                // 只寫入本次實際讀到的長度 len。
                fos.write(buffer, 0, len);

                copiedSize += len;
                printProgress(copiedSize, totalSize);
            }

            System.out.println();
            System.out.println("上傳成功");

        } catch (Exception e) {
            System.out.println("圖片複製失敗：" + e.getMessage());

        } finally {
            // finally 不管成功或失敗都會執行，適合拿來關閉檔案資源。
            try {
                if (fis != null) {
                    fis.close();
                }

                if (fos != null) {
                    fos.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        
    }

    private static void printProgress(long copiedSize, long totalSize) {
        // 如果檔案大小是 0 bytes，不能做 copiedSize / totalSize，避免除以 0。
        if (totalSize == 0) {
            // 空檔案沒有內容要複製，所以直接顯示 100%。
            System.out.print("\r上傳進度：[####################] 100%");
            return;
        }

        // 把「已複製 bytes / 總 bytes」換算成百分比，例如 50 代表 50%。
        //
        // 公式：
        // copiedSize * 100 / totalSize
        //
        // 例如：
        // totalSize  = 10000 bytes
        // copiedSize = 2500 bytes
        //
        // 2500 * 100 / 10000 = 25
        // 所以 progress = 25，代表目前完成 25%。
        //
        // 先乘 100 是因為 Java 整數除法會捨去小數。
        // 如果直接寫 copiedSize / totalSize，
        // 2500 / 10000 會得到 0，不會得到 0.25。
        int progress = (int) ((copiedSize * 100) / totalSize);

        // 進度條固定 20 格，例如 [##########----------]。
        int barLength = 20;

        // 根據百分比計算目前要填滿幾格。
        // 例如 progress = 50，barLength = 20，就會填滿 10 格。
        //
        // 公式：
        // progress * barLength / 100
        //
        // 例如：
        // progress  = 25
        // barLength = 20
        //
        // 25 * 20 / 100 = 5
        // 所以 filledLength = 5，進度條會變成：
        // [#####---------------] 25%
        int filledLength = progress * barLength / 100;

        // 用 StringBuilder 組出進度條內容。
        StringBuilder bar = new StringBuilder();

        // 已完成的部分用 # 表示。
        for (int i = 0; i < filledLength; i++) {
            bar.append("#");
        }

        // 還沒完成的部分用 - 表示。
        for (int i = filledLength; i < barLength; i++) {
            bar.append("-");
        }

        // \r 代表回到同一行開頭，讓進度條在同一行更新，不會一直換行。
        System.out.print("\r上傳進度：[" + bar + "] " + progress + "%");
    }
}
