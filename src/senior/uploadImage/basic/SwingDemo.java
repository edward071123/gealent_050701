package senior.uploadImage.basic;

import java.awt.*;
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
        // ========================畫面 起始================================
        // 範例互動網站:
        // https://edward071123.github.io/gealent_050701/src/senior/uploadImage/basic/swing-demo.html

        // Frame 基本設定
        settingFrame();

        //=================topPanel區塊的元件 起始============================
        JButton uploadButton = new JButton("上傳圖片");

        JLabel statusLabel = new JLabel("狀態：尚未選擇圖片");
        JLabel fileSizeLabel = new JLabel("大小：-");
        // GridLayout(列數, 欄數, 水平間距, 垂直間距)
        JPanel infoPanel = new JPanel(new GridLayout(1, 2, 12, 0));
        infoPanel.add(statusLabel);
        infoPanel.add(fileSizeLabel);

        JButton refreshButton = new JButton("重新整理");

        // BorderLayout(水平間距, 垂直間距)
        JPanel topPanel = new JPanel(new BorderLayout(8, 0));
        topPanel.add(uploadButton, BorderLayout.WEST);
        topPanel.add(infoPanel, BorderLayout.CENTER);
        topPanel.add(refreshButton, BorderLayout.EAST);
        //=================topPanel區塊的元件 結束=============================

        //=================splitPane區塊的元件 起始============================
        // 左側圖片清單一次只能選一張
        // DefaultListModel：清單資料來源，顯示uploads資料夾的 File。
        DefaultListModel<File> imageListModel = new DefaultListModel<>();
        JList<File> imageList = new JList<>(imageListModel);
        JScrollPane listScrollPane = new JScrollPane();
        listScrollPane.add(imageList);

        // 右邊的圖片顯示
        JLabel imageLabel = new JLabel("尚未選擇圖片", SwingConstants.CENTER);
        JScrollPane imageScrollPane = new JScrollPane();
        imageScrollPane.add(imageLabel);

        // JSplitPane：左右分割畫面(因為兩個區塊)
        // HORIZONTAL_SPLIT 代表水平分割，也就是左邊一區、右邊一區。
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        // 設定左右分割線的位置。 往左數字小，往右數字大
        splitPane.setDividerLocation(160);
        splitPane.add(listScrollPane);
        splitPane.add(imageScrollPane);
        //=================splitPane區塊的元件 結束===========================

        //=================progressBar區塊的元件 起始=========================
        JProgressBar progressBar = new JProgressBar(0, 100);
        // 設定進度條上面畫文字上去顯示 0%、50%、100% 這種文字
        progressBar.setStringPainted(true);
        //=================progressBar區塊的元件 結束=========================

        // 三大區塊分北中南 貼到 Frame
        add(topPanel, BorderLayout.NORTH);
        add(splitPane, BorderLayout.CENTER);
        add(progressBar, BorderLayout.SOUTH);
        // 顯示視窗
        setVisible(true);
        // ========================畫面 結束==================================

        // ========================事件 開始==================================
        // 一般事件的寫法
        // 要搭配以下import
        // import java.awt.event.ActionEvent;
        // import java.awt.event.ActionListener;
        // uploadButton.addActionListener(new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         uploadImage();
        //     }
        // });

        // Lambda 寫法
        // 監聽按鈕事件
        uploadButton.addActionListener(e -> uploadImage());
        refreshButton.addActionListener(e -> loadImage());
        // ========================事件 結束==================================
    }

    private void settingFrame() {
        setTitle("圖片上傳與瀏覽 - swing - 單檔");
        // 設定寬高
        setSize(900, 700);
        // 按按鈕時結束程式
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 視窗設定在中央的位置
        setLocationRelativeTo(null);
    }

    // 上傳檔案
    public void uploadImage() {
       System.out.println("上傳檔案");
    }

    public void loadImage() {
        System.out.println("取得檔案");
    }
}