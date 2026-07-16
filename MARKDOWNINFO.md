# Markdown 語法與範例

Markdown 是一種輕量級標記語言，可使用簡單的純文字語法編排文件。

## 1. 標題

使用 `#` 建立標題，`#` 數量代表標題層級，最多六層。

```markdown
# 一級標題
## 二級標題
### 三級標題
#### 四級標題
##### 五級標題
###### 六級標題
```

## 2. 段落與換行

段落之間保留一個空白行。

```markdown
這是第一個段落。

這是第二個段落。
```

若要在同一段內強制換行，可在行尾加入兩個空格，或使用 `<br>`。

```markdown
第一行  
第二行

第一行<br>
第二行
```

## 3. 粗體、斜體與刪除線

```markdown
**粗體文字**
*斜體文字*
***粗斜體文字***
~~刪除線文字~~
```

顯示效果：

**粗體文字**  
*斜體文字*  
***粗斜體文字***  
~~刪除線文字~~

## 4. 引用

使用 `>` 建立引用，多個 `>` 可形成巢狀引用。

```markdown
> 這是一段引用。
>
> > 這是第二層引用。
```

顯示效果：

> 這是一段引用。
>
> > 這是第二層引用。

## 5. 無序清單

可使用 `-`、`*` 或 `+` 建立無序清單。

```markdown
- 蘋果
- 香蕉
  - 黃香蕉
  - 綠香蕉
- 葡萄
```

顯示效果：

- 蘋果
- 香蕉
  - 黃香蕉
  - 綠香蕉
- 葡萄

## 6. 有序清單

```markdown
1. 開啟編輯器
2. 建立 Markdown 檔案
3. 輸入內容
   1. 加入標題
   2. 加入段落
```

顯示效果：

1. 開啟編輯器
2. 建立 Markdown 檔案
3. 輸入內容
   1. 加入標題
   2. 加入段落

## 7. 待辦清單

```markdown
- [x] 建立檔案
- [x] 撰寫標題
- [ ] 補充內容
```

顯示效果：

- [x] 建立檔案
- [x] 撰寫標題
- [ ] 補充內容

## 8. 超連結

```markdown
[Google](https://www.google.com)
[帶有提示文字的連結](https://www.google.com "前往 Google")
<https://www.google.com>
```

顯示效果：

[Google](https://www.google.com)  
[帶有提示文字的連結](https://www.google.com "前往 Google")  
<https://www.google.com>

## 9. 圖片

圖片語法與連結相似，但前面需要加上 `!`。

```markdown
![圖片替代文字](https://example.com/image.png)
![圖片替代文字](./images/example.png "圖片標題")
```

## 10. 行內程式碼

使用一對反引號包住程式碼。

```markdown
使用 `git status` 查看 Git 狀態。
```

顯示效果：

使用 `git status` 查看 Git 狀態。

## 11. 程式碼區塊

使用三個反引號包住程式碼，並可在開頭指定程式語言以啟用語法上色。

````markdown
```javascript
function greet(name) {
  console.log(`Hello, ${name}!`);
}

greet("Markdown");
```
````

顯示效果：

```javascript
function greet(name) {
  console.log(`Hello, ${name}!`);
}

greet("Markdown");
```

## 12. 分隔線

使用三個以上的 `-`、`*` 或 `_` 建立分隔線。

```markdown
---
```

顯示效果：

---

## 13. 表格

第二列的冒號可控制欄位對齊方式。

```markdown
| 姓名 | 職位 | 分數 |
| :--- | :---: | ---: |
| 小明 | 工程師 | 95 |
| 小美 | 設計師 | 90 |
```

顯示效果：

| 姓名 | 職位 | 分數 |
| :--- | :---: | ---: |
| 小明 | 工程師 | 95 |
| 小美 | 設計師 | 90 |

- `:---`：靠左對齊
- `:---:`：置中對齊
- `---:`：靠右對齊

## 14. 跳脫特殊字元

若要顯示 Markdown 的特殊符號，可在符號前加上反斜線 `\`。

```markdown
\*這段文字不會變成斜體\*
\# 這段文字不會變成標題
```

顯示效果：

\*這段文字不會變成斜體\*  
\# 這段文字不會變成標題

## 15. HTML 標籤

多數 Markdown 處理器支援部分 HTML 標籤。

```markdown
<details>
  <summary>點擊展開</summary>

  這是隱藏的詳細內容。
</details>
```

顯示效果：

<details>
  <summary>點擊展開</summary>

  這是隱藏的詳細內容。
</details>

## 16. 註腳

註腳屬於擴充語法，支援情況依 Markdown 平台而異。

```markdown
這是一段包含註腳的文字。[^1]

[^1]: 這是註腳內容。
```

## 17. 常用組合範例

````markdown
# 專案名稱

這是一段簡短的專案介紹。

## 功能

- 支援使用者登入
- 顯示資料列表
- 匯出報表

## 安裝方式

```bash
git clone https://github.com/example/project.git
cd project
npm install
npm run dev
```

## 開發進度

- [x] 建立專案
- [x] 完成首頁
- [ ] 加入測試

## 相關文件

請參考[官方網站](https://example.com)。
````

## 注意事項

- 不同平台使用的 Markdown 規格可能略有差異。
- 表格、待辦清單、刪除線與註腳通常屬於擴充語法。
- GitHub 使用的版本稱為 GitHub Flavored Markdown（GFM）。
- 建議在標題、段落、清單與程式碼區塊之間保留空白行，以提升相容性及可讀性。
