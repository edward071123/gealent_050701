package senior.uploadImage.basic;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javax.swing.JFileChooser;

public class Demo {
    public static void main(String[] args) {

        // JFileChooser chooser = new JFileChooser();

        // int result = chooser.showOpenDialog(null);

        // if (result == JFileChooser.APPROVE_OPTION) {

        //     File file = chooser.getSelectedFile();

        //     System.out.println("檔名：" + file.getName());
        //     System.out.println("路徑：" + file.getAbsolutePath());

        // }
        try {
            File source = new File("/Users/edwardchung/Desktop/未命名檔案夾/d2.jpg");

            File uploadDir = new File("src/senior/uploadImage/uploads");

            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            File target = new File(uploadDir, source.getName());

            Files.copy(
                    source.toPath(),
                    target.toPath(),
                    StandardCopyOption.REPLACE_EXISTING
            );

        } catch (Exception ex) {

        }
        
        System.out.println("上傳成功");
    }
}
