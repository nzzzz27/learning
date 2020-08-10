# [Handling form submission](https://www.playframework.com/documentation/2.8.x/ScalaForms)
フォームの処理と送信は、あらゆるWebアプリケーションの重要な部分です。 Playには、単純なフォームを簡単に処理したり、複雑なフォームを処理したりできる機能が付属しています。  
Playのフォーム処理アプローチは、データのバインドの概念に基づいています。 POSTリクエストからデータが受信されると、Playはフォーマットされた値を探し、それらをFormオブジェクトにバインドします。  

通常、フォームはBaseControllerインスタンスから直接使用されます。
ただし、フォーム定義はケースクラスまたはモデルと正確に一致する必要はありません。これらは純粋に入力を処理するためのものであり、個別のPOSTに対して個別のフォームを使用することは妥当です。  

https://www.playframework.com/documentation/2.8.x/api/scala/play/api/data/Forms$.html#mapping%5BR%2CA1%5D((String%2CMapping%5BA1%5D))((A1)%E2%87%92R)((R)%E2%87%92Option%5BA1%5D)%3AMapping%5BR%5D

## Imports 
フォームを使用するには、次のパッケージをクラスにインポートします。  

```
import play.api.data._
import play.api.data.Forms._
```

バリデーションと制約を利用するには、次のパッケージをクラスにインポートします。  
```
import play.api.data.validation.Constraints._
```

## Form Basics
![...](https://www.playframework.com/documentation/2.8.x/resources/manual/working/scalaGuide/main/forms/images/lifecycle.png)

### Defining a form
フォームに必要な要素を含むケースクラスを定義します。  

ここでは、ユーザーの名前と年齢を取得したいので、UserDataオブジェクトを作成します。
```
case class UserData(name: String, age: Int)
```

ケースクラスができたので、次のステップはフォーム構造を定義することです。   
Formの機能は、フォームデータをケースクラスのバインドされたインスタンスに変換することであり、次のように定義します。
```
val userForm = Form(
  mapping(
    "name" -> text,
    "age"  -> number
  )(UserData.apply)(UserData.unapply)
)
```

Formsオブジェクトはmapping methodを定義します。  

このメソッドは、フォームの名前と制約を受け取り、`apply function`と`unapply function`も受け取ります。  
UserDataはケースクラスであるため、applyメソッドとunapplyメソッドをマッピングメソッドに直接プラグインできます。  

フォームは、マップが与えられると、バインドされた値を持つUserDataインスタンスを作成します。

```
val anyData  = Map("name" -> "bob", "age" -> "21")
val userData = userForm.bind(anyData).get
```

ただし、ほとんどの場合、リクエストから提供されたデータを使用して、アクション内からフォームを使用します。  
フォームには[bindFromRequest](https://www.playframework.com/documentation/2.8.x/api/scala/play/api/data/Form.html#bindFromRequest(data:Map[String,Seq[String]]):play.api.data.Form[T])が含まれており、暗黙のパラメーターとして要求を受け取ります。暗黙のリクエストを定義すると、bindFromRequestがそれを見つけます。  

```
val userData = userForm.bindFromRequest.get
```

フォームマッピングでのケースクラスの使用に限定されません。applyメソッドとunapplyメソッドが適切にマッピングされている限り、Forms.tupleマッピングを使用するタプルやモデルケースクラスなど、好きなものを渡すことができます。   
ただし、フォーム専用のケースクラスを定義することには、いくつかの利点があります。  

- フォーム固有のケースクラスが便利です
  - Caseクラスは、データの単純なコンテナになるように設計されており、フォームの機能と自然に一致するすぐに使える機能を提供します。
- フォーム固有のケースクラスは強力です
  - タプルは便利に使用できますが、カスタムの適用または適用解除メソッドは許可されず、含まれているデータをアリティ（`_1`、`_2`など）でのみ参照できます。
- フォーム固有のケースクラスは、特にフォームを対象としています
  - モデルケースクラスの再利用は便利ですが、多くの場合、モデルには追加のドメインロジックが含まれ、緊密な結合につながる永続性の詳細さえ含まれます。  
  - さらに、フォームとモデルの間に直接1：1マッピングがない場合、パラメータ改ざん攻撃を防ぐために、機密フィールドを明示的に無視する必要があります。

### Defining constraints on the form
テキスト制約では、空の文字列が有効であると見なされます。  
これは、ここでエラーなしに名前が空になる可能性があることを意味します。名前に適切な値があることを確認する方法は、nonEmptyText制約を使用することです。  
```
val userFormConstraints2 = Form(
  mapping(
    "name" -> nonEmptyText,
    "age"  -> number(min = 0, max = 100)
  )(UserData.apply)(UserData.unapply)
)
```

このフォームを使用すると、フォームへの入力が制約に一致しない場合、エラーが発生します。  
```
val boundForm = userFormConstraints2.bind(Map("bob" -> "", "age" -> "25"))
boundForm.hasErrors must beTrue
```
すぐに使える制約は、Formsオブジェクトで定義されます。  
- text: maps to scala.String, optionally takes minLength and maxLength.
- nonEmptyText: maps to scala.String, optionally takes minLength and maxLength.
- number: maps to scala.Int, optionally takes min, max, and strict.
- longNumber: maps to scala.Long, optionally takes min, max, and strict.
- bigDecimal: takes precision and scale.
- date, sqlDate: maps to java.util.Date, java.sql.Date, optionally takes pattern and timeZone.
- email: maps to scala.String, using an email regular expression.
- boolean: maps to scala.Boolean.
- checked: maps to scala.Boolean.
- optional: maps to scala.Option.

### Defining ad-hoc constraints

### Validating a form in an Action

### Showing forms in a view template
フォームを作成したら、テンプレートエンジンで使用できるようにする必要があります。これを行うには、フォームをビューテンプレートのパラメーターとして含めます。 user.scala.htmlの場合、ページ上部のヘッダーは次のようになります。  

```
@(userForm: Form[UserData])(implicit messages: Messages)
```
user.scala.htmlにはフォームを渡す必要があるため、user.scala.htmlをレンダリングするときは、最初に空のuserFormを渡す必要があります。  

```
def index = Action { implicit request =>
  Ok(views.html.user(userForm))
}
```
最初に、フォームタグを作成できるようにします。これは、フォームタグを作成し、渡した逆ルートに従ってアクションとメソッドタグのパラメーターを設定する単純なビューヘルパーです。  
```
@helper.form(action = routes.Application.userPost()) {
  @helper.inputText(userForm("name"))
  @helper.inputText(userForm("age"))
}
```
いくつかの入力ヘルパーは、views.html.helperパッケージにあります。それらにフォームフィールドを入力すると、対応するHTML入力が表示され、値、制約が設定され、フォームバインディングが失敗したときにエラーが表示されます。  

Note:  
テンプレートで`import helper._`を使用して、ヘルパーの前に`@helper`を付けることを回避できます。


入力ヘルパーはいくつかありますが、最も役立つものは次のとおりです。  
- form: renders a form element.
- inputText: renders a text input element.
- inputPassword: renders a password input element.
- inputDate: renders a date input element.
- inputFile: renders a file input element.
- inputRadioGroup: renders a radio input element.
- select: renders a select element.
- textarea: renders a textarea element.
- checkbox: renders a checkbox element.
- input: renders a generic input element (which requires explicit arguments).


Note:  
これらの各テンプレートのソースコードは、views / helperパッケージでTwirlテンプレートとして定義されているため、パッケージバージョンは、生成されたScalaソースコードに対応します。  
参考までに、Githubの[views/helper](https://github.com/playframework/playframework/tree/master/core/play/src/main/scala/views/helper) packageを参照すると便利です。

フォームヘルパーと同様に、生成されたHTMLに追加されるパラメーターの追加セットを指定できます。  
```
@helper.inputText(userForm("name"), Symbol("id") -> "name", Symbol("size") -> 30)
```
上記の汎用入力ヘルパーを使用すると、目的のHTML結果をコーディングできます。

```
@helper.input(userForm("name")) { (id, name, value, args) =>
    <input type="text" name="@name" id="@id" @toHtmlArgs(args)>
}
```

Note:   
`_`で始まっていない限り、追加のパラメーターはすべて、生成されたHTMLに追加されます。 `_`で始まる引数は、フィールドコンストラクター引数用に予約されています。  

複雑なフォーム要素の場合、独自のカスタムビューヘルパー（viewsパッケージのscalaクラスを使用）とカスタムフィールドコンストラクターを作成することもできます。  


### Passing MessagesProvider to Form Helpers
上記のフォームヘルパー（入力、チェックボックスなど）はすべて、暗黙のパラメーターとして[MessagesProvider](https://www.playframework.com/documentation/2.8.x/api/scala/play/api/i18n/MessagesProvider.html)を取ります。これは、リクエストで定義された言語にマッピングされたエラーメッセージを提供する必要があるためです。  

メッセージの詳細については、[メッセージによる国際化のページ](https://www.playframework.com/documentation/2.8.x/ScalaI18N)をご覧ください。  

必要なMessagesProviderオブジェクトを渡すには2つの方法があります。  

#### Option One: Implicitly Convert Request to Messages
最初の方法は、コントローラーに[play.api.i18n.I18nSupport](https://www.playframework.com/documentation/2.8.x/api/scala/play/api/i18n/I18nSupport.html)を拡張させることです。これは、Injectされた[MessagesApi](https://www.playframework.com/documentation/2.8.x/api/scala/play/api/i18n/MessagesApi.html)を利用し、暗黙的な要求を暗黙的なメッセージに暗黙的に変換します。  
```
class MessagesController @Inject() (cc: ControllerComponents)
    extends AbstractController(cc)
    with play.api.i18n.I18nSupport {
  import play.api.data.Form
  import play.api.data.Forms._

  val userForm = Form(
    mapping(
      "name" -> text,
      "age"  -> number
    )(views.html.UserData.apply)(views.html.UserData.unapply)
  )

  def index = Action { implicit request =>
    Ok(views.html.user(userForm))
  }
}
```

つまり、次のフォームテンプレートが解決されます。
```
@(userForm: Form[UserData])(implicit request: RequestHeader, messagesProvider: MessagesProvider)

@import helper._

@helper.form(action = routes.FormController.post()) {
@CSRF.formField                     @* <- takes a RequestHeader    *@
@helper.inputText(userForm("name")) @* <- takes a MessagesProvider *@
@helper.inputText(userForm("age"))  @* <- takes a MessagesProvider *@
}
```

#### Option Two: Use MessagesRequest
2番目の方法は、MessagesRequestを提供する[MessagesActionBuilder](https://www.playframework.com/documentation/2.8.x/api/scala/play/api/mvc/MessagesActionBuilder.html)を依存性注入することです。
```
// Example form injecting a messagesAction
class FormController @Inject() (messagesAction: MessagesActionBuilder, components: ControllerComponents)
    extends AbstractController(components) {
  import play.api.data.Form
  import play.api.data.Forms._

  val userForm = Form(
    mapping(
      "name" -> text,
      "age"  -> number
    )(views.html.UserData.apply)(views.html.UserData.unapply)
  )

  def index = messagesAction { implicit request: MessagesRequest[AnyContent] =>
    Ok(views.html.messages(userForm))
  }

  def post() = TODO
}
```

フォームでCSRFを使用するには、リクエスト（技術的にはRequestHeader）とメッセージオブジェクトの両方がテンプレートで使用できる必要があるため、これは便利です。  
[MessagesProvider](https://www.playframework.com/documentation/2.8.x/api/scala/play/api/i18n/MessagesProvider.html)を拡張するWrappedRequestであるMessagesRequestを使用することで、テンプレートで使用できるようにする必要がある暗黙のパラメーターは1つだけです。

通常、リクエストの本文は必要ないため、`MessagesRequest [_]`と入力する代わりに、[MessagesRequestHeader](https://www.playframework.com/documentation/2.8.x/api/scala/play/api/mvc/MessagesRequestHeader.html)を渡すことができます。  
```
@(userForm: Form[UserData])(implicit request: MessagesRequestHeader)

@import helper._

@helper.form(action = routes.FormController.post()) {
  @CSRF.formField                     @* <- takes a RequestHeader    *@
  @helper.inputText(userForm("name")) @* <- takes a MessagesProvider *@
  @helper.inputText(userForm("age"))  @* <- takes a MessagesProvider *@
}
```
コントローラーにMessagesActionBuilderを注入するのではなく、MessagesAbstractControllerを拡張してフォーム処理をコントローラーに組み込むことにより、MessagesActionBuilderをデフォルトのアクションにすることもできます。
```
// Form with Action extending MessagesAbstractController
class MessagesFormController @Inject() (components: MessagesControllerComponents)
    extends MessagesAbstractController(components) {
  import play.api.data.Form
  import play.api.data.Forms._

  val userForm = Form(
    mapping(
      "name" -> text,
      "age"  -> number
    )(views.html.UserData.apply)(views.html.UserData.unapply)
  )

  def index = Action { implicit request: MessagesRequest[AnyContent] =>
    Ok(views.html.messages(userForm))
  }

  def post() = TODO
}
```


### Displaying errors in a view template
フォームのエラーは、Map [String、FormError]の形式をとります。  

- key: should be the same as the field.
- message: a message or a message key.
- args: a list of arguments to the message.

フォームエラーには、バインドされたフォームインスタンスで次のようにアクセスします。  

- errors: returns all errors as Seq[FormError].
- globalErrors: returns errors without a key as Seq[FormError].
- error("name"): returns the first error bound to key as Option[FormError].
- errors("name"): returns all errors bound to key as Seq[FormError].

フィールドに添付されたエラーはフォームヘルパーを使用して自動的にレンダリングされるため、エラーのある@ helper.inputTextは次のように表示されます。  

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
フィールドに関連付けられていないエラーは、暗黙のplay.api.i18n.Messagesインスタンスを取得するerror.formatを使用して文字列に変換できます。  

キーにバインドされていないグローバルエラーにはヘルパーがなく、ページで明示的に定義する必要があります。  

```
@if(userForm.hasGlobalErrors) {
  <ul>
  @for(error <- userForm.globalErrors) {
    <li>@error.format</li>
  }
  </ul>
}
```

### Mapping with tuples

### Mapping with single

### Fill values

### Nested values

### Repeated values

### Optional values

### Default values

### Ignored values

### Custom binders for form mappings

# Putting it all together





