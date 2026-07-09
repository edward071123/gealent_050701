package senior.borrowGiveBackSystemPlus.librarySystemPlusII;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import senior.borrowGiveBackSystemPlus.models.Book;
import senior.borrowGiveBackSystemPlus.models.Category;
import senior.borrowGiveBackSystemPlus.models.Item;
import senior.borrowGiveBackSystemPlus.models.Member;

public class LibrarySystemFrame extends JFrame {
    private static final String LOGIN_CARD = "login";
    private static final String MAIN_CARD = "main";

    private final String fontName = "SansSerif";

    private CardLayout cardLayout;
    private JPanel rootPanel;
    private JLabel currentUserLabel;
    private JTabbedPane mainTabs;
    private JTextField accountField;
    private JPasswordField passwordField;
    private JLabel loginMessageLabel;
    private JTable bookTable;
    private ArrayList<Member> members;
    private ArrayList<Category> categories;
    private ArrayList<Item> items;
    private ArrayList<Book> books;
    private Member currentMember;

    public LibrarySystemFrame() {
        super("librarySystemPlusII - 登入與分頁版");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 680);
        setLocationRelativeTo(null);

        members = new ArrayList<>();
        categories = new ArrayList<>();
        items = new ArrayList<>();
        books = new ArrayList<>();
        initMembers();
        initCategories();
        initItems();
        initBooks();

        cardLayout = new CardLayout();
        rootPanel = new JPanel(cardLayout);
        rootPanel.add(createLoginPanel(), LOGIN_CARD);
        rootPanel.add(createMainPanel(), MAIN_CARD);

        add(rootPanel);
        setVisible(true);
    }

    private void initMembers() {
        members.add(new Member(1, "admin", "管理員", "admin123", true));
        members.add(new Member(2, "aa", "AA會員", "aa123", false));
    }

    private void initCategories() {
        categories.add(new Category(1, "novel", "小說類"));
        categories.add(new Category(2, "programming", "程式類"));
    }

    private void initItems() {
        items.add(new Item(1, 2, "Java"));
        items.add(new Item(2, 2, "Python"));
        items.add(new Item(3, 1, "奇幻"));
        items.add(new Item(4, 1, "歷史"));
    }

    private void initBooks() {
        books.add(new Book("1", "Java入門", "張三", false, members.get(0).getDisplayName(), 2, 2, 1, "programming", "程式類", "Java"));
        books.add(new Book("2", "Python程式設計", "王五", true, "", 0, 2, 2, "programming", "程式類", "Python"));
        books.add(new Book("3", "哈利波特", "J.K.Rowling", true, "", 0, 1, 3, "novel", "小說類", "奇幻"));
        books.add(new Book("4", "達文西密碼", "丹布朗", true, "", 0, 1, 4, "novel", "小說類", "歷史"));
    }

    private JPanel createLoginPanel() {
        JPanel page = new JPanel(new BorderLayout());
        page.setBorder(BorderFactory.createEmptyBorder(80, 220, 80, 220));

        JLabel titleLabel = new JLabel("librarySystemPlusII:登入後才顯示分頁", JLabel.CENTER);
        titleLabel.setFont(new Font(fontName, Font.BOLD, 28));
        page.add(titleLabel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        accountField = new JTextField(18);
        passwordField = new JPasswordField(18);
        accountField.setText(members.get(0).getAccount());
        passwordField.setText(members.get(0).getPasswordHash());
        accountField.setFont(new Font(fontName, Font.PLAIN, 18));
        passwordField.setFont(new Font(fontName, Font.PLAIN, 18));
        loginMessageLabel = new JLabel("請輸入 members 帳號密碼登入");
        loginMessageLabel.setBorder(BorderFactory.createEmptyBorder(8, 0, 8, 0));

        formPanel.add(createFieldPanel("帳號:", accountField));
        formPanel.add(createFieldPanel("密碼:", passwordField));
        formPanel.add(loginMessageLabel);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        JButton loginButton = new JButton("登入");
        buttonPanel.add(loginButton);
        formPanel.add(buttonPanel);

        formPanel.add(createLoginHintPanel());
        loginButton.addActionListener(e -> login());

        page.add(formPanel, BorderLayout.CENTER);
        return page;
    }

    private JPanel createFieldPanel(String labelText, java.awt.Component field) {
        JPanel panel = new JPanel(new BorderLayout(8, 0));
        JLabel label = new JLabel(labelText);
        label.setFont(new Font(fontName, Font.PLAIN, 18));
        panel.add(label, BorderLayout.WEST);
        panel.add(field, BorderLayout.CENTER);
        panel.setBorder(BorderFactory.createEmptyBorder(6, 0, 6, 0));
        panel.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, 48));
        return panel;
    }

    private JPanel createLoginHintPanel() {
        JPanel hintPanel = new JPanel(new GridLayout(members.size() + 1, 4, 12, 4));
        hintPanel.setBorder(BorderFactory.createEmptyBorder(8, 0, 8, 0));

        hintPanel.add(createHintLabel("身分"));
        hintPanel.add(createHintLabel("account"));
        hintPanel.add(createHintLabel("password"));
        hintPanel.add(createHintLabel("登入後權限"));

        for (Member member : members) {
            hintPanel.add(createHintLabel(getMemberRoleName(member)));
            hintPanel.add(createHintValueLabel(member.getAccount()));
            hintPanel.add(createHintValueLabel(member.getPasswordHash()));
            hintPanel.add(createHintLabel(member.isAdmin() ? "借還書、會員管理、書類管理" : "借還書"));
        }

        return hintPanel;
    }

    private JLabel createHintLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font(fontName, Font.PLAIN, 14));
        return label;
    }

    private JLabel createHintValueLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font(fontName, Font.BOLD, 14));
        return label;
    }

    private JPanel createMainPanel() {
        JPanel page = new JPanel(new BorderLayout(10, 10));
        page.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        page.add(createHeaderPanel(), BorderLayout.NORTH);

        mainTabs = new JTabbedPane();
        mainTabs.addTab("借還書", createBorrowTab());
        page.add(mainTabs, BorderLayout.CENTER);

        return page;
    }

    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout(10, 0));

        JLabel titleLabel = new JLabel("librarySystemPlusII:登入與 Tabs 畫面版");
        titleLabel.setFont(new Font(fontName, Font.BOLD, 24));
        headerPanel.add(titleLabel, BorderLayout.WEST);

        JPanel userPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        currentUserLabel = new JLabel("目前身分：未登入");
        JButton logoutButton = new JButton("登出");
        logoutButton.addActionListener(e -> {
            logout();
            resetMainTabs(false);
        });
        userPanel.add(currentUserLabel);
        userPanel.add(logoutButton);
        headerPanel.add(userPanel, BorderLayout.EAST);

        return headerPanel;
    }

    private JPanel createBorrowTab() {
        JPanel tab = new JPanel(new BorderLayout(0, 10));
        tab.add(createSearchPanel(), BorderLayout.NORTH);
        tab.add(createBookTable(), BorderLayout.CENTER);
        tab.add(createBorrowOperatePanel(), BorderLayout.SOUTH);
        return tab;
    }

    private JPanel createSearchPanel() {
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        JTextField keywordField = new JTextField(18);
        JComboBox<String> categoryComboBox = new JComboBox<String>(buildCategoryOptions());
        JButton searchButton = new JButton("搜尋");
        JButton refreshButton = new JButton("重新整理");

        searchButton.addActionListener(e -> JOptionPane.showMessageDialog(this,
                "搜尋關鍵字: " + keywordField.getText().trim()
                        + ", 分類: " + categoryComboBox.getSelectedItem()));
        refreshButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "重新整理執行成功"));

        searchPanel.add(new JLabel("關鍵字:"));
        searchPanel.add(keywordField);
        searchPanel.add(new JLabel("分類:"));
        searchPanel.add(categoryComboBox);
        searchPanel.add(searchButton);
        searchPanel.add(refreshButton);
        return searchPanel;
    }

    private JScrollPane createBookTable() {
        String[] columns = { "編號", "書名", "作者", "分類", "子分類", "狀態", "借閱人" };
        bookTable = new JTable(createReadonlyTableModel(buildBookTableData(), columns));
        bookTable.setRowHeight(28);
        bookTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        return new JScrollPane(bookTable);
    }

    private JPanel createBorrowOperatePanel() {
        JPanel panel = new JPanel(new BorderLayout(0, 8));
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        JButton borrowButton = new JButton("借書");
        JButton giveBackButton = new JButton("還書");

        borrowButton.addActionListener(e -> showBorrowActionMessage("借入"));
        giveBackButton.addActionListener(e -> showBorrowActionMessage("歸還"));

        buttonPanel.add(borrowButton);
        buttonPanel.add(giveBackButton);
        panel.add(buttonPanel, BorderLayout.NORTH);
        panel.add(new JLabel(buildBookSummaryText()), BorderLayout.SOUTH);
        return panel;
    }

    private void showBorrowActionMessage(String action) {
        int selectedRow = bookTable.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "請先選擇一本書");
            return;
        }

        int modelRow = bookTable.convertRowIndexToModel(selectedRow);
        String bookName = bookTable.getModel().getValueAt(modelRow, 1).toString();
        JOptionPane.showMessageDialog(this, "會員 " + currentMember.getDisplayName() + " " + action + "「" + bookName + "」書");
    }

    private JPanel createMemberManageTab() {
        JPanel tab = new JPanel(new BorderLayout(0, 10));
        String[] columns = { "會員ID", "帳號", "顯示姓名", "是否管理員" };
        DefaultTableModel memberTableModel = createReadonlyTableModel(buildMemberTableData(), columns);
        JTable memberTable = new JTable(memberTableModel);
        memberTable.setRowHeight(28);
        memberTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tab.add(new JScrollPane(memberTable), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        JButton addButton = new JButton("新增會員");
        JButton updateInfoButton = new JButton("修改會員資料");
        JButton updatePasswordButton = new JButton("修改會員密碼");
        JButton deleteButton = new JButton("刪除會員");

        addButton.addActionListener(e -> addMember(memberTableModel, columns));
        updateInfoButton.addActionListener(e -> updateMemberInfo(memberTable, memberTableModel, columns));
        updatePasswordButton.addActionListener(e -> updateMemberPassword(memberTable));
        deleteButton.addActionListener(e -> deleteMember(memberTable, memberTableModel, columns));

        buttonPanel.add(addButton);
        buttonPanel.add(updateInfoButton);
        buttonPanel.add(updatePasswordButton);
        buttonPanel.add(deleteButton);
        tab.add(buttonPanel, BorderLayout.SOUTH);
        return tab;
    }

    private JPanel createBookTypeManageTab() {
        JPanel tab = new JPanel(new GridLayout(1, 3, 10, 0));
        tab.add(createBookManagePanel());
        tab.add(createCategoryManagePanel());
        tab.add(createItemManagePanel());
        return tab;
    }

    private JPanel createBookManagePanel() {
        String[] columns = { "編號", "書名", "作者", "分類", "子分類" };
        DefaultTableModel tableModel = createReadonlyTableModel(buildBookManageTableData(), columns);
        JTable table = new JTable(tableModel);
        table.setRowHeight(28);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JPanel panel = new JPanel(new BorderLayout(0, 8));
        panel.setBorder(BorderFactory.createTitledBorder("書籍管理"));
        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 6, 6));
        JButton addButton = new JButton("新增書籍");
        JButton updateButton = new JButton("修改書籍");
        JButton deleteButton = new JButton("刪除書籍");
        addButton.addActionListener(e -> addBook(tableModel, columns));
        updateButton.addActionListener(e -> updateBook(table, tableModel, columns));
        deleteButton.addActionListener(e -> deleteBook(table, tableModel, columns));
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(new JLabel(""));
        panel.add(buttonPanel, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel createCategoryManagePanel() {
        String[] columns = { "分類ID", "代碼", "名稱" };
        DefaultTableModel tableModel = createReadonlyTableModel(buildCategoryTableData(), columns);
        JTable table = new JTable(tableModel);
        table.setRowHeight(28);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JPanel panel = new JPanel(new BorderLayout(0, 8));
        panel.setBorder(BorderFactory.createTitledBorder("分類管理"));
        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 6, 6));
        JButton addButton = new JButton("新增分類");
        JButton updateButton = new JButton("修改分類");
        JButton deleteButton = new JButton("刪除分類");
        addButton.addActionListener(e -> addCategory(tableModel, columns));
        updateButton.addActionListener(e -> updateCategory(table, tableModel, columns));
        deleteButton.addActionListener(e -> deleteCategory(table, tableModel, columns));
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(new JLabel(""));
        panel.add(buttonPanel, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel createItemManagePanel() {
        String[] columns = { "子分類ID", "分類ID", "名稱" };
        DefaultTableModel tableModel = createReadonlyTableModel(buildItemTableData(), columns);
        JTable table = new JTable(tableModel);
        table.setRowHeight(28);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JPanel panel = new JPanel(new BorderLayout(0, 8));
        panel.setBorder(BorderFactory.createTitledBorder("子分類管理"));
        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 6, 6));
        JButton addButton = new JButton("新增子分類");
        JButton updateButton = new JButton("修改子分類");
        JButton deleteButton = new JButton("刪除子分類");
        addButton.addActionListener(e -> addItem(tableModel, columns));
        updateButton.addActionListener(e -> updateItem(table, tableModel, columns));
        deleteButton.addActionListener(e -> deleteItem(table, tableModel, columns));
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(new JLabel(""));
        panel.add(buttonPanel, BorderLayout.SOUTH);
        return panel;
    }

    private String[] buildCategoryOptions() {
        String[] options = new String[categories.size() + 1];
        options[0] = "全部";
        for (int i = 0; i < categories.size(); i++) {
            options[i + 1] = categories.get(i).getName();
        }
        return options;
    }

    private Object[][] buildBookTableData() {
        Object[][] data = new Object[books.size()][7];
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            data[i][0] = book.getNumber();
            data[i][1] = book.getTitle();
            data[i][2] = book.getAuthor();
            data[i][3] = findCategoryName(book.getCategoryId());
            data[i][4] = findItemName(book.getItemId());
            data[i][5] = book.isAvailable() ? "可借閱" : "已借出";
            data[i][6] = book.getBorrowUser();
        }
        return data;
    }

    private Object[][] buildBookManageTableData() {
        Object[][] data = new Object[books.size()][5];
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            data[i][0] = book.getNumber();
            data[i][1] = book.getTitle();
            data[i][2] = book.getAuthor();
            data[i][3] = findCategoryName(book.getCategoryId());
            data[i][4] = findItemName(book.getItemId());
        }
        return data;
    }

    private Object[][] buildCategoryTableData() {
        Object[][] data = new Object[categories.size()][3];
        for (int i = 0; i < categories.size(); i++) {
            Category category = categories.get(i);
            data[i][0] = category.getId();
            data[i][1] = category.getCode();
            data[i][2] = category.getName();
        }
        return data;
    }

    private Object[][] buildMemberTableData() {
        Object[][] data = new Object[members.size()][4];
        for (int i = 0; i < members.size(); i++) {
            Member member = members.get(i);
            data[i][0] = member.getId();
            data[i][1] = member.getAccount();
            data[i][2] = member.getDisplayName();
            data[i][3] = member.isAdmin() ? "是" : "否";
        }
        return data;
    }

    private Object[][] buildItemTableData() {
        Object[][] data = new Object[items.size()][3];
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            data[i][0] = item.getId();
            data[i][1] = item.getCategoryId();
            data[i][2] = item.getName();
        }
        return data;
    }

    private String buildBookSummaryText() {
        int availableCount = 0;
        for (Book book : books) {
            if (book.isAvailable()) {
                availableCount++;
            }
        }

        return "目前共有：" + books.size() + " 本書 可借閱：" + availableCount + " 本 已借出："
                + (books.size() - availableCount) + " 本";
    }

    private String findCategoryName(int categoryId) {
        for (Category category : categories) {
            if (category.getId() == categoryId) {
                return category.getName();
            }
        }

        return "";
    }

    private String findItemName(int itemId) {
        for (Item item : items) {
            if (item.getId() == itemId) {
                return item.getName();
            }
        }

        return "";
    }

    private void addBook(DefaultTableModel tableModel, String[] columns) {
        JTextField numberField = new JTextField(String.valueOf(getNextBookNumber()), 12);
        JTextField titleField = new JTextField(12);
        JTextField authorField = new JTextField(12);
        JComboBox<String> categoryComboBox = new JComboBox<String>(buildCategoryIdOptions());
        JComboBox<String> itemComboBox = new JComboBox<String>(buildItemIdOptions());

        JPanel formPanel = createBookFormPanel(numberField, titleField, authorField, categoryComboBox, itemComboBox);
        int result = JOptionPane.showConfirmDialog(this, formPanel, "新增書籍", JOptionPane.OK_CANCEL_OPTION);
        if (result != JOptionPane.OK_OPTION) {
            return;
        }

        String number = numberField.getText().trim();
        String title = titleField.getText().trim();
        String author = authorField.getText().trim();
        if (number.isEmpty() || title.isEmpty() || author.isEmpty()) {
            JOptionPane.showMessageDialog(this, "編號、書名、作者都必填");
            return;
        }

        if (findBookByNumber(number) != null) {
            JOptionPane.showMessageDialog(this, "書籍編號已存在");
            return;
        }

        int categoryId = parseId(categoryComboBox.getSelectedItem());
        int itemId = parseId(itemComboBox.getSelectedItem());
        Category category = findCategoryById(categoryId);
        books.add(new Book(number, title, author, true, "", 0, categoryId, itemId,
                category == null ? "" : category.getCode(),
                category == null ? "" : category.getName(),
                findItemName(itemId)));
        reloadBookManageTable(tableModel, columns);
        reloadBorrowBookTable();
        JOptionPane.showMessageDialog(this, "新增書籍執行成功");
    }

    private void updateBook(JTable table, DefaultTableModel tableModel, String[] columns) {
        Book book = getSelectedBook(table);
        if (book == null) {
            return;
        }

        JTextField numberField = new JTextField(book.getNumber(), 12);
        JTextField titleField = new JTextField(book.getTitle(), 12);
        JTextField authorField = new JTextField(book.getAuthor(), 12);
        JComboBox<String> categoryComboBox = new JComboBox<String>(buildCategoryIdOptions());
        JComboBox<String> itemComboBox = new JComboBox<String>(buildItemIdOptions());
        selectId(categoryComboBox, book.getCategoryId());
        selectId(itemComboBox, book.getItemId());

        JPanel formPanel = createBookFormPanel(numberField, titleField, authorField, categoryComboBox, itemComboBox);
        int result = JOptionPane.showConfirmDialog(this, formPanel, "修改書籍", JOptionPane.OK_CANCEL_OPTION);
        if (result != JOptionPane.OK_OPTION) {
            return;
        }

        String number = numberField.getText().trim();
        String title = titleField.getText().trim();
        String author = authorField.getText().trim();
        if (number.isEmpty() || title.isEmpty() || author.isEmpty()) {
            JOptionPane.showMessageDialog(this, "編號、書名、作者都必填");
            return;
        }

        Book sameNumberBook = findBookByNumber(number);
        if (sameNumberBook != null && !sameNumberBook.getNumber().equals(book.getNumber())) {
            JOptionPane.showMessageDialog(this, "書籍編號已存在");
            return;
        }

        int categoryId = parseId(categoryComboBox.getSelectedItem());
        int itemId = parseId(itemComboBox.getSelectedItem());
        Category category = findCategoryById(categoryId);
        replaceBook(new Book(number, title, author, book.isAvailable(), book.getBorrowUser(),
                book.getBorrowMemberId(), categoryId, itemId,
                category == null ? "" : category.getCode(),
                category == null ? "" : category.getName(),
                findItemName(itemId)), book.getNumber());
        reloadBookManageTable(tableModel, columns);
        reloadBorrowBookTable();
        JOptionPane.showMessageDialog(this, "修改書籍執行成功");
    }

    private void deleteBook(JTable table, DefaultTableModel tableModel, String[] columns) {
        Book book = getSelectedBook(table);
        if (book == null) {
            return;
        }

        int result = JOptionPane.showConfirmDialog(this,
                "確認刪除書籍「" + book.getTitle() + "」？",
                "刪除書籍",
                JOptionPane.YES_NO_OPTION);
        if (result != JOptionPane.YES_OPTION) {
            return;
        }

        books.remove(book);
        reloadBookManageTable(tableModel, columns);
        reloadBorrowBookTable();
        JOptionPane.showMessageDialog(this, "刪除書籍執行成功");
    }

    private void addCategory(DefaultTableModel tableModel, String[] columns) {
        JTextField codeField = new JTextField(12);
        JTextField nameField = new JTextField(12);
        JPanel formPanel = createCategoryFormPanel(codeField, nameField);
        int result = JOptionPane.showConfirmDialog(this, formPanel, "新增分類", JOptionPane.OK_CANCEL_OPTION);
        if (result != JOptionPane.OK_OPTION) {
            return;
        }

        String code = codeField.getText().trim();
        String name = nameField.getText().trim();
        if (code.isEmpty() || name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "代碼、名稱都必填");
            return;
        }

        categories.add(new Category(getNextCategoryId(), code, name));
        reloadCategoryTable(tableModel, columns);
        JOptionPane.showMessageDialog(this, "新增分類執行成功");
    }

    private void updateCategory(JTable table, DefaultTableModel tableModel, String[] columns) {
        Category category = getSelectedCategory(table);
        if (category == null) {
            return;
        }

        JTextField codeField = new JTextField(category.getCode(), 12);
        JTextField nameField = new JTextField(category.getName(), 12);
        JPanel formPanel = createCategoryFormPanel(codeField, nameField);
        int result = JOptionPane.showConfirmDialog(this, formPanel, "修改分類", JOptionPane.OK_CANCEL_OPTION);
        if (result != JOptionPane.OK_OPTION) {
            return;
        }

        String code = codeField.getText().trim();
        String name = nameField.getText().trim();
        if (code.isEmpty() || name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "代碼、名稱都必填");
            return;
        }

        replaceCategory(new Category(category.getId(), code, name));
        reloadCategoryTable(tableModel, columns);
        reloadBorrowBookTable();
        JOptionPane.showMessageDialog(this, "修改分類執行成功");
    }

    private void deleteCategory(JTable table, DefaultTableModel tableModel, String[] columns) {
        Category category = getSelectedCategory(table);
        if (category == null) {
            return;
        }

        int result = JOptionPane.showConfirmDialog(this,
                "確認刪除分類「" + category.getName() + "」？",
                "刪除分類",
                JOptionPane.YES_NO_OPTION);
        if (result != JOptionPane.YES_OPTION) {
            return;
        }

        categories.remove(category);
        reloadCategoryTable(tableModel, columns);
        reloadBorrowBookTable();
        JOptionPane.showMessageDialog(this, "刪除分類執行成功");
    }

    private void addItem(DefaultTableModel tableModel, String[] columns) {
        JComboBox<String> categoryComboBox = new JComboBox<String>(buildCategoryIdOptions());
        JTextField nameField = new JTextField(12);
        JPanel formPanel = createItemFormPanel(categoryComboBox, nameField);
        int result = JOptionPane.showConfirmDialog(this, formPanel, "新增子分類", JOptionPane.OK_CANCEL_OPTION);
        if (result != JOptionPane.OK_OPTION) {
            return;
        }

        String name = nameField.getText().trim();
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "名稱必填");
            return;
        }

        items.add(new Item(getNextItemId(), parseId(categoryComboBox.getSelectedItem()), name));
        reloadItemTable(tableModel, columns);
        JOptionPane.showMessageDialog(this, "新增子分類執行成功");
    }

    private void updateItem(JTable table, DefaultTableModel tableModel, String[] columns) {
        Item item = getSelectedItem(table);
        if (item == null) {
            return;
        }

        JComboBox<String> categoryComboBox = new JComboBox<String>(buildCategoryIdOptions());
        selectId(categoryComboBox, item.getCategoryId());
        JTextField nameField = new JTextField(item.getName(), 12);
        JPanel formPanel = createItemFormPanel(categoryComboBox, nameField);
        int result = JOptionPane.showConfirmDialog(this, formPanel, "修改子分類", JOptionPane.OK_CANCEL_OPTION);
        if (result != JOptionPane.OK_OPTION) {
            return;
        }

        String name = nameField.getText().trim();
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "名稱必填");
            return;
        }

        replaceItem(new Item(item.getId(), parseId(categoryComboBox.getSelectedItem()), name));
        reloadItemTable(tableModel, columns);
        reloadBorrowBookTable();
        JOptionPane.showMessageDialog(this, "修改子分類執行成功");
    }

    private void deleteItem(JTable table, DefaultTableModel tableModel, String[] columns) {
        Item item = getSelectedItem(table);
        if (item == null) {
            return;
        }

        int result = JOptionPane.showConfirmDialog(this,
                "確認刪除子分類「" + item.getName() + "」？",
                "刪除子分類",
                JOptionPane.YES_NO_OPTION);
        if (result != JOptionPane.YES_OPTION) {
            return;
        }

        items.remove(item);
        reloadItemTable(tableModel, columns);
        reloadBorrowBookTable();
        JOptionPane.showMessageDialog(this, "刪除子分類執行成功");
    }

    private JPanel createBookFormPanel(JTextField numberField, JTextField titleField, JTextField authorField,
            JComboBox<String> categoryComboBox, JComboBox<String> itemComboBox) {
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 8, 8));
        formPanel.add(new JLabel("編號："));
        formPanel.add(numberField);
        formPanel.add(new JLabel("書名："));
        formPanel.add(titleField);
        formPanel.add(new JLabel("作者："));
        formPanel.add(authorField);
        formPanel.add(new JLabel("分類："));
        formPanel.add(categoryComboBox);
        formPanel.add(new JLabel("子分類："));
        formPanel.add(itemComboBox);
        return formPanel;
    }

    private JPanel createCategoryFormPanel(JTextField codeField, JTextField nameField) {
        JPanel formPanel = new JPanel(new GridLayout(2, 2, 8, 8));
        formPanel.add(new JLabel("代碼："));
        formPanel.add(codeField);
        formPanel.add(new JLabel("名稱："));
        formPanel.add(nameField);
        return formPanel;
    }

    private JPanel createItemFormPanel(JComboBox<String> categoryComboBox, JTextField nameField) {
        JPanel formPanel = new JPanel(new GridLayout(2, 2, 8, 8));
        formPanel.add(new JLabel("分類："));
        formPanel.add(categoryComboBox);
        formPanel.add(new JLabel("名稱："));
        formPanel.add(nameField);
        return formPanel;
    }

    private String[] buildCategoryIdOptions() {
        String[] options = new String[categories.size()];
        for (int i = 0; i < categories.size(); i++) {
            Category category = categories.get(i);
            options[i] = category.getId() + " - " + category.getName();
        }
        return options;
    }

    private String[] buildItemIdOptions() {
        String[] options = new String[items.size()];
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            options[i] = item.getId() + " - " + item.getName();
        }
        return options;
    }

    private int parseId(Object selectedItem) {
        if (selectedItem == null) {
            return 0;
        }

        String text = selectedItem.toString();
        int dashIndex = text.indexOf(" - ");
        if (dashIndex < 0) {
            return Integer.parseInt(text);
        }

        return Integer.parseInt(text.substring(0, dashIndex));
    }

    private void selectId(JComboBox<String> comboBox, int id) {
        for (int i = 0; i < comboBox.getItemCount(); i++) {
            if (parseId(comboBox.getItemAt(i)) == id) {
                comboBox.setSelectedIndex(i);
                return;
            }
        }
    }

    private Book getSelectedBook(JTable table) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "請先選擇一本書");
            return null;
        }

        int modelRow = table.convertRowIndexToModel(selectedRow);
        String number = table.getModel().getValueAt(modelRow, 0).toString();
        Book book = findBookByNumber(number);
        if (book == null) {
            JOptionPane.showMessageDialog(this, "找不到選取的書籍");
        }
        return book;
    }

    private Category getSelectedCategory(JTable table) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "請先選擇一個分類");
            return null;
        }

        int modelRow = table.convertRowIndexToModel(selectedRow);
        int categoryId = Integer.parseInt(table.getModel().getValueAt(modelRow, 0).toString());
        Category category = findCategoryById(categoryId);
        if (category == null) {
            JOptionPane.showMessageDialog(this, "找不到選取的分類");
        }
        return category;
    }

    private Item getSelectedItem(JTable table) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "請先選擇一個子分類");
            return null;
        }

        int modelRow = table.convertRowIndexToModel(selectedRow);
        int itemId = Integer.parseInt(table.getModel().getValueAt(modelRow, 0).toString());
        Item item = findItemById(itemId);
        if (item == null) {
            JOptionPane.showMessageDialog(this, "找不到選取的子分類");
        }
        return item;
    }

    private Book findBookByNumber(String number) {
        for (Book book : books) {
            if (book.getNumber().equals(number)) {
                return book;
            }
        }
        return null;
    }

    private Category findCategoryById(int categoryId) {
        for (Category category : categories) {
            if (category.getId() == categoryId) {
                return category;
            }
        }
        return null;
    }

    private Item findItemById(int itemId) {
        for (Item item : items) {
            if (item.getId() == itemId) {
                return item;
            }
        }
        return null;
    }

    private void replaceBook(Book newBook, String oldNumber) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getNumber().equals(oldNumber)) {
                books.set(i, newBook);
                return;
            }
        }
    }

    private void replaceCategory(Category newCategory) {
        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).getId() == newCategory.getId()) {
                categories.set(i, newCategory);
                return;
            }
        }
    }

    private void replaceItem(Item newItem) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId() == newItem.getId()) {
                items.set(i, newItem);
                return;
            }
        }
    }

    private int getNextBookNumber() {
        int maxNumber = 0;
        for (Book book : books) {
            int number = Integer.parseInt(book.getNumber());
            if (number > maxNumber) {
                maxNumber = number;
            }
        }
        return maxNumber + 1;
    }

    private int getNextCategoryId() {
        int maxId = 0;
        for (Category category : categories) {
            if (category.getId() > maxId) {
                maxId = category.getId();
            }
        }
        return maxId + 1;
    }

    private int getNextItemId() {
        int maxId = 0;
        for (Item item : items) {
            if (item.getId() > maxId) {
                maxId = item.getId();
            }
        }
        return maxId + 1;
    }

    private void reloadBookManageTable(DefaultTableModel tableModel, String[] columns) {
        tableModel.setDataVector(buildBookManageTableData(), columns);
    }

    private void reloadCategoryTable(DefaultTableModel tableModel, String[] columns) {
        tableModel.setDataVector(buildCategoryTableData(), columns);
    }

    private void reloadItemTable(DefaultTableModel tableModel, String[] columns) {
        tableModel.setDataVector(buildItemTableData(), columns);
    }

    private void reloadBorrowBookTable() {
        if (bookTable != null) {
            bookTable.setModel(createReadonlyTableModel(buildBookTableData(),
                    new String[] { "編號", "書名", "作者", "分類", "子分類", "狀態", "借閱人" }));
        }
    }

    private DefaultTableModel createReadonlyTableModel(Object[][] data, String[] columns) {
        return new DefaultTableModel(data, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
    }

    private void addMember(DefaultTableModel memberTableModel, String[] columns) {
        JTextField accountTextField = new JTextField(12);
        JTextField displayNameTextField = new JTextField(12);
        JPasswordField passwordTextField = new JPasswordField(12);
        JCheckBox adminCheckBox = new JCheckBox("管理員");

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 8, 8));
        formPanel.add(new JLabel("帳號："));
        formPanel.add(accountTextField);
        formPanel.add(new JLabel("顯示姓名："));
        formPanel.add(displayNameTextField);
        formPanel.add(new JLabel("密碼："));
        formPanel.add(passwordTextField);
        formPanel.add(new JLabel("是否管理員："));
        formPanel.add(adminCheckBox);

        int result = JOptionPane.showConfirmDialog(this, formPanel, "新增會員", JOptionPane.OK_CANCEL_OPTION);
        if (result != JOptionPane.OK_OPTION) {
            return;
        }

        String account = accountTextField.getText().trim();
        String displayName = displayNameTextField.getText().trim();
        String password = new String(passwordTextField.getPassword());
        if (account.isEmpty() || displayName.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "帳號、顯示姓名、密碼都必填");
            return;
        }

        if (findMemberByAccount(account) != null) {
            JOptionPane.showMessageDialog(this, "帳號已存在");
            return;
        }

        members.add(new Member(getNextMemberId(), account, displayName, password, adminCheckBox.isSelected()));
        reloadMemberTable(memberTableModel, columns);
        JOptionPane.showMessageDialog(this, "新增會員執行成功");
    }

    private void updateMemberInfo(JTable memberTable, DefaultTableModel memberTableModel, String[] columns) {
        Member member = getSelectedMember(memberTable);
        if (member == null) {
            return;
        }

        JTextField accountTextField = new JTextField(member.getAccount(), 12);
        JTextField displayNameTextField = new JTextField(member.getDisplayName(), 12);
        JCheckBox adminCheckBox = new JCheckBox("管理員", member.isAdmin());

        JPanel formPanel = new JPanel(new GridLayout(3, 2, 8, 8));
        formPanel.add(new JLabel("帳號："));
        formPanel.add(accountTextField);
        formPanel.add(new JLabel("顯示姓名："));
        formPanel.add(displayNameTextField);
        formPanel.add(new JLabel("是否管理員："));
        formPanel.add(adminCheckBox);

        int result = JOptionPane.showConfirmDialog(this, formPanel, "修改會員資料", JOptionPane.OK_CANCEL_OPTION);
        if (result != JOptionPane.OK_OPTION) {
            return;
        }

        String account = accountTextField.getText().trim();
        String displayName = displayNameTextField.getText().trim();
        if (account.isEmpty() || displayName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "帳號、顯示姓名都必填");
            return;
        }

        Member sameAccountMember = findMemberByAccount(account);
        if (sameAccountMember != null && sameAccountMember.getId() != member.getId()) {
            JOptionPane.showMessageDialog(this, "帳號已存在");
            return;
        }

        replaceMember(new Member(member.getId(), account, displayName, member.getPasswordHash(), adminCheckBox.isSelected()));
        reloadMemberTable(memberTableModel, columns);
        JOptionPane.showMessageDialog(this, "修改會員資料執行成功");
    }

    private void updateMemberPassword(JTable memberTable) {
        Member member = getSelectedMember(memberTable);
        if (member == null) {
            return;
        }

        JPasswordField passwordTextField = new JPasswordField(12);
        JPanel formPanel = new JPanel(new GridLayout(1, 2, 8, 8));
        formPanel.add(new JLabel("新密碼:"));
        formPanel.add(passwordTextField);

        int result = JOptionPane.showConfirmDialog(this, formPanel, "修改會員密碼", JOptionPane.OK_CANCEL_OPTION);
        if (result != JOptionPane.OK_OPTION) {
            return;
        }

        String password = new String(passwordTextField.getPassword());
        if (password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "新密碼必填");
            return;
        }

        replaceMember(new Member(member.getId(), member.getAccount(), member.getDisplayName(), password, member.isAdmin()));
        JOptionPane.showMessageDialog(this, "修改會員密碼執行成功");
    }

    private void deleteMember(JTable memberTable, DefaultTableModel memberTableModel, String[] columns) {
        Member member = getSelectedMember(memberTable);
        if (member == null) {
            return;
        }

        int result = JOptionPane.showConfirmDialog(this,
                "確認刪除會員「" + member.getDisplayName() + "」？",
                "刪除會員",
                JOptionPane.YES_NO_OPTION);
        if (result != JOptionPane.YES_OPTION) {
            return;
        }

        members.remove(member);
        reloadMemberTable(memberTableModel, columns);
        JOptionPane.showMessageDialog(this, "刪除會員執行成功");
    }

    private Member getSelectedMember(JTable memberTable) {
        int selectedRow = memberTable.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "請先選擇一位會員");
            return null;
        }

        int modelRow = memberTable.convertRowIndexToModel(selectedRow);
        int memberId = Integer.parseInt(memberTable.getModel().getValueAt(modelRow, 0).toString());
        for (Member member : members) {
            if (member.getId() == memberId) {
                return member;
            }
        }

        JOptionPane.showMessageDialog(this, "找不到選取的會員");
        return null;
    }

    private Member findMemberByAccount(String account) {
        for (Member member : members) {
            if (member.getAccount().equals(account)) {
                return member;
            }
        }

        return null;
    }

    private void replaceMember(Member newMember) {
        for (int i = 0; i < members.size(); i++) {
            if (members.get(i).getId() == newMember.getId()) {
                members.set(i, newMember);
                if (currentMember != null && currentMember.getId() == newMember.getId()) {
                    currentMember = newMember;
                    currentUserLabel.setText("目前身分：" + newMember.getAccount()
                            + getMemberRoleDisplayText(newMember));
                }
                return;
            }
        }
    }

    private int getNextMemberId() {
        int maxId = 0;
        for (Member member : members) {
            if (member.getId() > maxId) {
                maxId = member.getId();
            }
        }
        return maxId + 1;
    }

    private void reloadMemberTable(DefaultTableModel memberTableModel, String[] columns) {
        memberTableModel.setDataVector(buildMemberTableData(), columns);
    }

    private void login() {
        String account = accountField.getText().trim();
        String password = new String(passwordField.getPassword());
        if (account.isEmpty()) {
            loginMessageLabel.setText("請輸入帳號");
            return;
        }

        if (password.isEmpty()) {
            loginMessageLabel.setText("請輸入密碼");
            return;
        }

        Member member = findMember(account, password);
        if (member != null) {
            showMainPage(member);
            return;
        }

        loginMessageLabel.setText("帳號或密碼錯誤，請使用假資料登入");
    }

    private Member findMember(String account, String password) {
        for (Member member : members) {
            if (member.getAccount().equals(account) && member.getPasswordHash().equals(password)) {
                return member;
            }
        }

        return null;
    }

    private String getMemberRoleName(Member member) {
        return member.isAdmin() ? "管理員" : "一般會員";
    }

    private String getMemberRoleDisplayText(Member member) {
        return "(" + getMemberRoleName(member) + ")";
    }

    private void showMainPage(Member member) {
        currentMember = member;
        currentUserLabel.setText("目前身分：" + member.getAccount() + getMemberRoleDisplayText(member));
        resetMainTabs(member.isAdmin());
        mainTabs.setSelectedIndex(0);
        cardLayout.show(rootPanel, MAIN_CARD);
    }

    private void logout() {
        accountField.setText("");
        passwordField.setText("");
        loginMessageLabel.setText("已登出，請重新登入");
        currentUserLabel.setText("目前身分：未登入");
        currentMember = null;
        cardLayout.show(rootPanel, LOGIN_CARD);
    }

    private void resetMainTabs(boolean admin) {
        while (mainTabs.getTabCount() > 1) {
            mainTabs.removeTabAt(1);
        }

        if (admin) {
            mainTabs.addTab("會員管理", createMemberManageTab());
            mainTabs.addTab("書類管理", createBookTypeManageTab());
        }
    }
}
