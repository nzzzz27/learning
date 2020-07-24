# DCL(Data Control Language)
誰にどのようなデータ操作やテーブル操作を許すかといった権限を設定するためのSQL命令の総称。  
権限を付与するGRANT文と、剥奪するREVOKE文がある。  

```
//権限付与
GRANT 権限名 To ユーザー名

//権限剥奪
REVOLE 権限名 FROM ユーザー名
```

これらは特に、データベースの全権を管理するデータベース管理者（DBA: Database Administrator)だけが使う命令。DBMS製品によって、構文や位置付けが大きく異なるため、詳細は各製品のドキュメントを参照する。


