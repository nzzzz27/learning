## ディレクトリ構造

## `conf/root`
アプリケーションのエントリーポイントを定義する。  
Play アプリケーションには、各 URL あたり 1 つのエントリーポイントがある。これらのメソッドを**アクションメソッド**と呼びます。アクションメソッドは**コントローラと呼ぶ特別なクラスで定義されます**。

## デフォルトパッケージ

### [BaseController](https://www.playframework.com/documentation/2.7.x/api/scala/play/api/mvc/BaseController.html)
アクションおよび結果タイプを生成するユーティリティメソッドを定義する。
```
import play.api.mvc.BaseController
```
```
def list() =  Action { implicit request: Request[AnyContent] =>
  ...
}
```
