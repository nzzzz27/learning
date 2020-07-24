# インストール方法
```
//install
$ brew install mysql

//インストールしたmysqlの情報を確認
$ brew info mysql

//バージョン確認
$ mysql --version
```

# 起動・停止
```
//起動
$ mysql.server start 

//パスワードなしでログイン
$ mysql.server start --skip-grant-tables 

//停止
$ mysql.server stop 
```

# ログイン・ログアウト
```
//rootユーザーでログイン
$ mysql -uroot

//作成したユーザーでログイン
$ mysql -u maki -p

//ログアウト
mysql> exit
mysql> quit
mysql> \q
```

# ユーザー作成
```
//ユーザー作成
mysql> create user 'ユーザー名'@localhost identified by 'パスワード';

//ユーザー削除
mysql> drop user 'ユーザー名'@localhost;
```


# 参考
[Qiita: Macでmysqlを扱う方法](https://qiita.com/fuwamaki/items/194c2a82bd6865f26045#%E3%83%86%E3%83%BC%E3%83%96%E3%83%AB%E7%8A%B6%E6%85%8B%E7%A2%BA%E8%AA%8D)
