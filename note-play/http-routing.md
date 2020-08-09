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
ルーターは、Scala呼び出し内からURLを生成するためにも使用できます。これにより、すべてのURIパターンを単一の構成ファイルに集中させることができるため、アプリケーションのリファクタリングをより確実に行うことができます。  

ルーターは、ルートファイルで使用される各コントローラーについて、ルートパッケージに「リバースコントローラー」を生成し、同じアクションメソッドと同じシグネチャを持ちますが、`play.api.mvc.Action`ではなく`play.api.mvc.Call`を返します。  

`play.api.mvc.Call`はHTTP呼び出しを定義し、HTTPメソッドとURIの両方を提供します。  

たとえば、次のようなコントローラを作成するとします。  

```
package controllers
  import javax.inject.Inject

  import play.api._
  import play.api.mvc._

  class Application @Inject() (cc: ControllerComponents) extends AbstractController(cc) {
    def hello(name: String) = Action {
      Ok("Hello " + name + "!")
    }
  }
```
そして、それをconf / routesファイルにマッピングした場合：  
```
# Hello action
GET   /hello/:name          controllers.Application.hello(name)
```

その後、controllers.routes.Applicationリバースコントローラを使用して、helloアクションメソッドへのURLを逆にすることができます。  
```
// Redirect to /hello/Bob
def helloBob = Action {
  Redirect(routes.Application.hello("Bob"))
}
```

Note:   
各コントローラーパッケージには、ルートサブパッケージがあります。したがって、controllers.Applications.helloアクションは、controllers.routes.Application.helloを介して逆にすることができます。（たまたま生成されたパスと一致するroutesファイルにそれより前に他のルートがない限り）。
リバースアクションメソッドは非常に単純に機能します。パラメーターを受け取り、ルートパターンに置き換えます。  
パスセグメント（：foo）の場合、値は置換が行われる前にエンコードされます。正規表現およびワイルドカードパターンの場合、値は複数のセグメントにわたる可能性があるため、文字列はそのままの形式で置き換えられます。  
これらのコンポーネントを逆ルートに渡すときは、必要に応じてこれらのコンポーネントをエスケープし、未検証のユーザー入力を渡さないようにしてください。 


### Reverse Routingに関するメモ
Reverse Routingとは、
```
GET     /tweet/list                 controllers.tweet.TweetController.list
```
例えば、上記のRoutingの、`contollers.tweet.TweetController.list`に到達しようとしているメゾットがある`/tweet/list`を取得する事。  

Reverse Routingの構築方法：   
```
[full package name].routes.[controller].[method]

controllers.tweet.routes.TweetController.store()
```

Reverse Routeは、HTTPメゾットとURIを含むCallを返す。  
```
FakeRequest(controllers.routes.Authentication.authenticate)
```

また、リダイレクトURIをテストするために使用することもできる。  
```
val call: Call = controllers.routes.Events.events
redirectLocation(loginResult) must beSome(call.url)
```

参考：  
- [2つの逆ルーティングを再生し、コントローラメソッドからルートを取得する](http://www.366service.com/jp/qa/9ce110d1755eb75a05ee95f23f5ce2cb)


## Relative routes

## The Default Controller

## Custom routing


# 関連リンク
- [HTTP routing](https://www.playframework.com/documentation/2.8.x/ScalaRouting)
