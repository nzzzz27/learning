# 提出前の確認事項
## scala 
- インデントは揃っているか
  - 変数定義
  - 型の位置
  - =>の位置

- 後から見返してもわかりやすいコードか
```
//Not Good
numOpt match {
   case Some(n) if n >= 10 => println("A")
   case Some(n) if n <= 4  => println("C")
   case None               => println("D")
    case _                  => println("B")
}

//Good
numOpt match  {
  case Some(x) if(x >= 10)           => "a"
  case Some(y) if(y >=  5 && y <= 9) => "b"
  case Some(_)                       => "c"
  case None                          => "d"
}
```

## css
- インデントは、プロパティブロックごとに揃っているか
```
.global-nav {
  list-style:  none;
  text-align:  right;
  a {
    padding:  8px;
    color:    blue;
  }
}
```
- 汎用値は変数化されているか
  - font-size
  - margin / padding
  - color 
- 変数命名規則は、`$プロパティ名-対象の要素名-種類`になっているか  
```
//例1：フォントカラー（$色-フォント-ベース）
$color-font-base: #333; ... ok
$font-color-base; #333; ... ng 

//例2：マージン($スペース-サイズ）
$space-small: 8px;
```

## git
## ブランチ名
- 以下のフォーマットで、何の修正がされたかわかる名前をつける
```
//feture branch フォーマット
feture/YYYY-MM-チケット番号-ブランチ名

//実例
feature/2020-08-update-ixias-version
```

### Pull Request
- スペース削除や他者のコミットなど、そのPRと無関係な作業のコミットが混じっていないか
- ブランチとプルリクは基本的にチケットベースで作成しているか。
  - 修正箇所が多い場合は、PRを細かく分ける
- ブランチ名の命名規則は正しいか
- コメントの書き方は正しいか
  - レビュアーが修正後の表示確認の手間を省くため、完成形のスクショを貼る
  - md記法で書く

### developをmasterにPRするとき
- 含まれているPRの表題をコメントに記載
- 意図しないコミットが入っていないか
  - 他者の変更やコミット
  - スペース削除など、そのPRと無関係な作業のコミット
