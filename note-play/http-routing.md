# HTTP routing

## The built-in HTTP router
routerは、HTTPリクエストをActionに変換するためのコンポーネント。 
定義ファイルは`conf/routes`ディレクトリに配置され、routesのエラーはブラウザに直接反映される。  

- クエリを含む、リクエストパス
  - ex) `/clients/1542`, `/photos/list`
- HTTPメゾット
  - `GET`, `POST`, `PATCH`, `PUT`, `DELETE`, `HEAD` 

HTTPリクエストは、MVCフレームワークによってイベントと見なされている。このイベントには、2つの主要な情報が含まれている。  


## Dependency Injection
Playのデフォルトルートジェネレーターは、@ Injectアノテーションが付けられたコンストラクターでコントローラーインスタンスを受け入れるルータークラスを作成します。  
つまり、このクラスは依存関係の注入での使用に適しており、コンストラクタを使用して手動でインスタンス化することもできます。


## The routes file syntax
ルーティングの定義方法  
```
GET   /clients/:id          controllers.Clients.show(id: Long)
```

`->`パラメータを使用すると、別のルーターを指定することもできる。  
```
->      /api                        api.MyRouter
```

## The URI pattern
URIのパターンで、ルートリクエストパスを定義することができる。  

### Static path
```
GET   /clients/all          controllers.Clients.list()
```

### Dynamic parts
```
GET   /clients/:id          controllers.Clients.show(id: Long)
```

### Dynamic parts spanning several `/` 
動的部分にスラッシュで区切られた複数のURIパスセグメントをキャプチャする場合は、* id構文を使用して動的部分を定義できます。これは、ワイルドカードパターンとも呼ばれ、。*正規表現を使用します。

例えば下記では、`GET /files/images/logo.png`のファイル名も取得できる。  
```
GET   /files/*name          controllers.Application.download(name)
```
複数の/にまたがる動的部分は、ルーターによってデコードされたり、リバースルーターによってエンコードされたりしないことに注意してください。ユーザー入力の場合と同様に、未加工のURIセグメントを検証するのはユーザーの責任です。  
リバースルーターは単に文字列を連結するだけなので、結果のパスが有効であることを確認する必要があります。たとえば、複数の先行スラッシュや非ASCII文字が含まれていないことを確認してください。  

### Dynamic parts with custom regular expressions
`id <regex>`構文を使用して、動的部分に独自の正規表現を定義することもできます。  

```
GET   /items/$id<[0-9]+>    controllers.Items.show(id: Long)
```
ワイルドカードパターンのように、パラメータはルーターによってデコードされない。  


## Call to the Action generator method

## Routing priority
競合がある場合、最初のルート（宣言順）が使用される。  

## Reverse routing

## Relative routes

## The Default Controller

## Custom routing


# 関連リンク
- [HTTP routing](https://www.playframework.com/documentation/2.8.x/ScalaRouting)
