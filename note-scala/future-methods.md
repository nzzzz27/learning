# Future - メゾット
## コールバック
Future[T]に包まれた値を使用するには、Fureture[T]に用意されているメゾット郡を使ってコールバックで処理する。  
T型の値が立用可能にあった時点で、そのコールバックは発火される。  


## メゾット
### 初期化
```
val future = Future(1)
future: scala.concurrent.Future[Int] = Future(Success(1))

val future = Future("hoge")
future: scala.concurrent.Future[String] = Future(Success(hoge))
```

### `.successful()`/`.faild()`
実行済みのFutureを生成することができる。  
successfulはすでにある値をFutureが成功した場合の値として包み、faieldはすでにあるThrowableを継承した値をFutureが失敗した場合の値として包んで返す。  

failedの引数には、[Throwable](https://docs.oracle.com/javase/jp/8/docs/api/java/lang/Throwable.html)を継承したもののみが渡せる。  

これらの関数はFuture型で包むだけなので、何かの処理を非同期で行うわけではない。

```
val success = Future.successful(2)
val failed  = Future.failed(throw new java.util.NosuchElementExecption)
```

### `.isCompleted`
非同期処理が完了したかどうかを、Booleanで返す。  
```
  val msg = "hello"
  val f: Future[String] = Future {
    Thread.sleep(1000)
    msg * 5
  }

  f.isCompleted // false
```


### `Await`
値を取得するには、処理の終了を待つ必要がある。Futureの処理が終了するまで待つには、`Await`を使う。  


#### `Await.ready()`
Future が完了するまで待機することができる。   
このメソッドを呼んだ時、Future が失敗したとしても例外は投げられない。 
第二引数で、処理の待ち時間の制限指定ができる。下記の場合は、[Duration](https://www.scala-lang.org/api/2.12.3/scala/concurrent/duration/Duration$.html#Inf:scala.concurrent.duration.Duration.Infinite).Inf = duration infinite。  
```
val future = Future {
  Thread.sleep(1000)
  2 * 2
}

//Await.readyの第二引数は待ち時間を表す。Infはinfinityのことで、永遠に待つ。  
val result = Await.ready(future, Duration.Inf)

result //Future(Success(4))
```

### `.onComplete`
戻り値：Unit型  
Futureが完了した時に、成否にかかわらず呼ばれる。  

定義
```
def onComplete[U](f: Try[T] => U)(implicit executor: ExecutionContext): Unit
```


### `[.andThen]`(https://github.com/scala/scala/blob/a6e1a6f476f26f66ae1c3848033e95f6b173ad56/src/library/scala/concurrent/Future.scala#L497)
戻り値：Future型

定義
```
def andThen[U](pf: PartialFunction[Try[T], U])(implicit executor: ExecutionContext): Future[T]
```


### Futureの処理を連結させたい時
Futureの式を合成し、１つのFutureの式にすることができ、DBからのデータ取得をシンプルに書くことができる。  
ただ、Future式の合成のために、それぞれの式の実行完了を待っている状態になり並列性が失われるので適度にFutureの式を分割する必要がある。 


メゾットの例文に使う関数。  
いずれの例でも、１つ１つのFutureの処理を待つので、合計3秒かかる。
```
def future1(): Future[Int] =
  Future{
    Thread.sleep(1000)
    2 * 2
  }

def future2(i: Int): Future[Int] =
  Future {
    Thread.sleep(1000)
    i * 2
  }

def future3(i: Int): Future[Unit] =
  Future {
    Thread.sleep(1000)
    println(i * 2)
  }
```

#### `.map()`
```
//Future[Future[Future[Unit]]]型
future1 map { n =>
  future2(n) map { m =>
    future3(m)
  }
}
```

#### `.flatMap`
```
//Future[Unit]型
future1 flatMap { n =>
  future2(n) flatMap { m =>
    future3(m)
  }
}
```

#### `.flatMap + .map`
```
//Future[Future[Unit]]
future1 flatMap { n =>
  future2(n) map { m =>
    future3(m)
  }
}
```


#### `for {} yield {}`
```
//Future[Unit]
for {
  n <- future1()
  m <- future2(n)
  _ <- future3(m)
} yield {
  Unit
}
```

## 例外処理
### `.recover`
元のFutureが成功した時：  
同一の結果をもつ新たなFuture型の値を返す  

元のFutureが失敗した時：  
失敗したFutureを成功したFutureに変換する。  
失敗したFutureが持つThrowableに対してrecoverの 例外処理の引数として渡された部分関数を適用した値を「成功時に値として」返している。  

```
val future = Future {
  println("Futureの処理開始")
  Thread.sleep(3000)
  1 / 0
}

future recover {
  case e:Exception => println("エラー発生")
}
```



### `.recoverWith`

元のFutureが成功した時：  
同一の結果を持つ新たなFuture型の値を返す  

元のFutureが失敗した時：  
失敗したFutureを成功したFutureに変換する。  
失敗したFutureが持つ失敗値Throwableに対し、recoverWithの引数として渡された部分関数を適用した値を元に「精巧ないしは失敗したFuture型の値」を返す。  

```
val future = Future {
  println("Futureの処理開始")
  Thread.sleep(3000)
  1 / 0
}

future recoverWith {
  case e: Exception => Future.successful(2)
}
```


## 参考サイト
[FutureとPromise](https://docs.scala-lang.org/ja/overviews/core/futures.html)  
[Scala ExecutionContextって何 / Futureはスレッド立ち上げじゃないよ](https://mashi.hatenablog.com/entry/2014/11/24/010417)  


