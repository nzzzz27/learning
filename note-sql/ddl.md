# DDL(Data Definition Language)
データベースの定義。  
1つのデータベースにテーブルは1つだけ持てる。  

# DB操作
## 新規DB作成
```
mysql> CREATE DATABASE データベース名;
```
## 既存のDBを確認
```
mysql> SHOW DATABASES
```

## 使用するデータベースを選択
```
mysql> USE データベース名;
```

# テーブル操作
## 作成
テーブル作成
```
CREATE TABLE テーブル (
  列1 列1の型,
  列2 列2の型,
  ...
);
```


## 制約設定
`NOT NULL`      : NULLの格納を禁止する。  
`UNIQUE`        : ある列の内容が重複してはいけない場合。NULLの重複は許容される。  
`CHECK (条件式)`: 条件式が真となる値だけが格納を許容される。  
`PRIMARY KEY`   : idなど、行を特定するために設定される列。UNIQUEかつNOT NULLの値。  
`REFERENCES 参照先テーブル(参照先列) `: 外部キー制約をかける。  
`FOREIGN KEY(参照元列名) REFERENCES 参照っ先テーブル名(参照先列名)`: CREATE TABLE文の最後にまとめて外部キー制約をかける。  
```
CREATE TABLE 家計簿 (
  日付   DATE         NOT NULL,
  費目ID INTEGER      REFERENCES 費目(ID),
  メモ   VARCHAR(100) DEFAULT '不明' NOT NULL,
  入金額 INTEGER      DEFAULT 0 CHECK (入金額 >= 0),
  出金額 INTEGER      DEFAULT 0 CHECK (出金額 >= 0),
);

CREATE TABLE 費目 (
  ID   INTEGER     PRIMARY KEY, 
  名前 VARCHAR(40) UNIQUE,
)
```


## 削除
テーブル削除
```
DROP TABLE テーブル
```

テーブルの全行を削除（初期化）
```
TRUNCATE TABLE テーブル
```


## 更新
既存テーブルに列を追加する場合、挿入位置は原則として一番最後になる。  
DBMSによっては、挿入位置を任意に指定できるものもある。  
```
//列を追加
ALTER TABLE テーブル ADD 列名 型 制約

//列を削除
ALTER TABLE テーブル DROP 列名 型 制約
```
