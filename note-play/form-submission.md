# Handling form submission
フォームの処理と送信は、あらゆるWebアプリケーションの重要な部分です。 Playには、単純なフォームを簡単に処理したり、複雑なフォームを処理したりできる機能が付属しています。  
Playのフォーム処理アプローチは、データのバインドの概念に基づいています。 POSTリクエストからデータが受信されると、Playはフォーマットされた値を探し、それらをFormオブジェクトにバインドします。  

通常、フォームはBaseControllerインスタンスから直接使用されます。
ただし、フォーム定義はケースクラスまたはモデルと正確に一致する必要はありません。これらは純粋に入力を処理するためのものであり、個別のPOSTに対して個別のフォームを使用することは妥当です。  


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

### 1.Defining a form
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

Formsオブジェクトは`[mapping method](https://www.playframework.com/documentation/2.8.x/api/scala/play/api/data/Forms$.html#mapping%5BR%2CA1%5D((String%2CMapping%5BA1%5D))((A1)%E2%87%92R)((R)%E2%87%92Option%5BA1%5D)%3AMapping%5BR%5D)`を定義します。  

このメソッドは、フォームの名前と制約を受け取り、`apply function`と`unapply function`も受け取ります。  
UserDataはケースクラスであるため、applyメソッドとunapplyメソッドをマッピングメソッドに直接プラグインできます。  

フォームは、マップが与えられると、バインドされた値を持つUserDataインスタンスを作成します。

```
val anyData  = Map("name" -> "bob", "age" -> "21")
val userData = userForm.bind(anyData).get
```

ただし、ほとんどの場合、リクエストから提供されたデータを使用して、アクション内からフォームを使用します。  
フォームにはbindFromRequestが含まれており、暗黙のパラメーターとして要求を受け取ります。暗黙のリクエストを定義すると、bindFromRequestがそれを見つけます。  

```
val userData = userForm.bindFromRequest.get
```

フォームマッピングでのケースクラスの使用に限定されません。applyメソッドとunapplyメソッドが適切にマッピングされている限り、Forms.tupleマッピングを使用するタプルやモデルケースクラスなど、好きなものを渡すことができます。   
ただし、フォーム専用のケースクラスを定義することには、いくつかの利点があります。  

- フォーム固有のケースクラスが便利です
  - Caseクラスは、データの単純なコンテナになるように設計されており、フォームの機能と自然に一致するすぐに使える機能を提供します。
- フォーム固有のケースクラスは強力です
  - タプルは便利に使用できますが、カスタムの適用または適用解除メソッドは許可されず、含まれているデータをアリティ（_1、_2など）でのみ参照できます。
- フォーム固有のケースクラスは、特にフォームを対象としています
  - モデルケースクラスの再利用は便利ですが、多くの場合、モデルには追加のドメインロジックが含まれ、緊密な結合につながる永続性の詳細さえ含まれます。  
  - さらに、フォームとモデルの間に直接1：1マッピングがない場合、パラメータ改ざん攻撃を防ぐために、機密フィールドを明示的に無視する必要があります。





# リンク
- [Handling form submission](https://www.playframework.com/documentation/2.8.x/ScalaForms)
