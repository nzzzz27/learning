# DML(Data Manipulation Language)

## 4大命令
- 分類方法1 検索系と更新系  
  - 検索系： SELECT  
  - 更新系: UPDATE, INSERT, DELETE 
- 分類方法2 既存系と新規系
  - 既存系: SELECT, UPDATE, DELETE 
  - 新規系: INSERT

### `SELECT`
```
//テーブルの内容を全て表示
SELECT * FROM テーブル;

//列Cの値が100以上の列A
SELECT 列A FROM テーブル WHERE 列C > 100;
```
### `INSERT`
```
//データを、特定の列のテーブルに追加
INSERT INTO テーブル 
  (列名1, 列名2, 列名3, 列名4)
  VALUES ('2018-02-28', '娯楽費', '映画を見た', 0, 1800); 
  
//データを、全ての列のテーブルに追加
INSERT INTO テーブル 
  VALUES ('2018-02-28', '娯楽費', '映画を見た', 0, 1800);  

//複数のレコードを一括追加
INSERT テーブル (
 列名1, 列名2
) values (
  値1, 値2
), (
  値1, 値2
);
```

### `UPDATE`
```
//列Aが1の行の、列Bの値を修正
UPDATE テーブル SET 列B = 値 WHERE 列A = 1;
```
### `DELETE`
```
//データを全て消去
DELETE FROM テーブル
```

## `WHERE`句  
処理対象行の絞り込みに用いる。`WHERE`句を利用しないと、全ての行が対象となる。  
`SELECT`, `UPDATE`, `DELETE`文で使用可能。  
```
WHERE 条件式
```

## 比較演算子
|比較演算子|意味|
|:---|:---|
|=|左右の値が等しい|
|<|左辺は右辺より小さい|
|>|左辺は右辺より大きい|
|<=|左辺は右辺の値以下|
|>=|左辺は右辺の値以上|
|<>|左右の値が等しくない|
