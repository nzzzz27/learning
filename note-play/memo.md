## ディレクトリ構造

## `conf/root`
アプリケーションのエントリーポイントを定義する。  
Play アプリケーションには、各 URL あたり 1 つのエントリーポイントがある。これらのメソッドを**アクションメソッド**と呼びます。アクションメソッドは**コントローラと呼ぶ特別なクラスで定義されます**。

## デフォルトパッケージ

### [BaseController](https://www.playframework.com/documentation/2.7.x/api/scala/play/api/mvc/BaseController.html)
アクションの結果を生成するユーティリティメソッドを定義する。
```
import play.api.mvc.BaseController
```
```
class HomeController @Inject() (val controllerComponents: ControllerComponents) extends BaseController {
  
  def hello(name:String) = Action { request =>
    //リクエスト結果がOKの時の処理
    Ok("Hello " + name)
  }

}
```

## `@routes`
> Playがデフォルトでcontrollers.routesをインポートしているために{controllers}が省略されたものです。


## URLの意味
```
controllers.tweet.TweetController.register 
// controllers.tweet => controllers/tweet
// TweetController.register => TweetControllerクラスのregisterメゾット

views.html.tweet.list(tweets)
// views.html => views/
// tweet.list() => tweet/list.scala.html
```

