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

// ユーザーと認証プラグインを確認
mysql> select user, host, plugin from mysql.user;

// ユーザーを指定して、認証プラグインを確認
mysql> select user, host, plugin from mysql.user where user = "ユーザー名";
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

## `SQLException: Unable to load authentication plugin 'caching_sha2_password'.`
MySQL8.0には、認証プラグイン`caching_sha2_password`が新規追加された。  
MySQL5.7までの認証プラグインには`mysql_native_password`がデフォルトで使用。  

`caching_sha2_password`は、SHA-256を使用してより安全なパスワードの暗号化を提供するとともに、キャッシュを使用して同一ユーザの認証処理を高速化しようという、MySQL推奨の認証プラグイン。　　
接続に使用するクライアント又はコネクタ側でも`caching_sha2_password`をサポートしている必要があり、サポートされていない場合は、認証エラーが返される。   

### 解決策1
クライアントまたはコネクタ側でサポートできない場合は、MySQLの認証設定を変更する。  
```
mysql> create user user2@localhost identified with mysql_native_password by 'user2_Password';
```

参考：  
- [MySQL8.0新機能 (caching_sha2_password 認証プラグイン)](https://www.s-style.co.jp/blog/2018/05/1807/)  
- [caching_sha2_password-Compatible Clients and Connectors](https://dev.mysql.com/doc/refman/8.0/en/upgrading-from-previous-series.html#upgrade-caching-sha2-password-compatible-connectors)

# 参考
[Qiita: Macでmysqlを扱う方法](https://qiita.com/fuwamaki/items/194c2a82bd6865f26045#%E3%83%86%E3%83%BC%E3%83%96%E3%83%AB%E7%8A%B6%E6%85%8B%E7%A2%BA%E8%AA%8D)
