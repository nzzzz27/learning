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

//起動状況確認
$ mysql.server status
```

# ログイン・ログアウト
```
//rootユーザーでログイン
$ mysql -uroot

//rootユーザーで、パスワードありでログイン
$ mysql -u root -p

//作成したユーザーでログイン
$ mysql -u maki -p

//rootユーザーで、パスワード付きでログイン
$ mysql -u root -proot

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
# トラブル
## `mysqld_safe A mysqld process already exists`
macにある、`launchctl`という仕組みにmysqlが登録されていることが原因。  
以下のコマンドで監視を外す。  
```
//launchctlの監視下になっていることを確認
❯ launchctl list | grep mysql
30154   0       homebrew.mxcl.mysql

//監視から外す
❯ brew services stop mysql
Stopping `mysql`... (might take a while)
==> Successfully stopped `mysql` (label: homebrew.mxcl.mysql)

//再登録
brew services start サービス名
```
参考：[brewでインストールしたmysqlが勝手に再起動するのを直す](https://qiita.com/Komei22/items/31a3db6d5b803ef5082b)


# 参考
[Qiita: Macでmysqlを扱う方法](https://qiita.com/fuwamaki/items/194c2a82bd6865f26045#%E3%83%86%E3%83%BC%E3%83%96%E3%83%AB%E7%8A%B6%E6%85%8B%E7%A2%BA%E8%AA%8D)
