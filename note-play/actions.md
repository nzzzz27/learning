# Actions, Controllers and Results

## Actionとは
Playで受け取るリクエストのほとんどは、Actionによって操作される。  

`play.api.mvc.Action`は、基本的にリクエストとクライアントに送られる結果を生成するための関数`(play.api.mvc.Request => play.api.mvc.Result)`である。  

```
def echo = Action { request =>
  Ok("Got request [" + request + "]")
}
```

Actionは、Webクライアントに送るためのHTTPリスポンスを表す`play.api.mvc.Result`の値を返す。  
上記の例では、OKはtext / plain応答本文を含む200 OK応答を作成している。  


## Building an Action
`BaseController`を継承するControllerであれば、`Action`値がデフォルトのアクションビルダーとなる。  
このアクションビルダーは、`Action`を生成するためのいくつかのヘルパーを持っている。  

```
Action {
  Ok("Hello world")
}
```
リクエストへの参照は取得しない。  
多くの場合、このアクションを呼び出すHTTPリクエストにアクセスすると便利。  

```
Action { request =>
  Ok("Got request [" + request + "]")
}
```
関数Request => Resultを引数として取り、リクエストへの参照を取得する。  


```
Action { implicit request =>
  Ok("Got request [" + request + "]")
}
```
多くの場合、リクエストパラメータを暗黙的としてマークすると、それを必要とする他のAPIで暗黙的に使用できるようになる。  

```
def action = Action { implicit request =>
  anotherMethod("Some para value")
  Ok("Got request [" + request + "]")
}
```

```
Action(parse.json) { implicit request =>
  Ok("Got request [" + request + "]")
}
```
追加のBodyParser引数を指定する。  
これ以外の書き方をした場合は、どんなBody Parserでも使用する。  


## Controllers are action generators
Playのコントローラーは、アクション値を生成するオブジェクトにすぎない。コントローラーは通常、依存性注入を利用するクラスとして定義される。  

アクションジェネレータ定義の例：  
```
package controllers

import javax.inject.Inject

import play.api.mvc._

class Application @Inject() (cc: ControllerComponents) extends AbstractController(cc) {
  def index = Action {
    Ok("It works!")
  }
}
```

パラメータを持つ場合の定義の例：  
```
def hello(name: String) = Action {
  Ok("Hello " + name)
}
```

### 注意
コントローラーの定義は、クラスを使用することが推奨。  
近い将来、オブジェクトとして定義されたコントローラーはサポートされなくなるため。 


## Simple results 
以下の結果は、`play.api.mvc.Result`によって定義される。  

```
import play.api.http.HttpEntity

def index = Action {
  Result(
    header = ResponseHeader(200, Map.empty),
    body = HttpEntity.Strict(ByteString("Hello world!"), Some("text/plain"))
  )
}
```
その他の例：
```
val ok           = Ok("Hello world!")
val notFound     = NotFound
val pageNotFound = NotFound(<h1>Page not found</h1>)
val badRequest   = BadRequest(views.html.form(formWithErrors))
val oops         = InternalServerError("Oops")
val anyStatus    = Status(488)("Strange response type")
```
上記は全て、`play.api.mvc.Results`のトレイトとコンパニオンオブジェクトで見つけることができる。  


## Redirects are simple results too
リダイレクトの書き方。  
```
def index = Action {
  Redirect("/user/home")
}
```

デフォルトは`303 SEE_OTHER`だが、カスタマイズもできる。  
```
def index = Action {
  Redirect("/user/home", MOVED_PERMANENTLY)
}
```


# 参考リンク
- [Actions, Controllers and Results](https://www.playframework.com/documentation/2.8.x/ScalaActions)
