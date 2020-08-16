# Play framework 

## Routing
### データの流れ
![mvc](https://user-images.githubusercontent.com/62970114/89749000-efed2300-db00-11ea-8767-84b3d31ef9fb.png)

### 紐づくファイルの指定方法
```
controllers.tweet.TweetController.register
// controllers.tweet => controllers/tweet
// TweetController.register => TweetControllerクラスのregisterメゾット

views.html.tweet.list(tweets)
// views.html => views/
// tweet.list() => tweet/list.scala.html
```

### リバースルーティング
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




## HTTP
### Action
Controllerにあるリクエストに対するメソッドの事。  

```
戻り値：`play.api.mvc.Result`型の関数
```

- [公式ドキュメント](https://www.playframework.com/documentation/ja/2.4.x/ScalaActions)
- [packages](https://www.playframework.com/documentation/2.8.x/api/scala/play/api/mvc/Action.html#apply(request:play.api.mvc.Request[A]):scala.concurrent.Future[play.api.mvc.Result])

```
//Actionの例

class TweetController @Inject()(val controllerComponents: ControllerComponents) extends BaseController with play.api.i18n.I18nSupport {
  //...省略
  
  //Action  
  def list() =  Action { implicit request: Request[AnyContent] =>
    Ok(views.html.tweet.list(tweets.toSeq))
  }
}
```



## Form
- [公式ドキュメント](https://www.playframework.com/documentation/ja/2.4.x/ScalaForms)
- [packages](https://www.playframework.com/documentation/2.8.x/api/scala/play/api/data/Form.html)

### フォームの定義方法
1. 必要なパッケージを読み込む
```
import play.api.data._
import play.api.data.Forms._
```

2. フォームを定義する
2-1.ケースクラスで必要な要素を定義
```
case class UserData(name: String, age: Int)
```

2-2. Form関数で、データをバインドされたケースクラスのインスタンスに変換
```
val userForm = Form(
  mapping(
    "name" -> text,
    "age" -> number
  )(UserData.apply)(UserData.unapply)
)
```

3. [フォームに制約を定義する](https://www.playframework.com/documentation/ja/2.4.x/ScalaForms#%E3%83%95%E3%82%A9%E3%83%BC%E3%83%A0%E3%81%AB%E5%88%B6%E7%B4%84%E3%82%92%E5%AE%9A%E7%BE%A9%E3%81%99%E3%82%8B)
```
val userFormConstraints2 = Form(
  mapping(
    "name" -> nonEmptyText,
    "age" -> number(min = 0, max = 100)
  )(UserData.apply)(UserData.unapply)
)
```

- [Forms](https://www.playframework.com/documentation/ja/2.4.x/api/scala/index.html#play.api.data.Forms$) Object：デフォルトで使える制約一覧
- [アドホック制約](https://www.playframework.com/documentation/ja/2.4.x/ScalaForms#%E3%82%A2%E3%83%89%E3%83%9B%E3%83%83%E3%82%AF%E5%88%B6%E7%B4%84%E3%81%AE%E5%AE%9A%E7%BE%A9)：独自の制約を定義する場合


4. [アクション内のフォームの検証](https://www.playframework.com/documentation/ja/2.4.x/ScalaForms#%E3%82%A2%E3%82%AF%E3%82%B7%E3%83%A7%E3%83%B3%E5%86%85%E3%81%AE%E3%83%95%E3%82%A9%E3%83%BC%E3%83%A0%E3%81%AE%E6%A4%9C%E8%A8%BC)

```
userForm.bindFromRequest.fold(
  formWithErrors => {
    // binding failure, you retrieve the form containing errors:
    BadRequest(views.html.user(formWithErrors))
  },
  userData => {
    /* binding success, you get the actual value. */
    val newUser = models.User(userData.name, userData.age)
    val id = models.User.create(newUser)
    Redirect(routes.Application.home(id))
  }
)
```

- 成功の場合：指定した処理
- 失敗の場合：`BadRequest`のページをレンダリング


5. [ビューテンプレートにフォームを表示する](https://www.playframework.com/documentation/ja/2.4.x/ScalaForms#%E3%83%93%E3%83%A5%E3%83%BC%E3%83%86%E3%83%B3%E3%83%97%E3%83%AC%E3%83%BC%E3%83%88%E3%81%AB%E3%83%95%E3%82%A9%E3%83%BC%E3%83%A0%E3%82%92%E8%A1%A8%E7%A4%BA%E3%81%99%E3%82%8B)

5-1. Form構造をテンプレートエンジンに渡す
```
@(userForm: Form[UserData])(implicit messages: Messages)
```

5-2. [ビューヘルパー](https://www.playframework.com/documentation/ja/2.4.x/api/scala/index.html#views.html.helper.form$)でタグを生成
```
@helper.form(action = routes.Application.userPost()) {
  @helper.inputText(userForm("name"))
  @helper.inputText(userForm("age"))
}
```
- [package: views.html.helper](https://www.playframework.com/documentation/ja/2.4.x/api/scala/index.html#views.html.helper.form$)
- [カスタムフィールドを設定](https://www.playframework.com/documentation/ja/2.4.x/ScalaCustomFieldConstructors)


6. [ビューテンプレートでのエラーの表示](https://www.playframework.com/documentation/ja/2.4.x/ScalaForms#%E3%83%93%E3%83%A5%E3%83%BC%E3%83%86%E3%83%B3%E3%83%97%E3%83%AC%E3%83%BC%E3%83%88%E3%81%A7%E3%81%AE%E3%82%A8%E3%83%A9%E3%83%BC%E3%81%AE%E8%A1%A8%E7%A4%BA)
フィールドのエラーは`Map[String,FormError]`の形をとり、フォームの`@helper`を使って自動的にレンダリングされる。   

例えば、エラーのある @helper.inputText は次のように表示される。   
```
<dl class="error" id="age_field">
    <dt><label for="age">Age:</label></dt>
    <dd><input type="text" name="age" id="age" value=""></dd>
    <dd class="error">This field is required!</dd>
    <dd class="error">Another error</dd>
    <dd class="info">Required</dd>
    <dd class="info">Another constraint</dd>
</dl>
```
- [package: FormError](https://www.playframework.com/documentation/ja/2.4.x/api/scala/index.html#play.api.data.FormError)


7. [CSRF対策](https://www.playframework.com/documentation/ja/2.4.x/ScalaCsrf)
Play はリクエストが CSRF リクエストでないことを検証する複数のメソッドを提供しています。その主要なメカニズムは CSRF トークンです。  

- [公式ドキュメント英語版](https://www.playframework.com/documentation/2.8.x/ScalaCsrf)（こっちの方が詳しく載っている）

このトークンは、クエリ文字列、または投稿されたすべてのフォームのボディ部分に配置され、同様にユーザーのセッションにも配置されます。そして、Play はこの双方のトークンが存在し、一致することを検証します。  

設定には、目的に合わせて2パターンある。  

7-A. [グローバル設定](https://www.playframework.com/documentation/ja/2.4.x/ScalaCsrf#%E3%82%B0%E3%83%AD%E3%83%BC%E3%83%90%E3%83%AB-CSRF-%E3%83%95%E3%82%A3%E3%83%AB%E3%82%BF%E3%81%AE%E9%81%A9%E7%94%A8)

7-B. [アクションごとにフィルタを設定](https://www.playframework.com/documentation/ja/2.4.x/ScalaCsrf#%E3%82%A2%E3%82%AF%E3%82%B7%E3%83%A7%E3%83%B3%E3%81%94%E3%81%A8%E3%81%AB-CSRF-%E3%83%95%E3%82%A3%E3%83%AB%E3%82%BF%E3%83%AA%E3%83%B3%E3%82%B0%E3%82%92%E9%81%A9%E7%94%A8%E3%81%99%E3%82%8B)


## DB接続（MySQL）
- [SQLデータベース](https://www.playframework.com/documentation/ja/2.3.x/JavaDatabase)
- [docker-composeで接続](https://qiita.com/LazyHippos/items/58d0f98a15656ed65136)
