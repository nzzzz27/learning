# [Throwable](https://docs.oracle.com/javase/jp/8/docs/api/java/lang/Throwable.html)
Throwableクラスは、Java言語の全てのエラーと例外のスーパークラス。  
catch節の引数の型に指定できる。

```
//クラスツリー
Throwable
├ Error：通常のアプリケーションであればキャッチすべきではない重大な問題
└ Exception：通常のアプリケーションでキャッチされる可能性のある状態
  └ RuntimeException：メExceptionの中でもthrows節による宣言が必要ないもの
```

例外がスローされる場合  
- Java仮想マシンが、null参照に対するメンバ呼び出しを行った時。  
→ `Object foo = null; foo.toString();`
- アプリケーションが、nullではいけない変数にnullが入っていることをthrow構文でスローする。  
→  `throw new NullPointerException();`
[Throwableについて本気出して考えてみた
**コンパイル時については**、Throwableクラス自身またはサブクラスのうち、**`RuntimeException`と`Error`のどちらのサブクラスでもないクラスが、例外**とみなされる。

Throwableクラスツリーのまとめ  
- Error
  - calleeのバグ発生を通知するもの
  - コンポーネントの仕様として、「自分がバグってたら投げるよ」の明示が必須
  - なぜなら、仕様外の動きをされた上に、Errorのthrowが明示されていなかったらcallerが究極に困るからｗ
- RuntimeException
  - callerのバグ発生を通知するもの
  - クラスの仕様として、「変な呼び方したら投げるよ」の明示が必須
  - callerとしてはcallerのバグだが、calleeとしては仕様の範囲内の動作である
  - なぜなら、Javadocに挙動が明示されているから
- Exception(非RTE)
  - 第三者の異常であり、当事者(caller, callee)には責任が無い
  - バグ発生を通知するものではない
  - caller, callee両者にとって、仕様の範囲内の動作である
  - なぜなら、Javadocに挙動が明示されていて、かつ、catchによる処理が保証されるから


# 参考
[Throwable](https://docs.oracle.com/javase/jp/8/docs/api/java/lang/Throwable.html)
[Throwableについて本気出して考えてみた](http://daisuke-m.hatenablog.com/entry/20081202/1228221927)
