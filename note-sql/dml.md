# DML(Data Manipulation Language)

# 4大命令
```
//テーブルの内容を全て表示
SELECT * FROM テーブル;

//列Cの値が100以上の列A
SELECT 列A FROM テーブル WHERE 列C > 100;

//データをテーブルに追加
INSERT INTO テーブル VALUES ('2018-02-28', '娯楽費', '映画を見た', 0, 1800);

//列Aが1の行の、列Bの値を修正
UPDATE テーブル SET 列B = 値 WHERE 列A = 1;

//データを全て消去
DELETE FROM テーブル
```

