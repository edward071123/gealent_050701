package senior.uploadImage.basic;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class SwingDemo {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // 建立主視窗。
            new Frame();
        });
    }

}

class Frame extends JFrame {

    private final File uploadDir = new File("src/senior/uploadImage/uploads");
    // 畫面上的主要元件。
    // JLabel：單純顯示文字或圖片。
    private JLabel statusLabel = new JLabel("狀態：尚未選擇圖片");
    private JLabel fileSizeLabel = new JLabel("大小：-");
    private JLabel imageLabel = new JLabel("尚未選擇圖片", SwingConstants.CENTER);

    // JButton：使用者可以點擊的按鈕。
    private JButton uploadButton = new JButton("上傳圖片");
    private JButton refreshButton = new JButton("重新整理");

    private JProgressBar progressBar = new JProgressBar(0, 100);

    // DefaultListModel：清單資料來源，負責保存要顯示的 File。
    // JList：真正畫在畫面上的清單元件。
    private DefaultListModel<File> imageListModel = new DefaultListModel<>();
    private JList<File> imageList = new JList<>(imageListModel);

    public Frame() {
        // 視窗基本設定。
        // setTitle：視窗標題。
        // setSize：視窗寬高。
        // setDefaultCloseOperation：按關閉按鈕時結束程式。
        // setLocationRelativeTo(null)：讓視窗出現在螢幕中央。
        setTitle("圖片上傳與瀏覽 - swing - 單檔");
        setSize(950, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 進度條設定。
        // setStringPainted(true)：讓進度條顯示 0%、50%、100% 這種文字。
        progressBar.setStringPainted(true);
        progressBar.setValue(0);
        progressBar.setString("0%");

        JPanel infoPanel = new JPanel(new GridLayout(1, 2, 12, 0));
        infoPanel.add(statusLabel);
        infoPanel.add(fileSizeLabel);

        JPanel topPanel = new JPanel(new BorderLayout(8, 0));
        topPanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        topPanel.add(uploadButton, BorderLayout.WEST);
        topPanel.add(infoPanel, BorderLayout.CENTER);
        topPanel.add(refreshButton, BorderLayout.EAST);


        // 左側圖片清單一次只能選一張。
        imageList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane listScrollPane = new JScrollPane(imageList);
        listScrollPane.setPreferredSize(new Dimension(360, 0));

        JScrollPane imageScrollPane = new JScrollPane(imageLabel);

        // JSplitPane：左右分割畫面。
        // HORIZONTAL_SPLIT 代表水平分割，也就是左邊一區、右邊一區。
        JSplitPane splitPane = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                listScrollPane,
                imageScrollPane);

        // 設定左右分割線的位置。
        splitPane.setDividerLocation(360);

        // JFrame 預設就是 BorderLayout。
        // NORTH 放上方工具列，CENTER 放主要內容。
        add(topPanel, BorderLayout.NORTH);
        add(splitPane, BorderLayout.CENTER);
        add(progressBar, BorderLayout.SOUTH);

        // 按鈕事件。
        // addActionListener：設定按鈕被點擊後要執行什麼方法。
        // 原始寫法
        // uploadButton.addActionListener(new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         uploadImage();
        //     }
        // });
        uploadButton.addActionListener(e -> uploadImage());
        refreshButton.addActionListener(e -> loadUploadImages());

        imageList.setCellRenderer((list, value, index, isSelected, cellHasFocus) -> {
            JLabel label = (JLabel) new DefaultListCellRenderer()
                    .getListCellRendererComponent(
                            list,
                            value,
                            index,
                            isSelected,
                            cellHasFocus
                    );

            File file = (File) value;
            label.setText(file.getName());

            return label;
        });

        imageList.addListSelectionListener(e -> {
            if (e.getValueIsAdjusting()) {
                return;
            }

            File selectedFile = imageList.getSelectedValue();

            if (selectedFile != null) {
                showImage(selectedFile);
            }
        });


        loadUploadImages();
        setVisible(true);
    }
    
    private void uploadImage() {
        JFileChooser chooser = new JFileChooser();

        chooser.setFileFilter(new FileNameExtensionFilter(
                "圖片檔案 (*.jpg, *.jpeg, *.png, *.gif)",
                "jpg",
                "jpeg",
                "png",
                "gif"
        ));

        // showOpenDialog 會打開檔案選擇視窗。
        // 回傳值用來判斷使用者是選了檔案，還是按取消。
        int result = chooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File sourceFile = chooser.getSelectedFile();
            File targetFile = new File(uploadDir, sourceFile.getName());

            setStatusText("上傳中...", null);
            progressBar.setValue(0);
            progressBar.setString("0%");

            uploadButton.setEnabled(false);
            refreshButton.setEnabled(false);

            new Thread(() -> {
                byte[] buffer = new byte[4096];
                long totalSize = sourceFile.length();
                long copiedSize = 0;

                FileInputStream fis = null;
                FileOutputStream fos = null;

                try {
                    fis = new FileInputStream(sourceFile);
                    fos = new FileOutputStream(targetFile);

                    int len;

                    while ((len = fis.read(buffer)) != -1) {
                        Thread.sleep(10);
                        fos.write(buffer, 0, len);
                        copiedSize += len;

                        int value = (int) ((copiedSize * 100) / totalSize);

                        // 加在這裡：背景 thread 要更新 progressBar，所以丟回 EDT
                        SwingUtilities.invokeLater(() -> {
                            progressBar.setValue(value);
                            progressBar.setString(value + "%");
                        });
                    }

                    // 加在這裡：上傳完成後更新畫面，也要丟回 EDT
                    SwingUtilities.invokeLater(() -> {
                        loadUploadImages();
                        showImage(targetFile);
                        imageList.setSelectedValue(targetFile, true);
                        setStatusText("上傳成功", targetFile);
                    });

                } catch (Exception ex) {
                    // 加在這裡：錯誤視窗和狀態更新，也要丟回 EDT
                    SwingUtilities.invokeLater(() -> {
                        JOptionPane.showMessageDialog(
                                Frame.this,
                                "上傳失敗：" + ex.getMessage()
                        );
                        setStatusText("上傳失敗", null);
                    });

                } finally {
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

                    uploadButton.setEnabled(true);
                    refreshButton.setEnabled(true);
                }
            }).start();
        }
    }

    private void loadUploadImages() {
        imageListModel.clear();
       // listFiles 會取得資料夾裡的所有檔案。
        File[] files = uploadDir.listFiles();

        if (files == null) {
            return;
        }

        for (File file : files) {
            if (isImageFile(file)) {
                imageListModel.addElement(file);
            }
        }

        if (imageListModel.isEmpty()) {
            imageLabel.setIcon(null);
            imageLabel.setText("uploads 資料夾目前沒有圖片");
            setStatusText("uploads 資料夾目前沒有圖片", null);
        }

    }

    private void showImage(File file) {
        /*
         * 顯示圖片流程：
         * 1. 更新上方狀態與檔案大小。
         * 2. 用 ImageIcon 讀取圖片。
         * 3. 取得右側預覽區大小。
         * 4. 依照預覽區大小等比例縮放圖片。
         * 5. 把縮放後的圖片放到 imageLabel。
         */

        setStatusText("目前顯示", file);

        // ImageIcon 可以直接用檔案路徑讀取圖片。
        ImageIcon icon = new ImageIcon(file.getAbsolutePath());
        int imageWidth = icon.getIconWidth();
        int imageHeight = icon.getIconHeight();

        // 如果寬高小於等於 0，代表圖片讀取失敗。
        if (imageWidth <= 0 || imageHeight <= 0) {
            imageLabel.setIcon(null);
            imageLabel.setText("圖片讀取失敗");
            return;
        }

        // 依照右側可視區域等比例縮放圖片。
        // imageLabel 放在 JScrollPane 裡，所以 parent 通常是 JViewport。
        Dimension viewSize = imageLabel.getParent() instanceof JViewport
                ? ((JViewport) imageLabel.getParent()).getExtentSize()
                : imageLabel.getSize();

        // 預留一點邊距，不要讓圖片緊貼邊界。
        int maxWidth = Math.max(100, viewSize.width - 20);
        int maxHeight = Math.max(100, viewSize.height - 20);

        // 分別算出寬度比例和高度比例，取比較小的那個。
        // 這樣圖片不會超出預覽區，也不會變形。
        double scale = Math.min(
                (double) maxWidth / imageWidth,
                (double) maxHeight / imageHeight
        );

        // 算出縮放後實際顯示的寬高。
        int displayWidth = Math.max(1, (int) (imageWidth * scale));
        int displayHeight = Math.max(1, (int) (imageHeight * scale));

        // 產生縮放後的 Image。
        Image image = icon.getImage().getScaledInstance(
                displayWidth,
                displayHeight,
                Image.SCALE_SMOOTH
        );

        // 清掉原本的文字，改顯示圖片。
        imageLabel.setText("");
        imageLabel.setIcon(new ImageIcon(image));
    }

    // 統一設定上方資訊。上方不顯示檔名，檔名只放在左側清單。
    private void setStatusText(String text, File file) {
        // 狀態文字，例如：上傳中、上傳成功、目前顯示。
        statusLabel.setText("狀態：" + text);

        // 如果沒有檔案，就把大小顯示成 -。
        if (file == null) {
            fileSizeLabel.setText("大小：-");
            statusLabel.setToolTipText(null);
            return;
        }

        // 有檔案時，顯示檔案大小。
        fileSizeLabel.setText("大小：" + formatFileSize(file.length()));

        // tooltip 是滑鼠移到狀態文字上時顯示的小提示。
        // 這裡用來保存完整路徑，但畫面上不直接顯示完整路徑。
        statusLabel.setToolTipText(file.getAbsolutePath());
    }

    private String formatFileSize(long size) {
        if (size < 1024) {
            return size + " B";
        }

        double kb = size / 1024.0;
        if (kb < 1024) {
            return String.format("%.1f KB", kb);
        }

        double mb = kb / 1024.0;
        return String.format("%.1f MB", mb);
    }
 
    private boolean isImageFile(File file) {
        // 先把檔名轉成小寫，這樣 .JPG / .jpg 都能判斷成功。
        String name = file.getName().toLowerCase();

        // 第一步：確認它是真的「檔案」，不是資料夾。
        boolean isFile = file.isFile();

        // 第二步：分別判斷副檔名。
        boolean isJpg = name.endsWith(".jpg");
        boolean isJpeg = name.endsWith(".jpeg");
        boolean isPng = name.endsWith(".png");
        boolean isGif = name.endsWith(".gif");

        // 第三步：只要符合其中一種圖片副檔名，就算是圖片格式。
        boolean isImageType = isJpg || isJpeg || isPng || isGif;

        // 第四步：必須同時滿足：
        // 1. 它是檔案
        // 2. 它是允許的圖片格式
        return isFile && isImageType;
    }

}