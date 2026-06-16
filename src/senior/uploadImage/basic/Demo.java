package senior.uploadImage.basic;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class Demo {
    public static void main(String[] args) {

        try {
            File source = new File("/Users/edwardchung/Desktop/未命名檔案夾/d23.jpg");

            File uploadDir = new File("src/senior/uploadImage/uploads");

            // 如果沒有目標資料夾就自動建立
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            File target = new File(uploadDir, source.getName());

            /*
             * Files.copy 是 Java 內建的檔案複製方法。
             *
             * 第一個參數：source.toPath()
             * - source 是原始檔案，例如桌面上的 d23.jpg。
             * - toPath() 會把 File 轉成 Path。
             * - Files.copy 需要的是 Path，所以要呼叫 toPath()。
             *
             * 第二個參數：target.toPath()
             * - target 是要複製到哪裡。
             * - 這裡代表 src/senior/uploadImage/uploads/d23.jpg。
             *
             * 第三個參數：StandardCopyOption.REPLACE_EXISTING
             * - 如果目標位置已經有同名檔案，就直接覆蓋。
             * - 如果沒有這個設定，遇到同名檔案時會複製失敗。
             */
            Files.copy(
                source.toPath(),
                target.toPath(),
                StandardCopyOption.REPLACE_EXISTING
            );

            System.out.println("上傳成功");

        } catch (Exception ex) {
            System.out.println("上傳失敗：" + ex.getMessage());
        }
        
        
    }
}
