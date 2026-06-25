package senior.uploadImage.basic;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.*;

public class SwingDemo {
    /*
    * 畫面佈局簡圖：
    *
    * BorderLayout 會把畫面分成 5 個區域：
    *
    * +---------------------------+
    * |          NORTH            |
    * +-------+-----------+-------+
    * | WEST  |  CENTER   | EAST  |
    * |       |           |       |
    * +-------+-----------+-------+
    * |          SOUTH            |
    * +---------------------------+
    *
    * 本程式實際放置的元件：
    *
    * JFrame
    * +-------------------------------------------------------------------+
    * | topPanel                                                          |
    * | +--------------+--------------------------------+---------------+ |
    * | | uploadButton | infoPanel                      | refreshButton | |
    * | |              | statusLabel + fileSizeLabel    |               | |
    * | +--------------+--------------------------------+---------------+ |
    * +------------------------+------------------------------------------+
    * | splitPane              |                                          |
    * | +--------------------+ | +--------------------------------------+ |
    * | | listScrollPane     | | | imageScrollPane                      | |
    * | | imageList          | | | imageLabel                           | |
    * | | - d1.jpg           | | |                                      | |
    * | | - d2.jpg           | | |                                      | |
    * | +--------------------+ | +--------------------------------------+ |
    * +------------------------+------------------------------------------+
    * | progressBar                                                       |
    * | [ 0% ----------------------------------------------------- 100% ] |
    * +-------------------------------------------------------------------+
    *
    * BorderLayout.NORTH  ：上方工具列 topPanel
    * BorderLayout.CENTER ：中間左右分割 splitPane
    * BorderLayout.SOUTH  ：下方進度條 progressBar
    * splitPane 左邊      ：圖片清單 imageList
    * splitPane 右邊      ：圖片預覽 imageLabel
    */
    public static void main(String[] args) {
        // 把工作丟給Swing的UI執行緒
        SwingUtilities.invokeLater(() -> {
            // 建立主視窗
            new Frame();
        });

    }
}

class Frame extends JFrame {

    public Frame() {

        // 畫面

        setTitle("圖片上傳與瀏覽 - swing - 單檔");
        // 設定寬高
        setSize(900, 700);
        // 按按鈕時結束城市
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 視窗設定在中央的位置
        setLocationRelativeTo(null);

        // 上傳的按鈕
        JButton uploadButton = new JButton("上傳圖片");

        // 狀態的標籤
        JLabel statusLabel = new JLabel("狀態: 尚未選擇圖片");
        // 檔案大小的標籤
        JLabel fileSizeLabel = new JLabel("大小：-");
        // 資訊的版面
        JPanel infoPanel = new JPanel();
        // 狀態的標籤疊加上資訊的版面
        infoPanel.add(statusLabel);
        // 檔案大小的標籤疊加上資訊的版面
        infoPanel.add(fileSizeLabel);

        // 重整按鈕
        JButton refreshButton = new JButton("重新整理");

        // 上面的Panel
        // new BorderLayout(8, 0) 是8左右元件的間距
        JPanel topPanel = new JPanel(new BorderLayout(8, 0));
        topPanel.add(uploadButton, BorderLayout.WEST);
        topPanel.add(infoPanel, BorderLayout.CENTER);
        topPanel.add(refreshButton, BorderLayout.EAST);

        // upload顯示檔案列表
        JList<File> imageList = new JList<>();
        // 左邊檔案列表的上下滾動畫面
        JScrollPane listScrollPane = new JScrollPane();
        listScrollPane.add(imageList);

        // 顯示圖片
        JLabel imageLabel = new JLabel();
        // 右邊圖片預覽的滾動畫面
        JScrollPane imageScrollPane = new JScrollPane();
        imageScrollPane.add(imageLabel);

        // 中間檔案列表 + 預覽圖片的Panel
        // HORIZONTAL_SPLIT 代表水平分割，也就是左邊一區、右邊一區。
        JSplitPane splitPane = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                listScrollPane,
                imageScrollPane);

        // 設定左右分割線的位置
        splitPane.setDividerLocation(360);

        // JProgressBar：顯示上傳進度，0 到 100 代表百分比。
        JProgressBar progressBar = new JProgressBar(0, 100);
        // 是否顯示0%、50%、100% 這種文字。
        progressBar.setStringPainted(true);
        progressBar.setValue(0);
        progressBar.setString("0%");

        add(topPanel, BorderLayout.NORTH);
        add(splitPane, BorderLayout.CENTER);
        add(progressBar, BorderLayout.SOUTH);

        // 動作產生

        // 標準寫法
        // uploadButton.addActionListener(new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         // 上傳圖片
        //         uploadImage();
        //     }
        // });

        // lambda 寫法
        uploadButton.addActionListener(e -> uploadImage());

        refreshButton.addActionListener(e -> loadImage());

        // 顯示視窗
        setVisible(true);
    }

    // 上傳檔案
    public void uploadImage() {
        // 1. 選擇檔案
        JFileChooser chooser = new JFileChooser();
        // 選擇檔案過濾器
        chooser.setFileFilter(new FileNameExtensionFilter(
                "圖片檔案 (*.jpg, *.jpeg, *.png, *.gif)",
                "jpg",
                "jpeg",
                "png",
                "gif"));

        // 顯示選擇檔案畫面
        int result = chooser.showOpenDialog(this);
        // 選擇完畢的判斷
        if (result == JFileChooser.APPROVE_OPTION) {
            // 來源檔案
            File sourceFile = chooser.getSelectedFile();
            // 目標資料夾
            File uploadDir = new File("src/senior/uploadImage/uploads");

            // 判斷資料夾是否存在
            if (!uploadDir.exists()) {
                // 創造資料夾
                uploadDir.mkdirs();
            }

            // 目標檔案 = 目標資料夾 + 來源檔案的檔名
            File targetFile = new File(uploadDir, sourceFile.getName());

            // 1024 byte => 1 KB
            // 1024 KB => 1 MB
            // 1024 MB => 1 GB
            // 1024 GB => 1 TB
            // 每次讀取 4096 bytes，不要一次把整張圖片全部讀進記憶體。
            byte[] buffer = new byte[4096];

            // 原始檔案的大小
            long totalSize = sourceFile.length();
            // 已複製的檔案大小(進度條計算使用)
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
                    // 睡100毫秒, 為了觀察進度條
                    Thread.sleep(100);
                    // 只寫入本次實際讀到的長度 len。
                    fos.write(buffer, 0, len);

                    copiedSize += len;

                    // 呼叫進度條更新的方法
                    // printProgress(copiedSize, totalSize);
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

        // 2. 讀寫檔案

    }

    public void loadImage() {
        System.out.println("取得檔案");
    }
}