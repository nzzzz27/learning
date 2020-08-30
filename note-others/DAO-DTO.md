# DAO/DTOとは
オブジェクトのデザインパターンの一つ  

## DAO（Data Access Object）クラス
データベースやファイル、RESTful APIに対してCRUD処理を行う専門的なクラス。  
DAOとしてカプセル化している。  

特徴：  
- データを操作するためのインターフェイス(メソッド)を提供することで、ビジネスロジックとデータ操作を分離できる  
- データストアの詳細をData Access Objectの利用側から隠蔽することで、データストア技術を更新／置換したとしてもアプリケーション全体への影響を最小限にすることができる


## DTO（Data Transfer Object）クラス
データを転送するために作られるオブジェクト。  
基本的には以下の構成で作られる。  

- フィールド：データを格納する
- アクセサメゾット：フィールドへアクセスするためのメゾット（Getter / Setter）  


## 参考サイト
- [JavaのData Access Object（DAO）クラスとDTO（Data Transfer Object）クラスとは【初心者向け】](https://techacademy.jp/magazine/19443)
