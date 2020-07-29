# 提出前の確認事項
## scala 
- インデントは揃っているか
```
//変数定義

//型の位置

// =>の位置

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

# Pull Request
- ブランチとプルリクは基本的にチケットベースで作成しているか。
  - 修正箇所が多い場合は、PRを細かく分ける
- ブランチ名の命名規則は正しいか
```
//feture branch 
feture/YYYY-MM-チケット番号-ブランチ名
```
- コメントの書き方は正しいか
  - レビュアーが修正後の表示確認の手間を省くため、完成形のスクショを貼る
  - md記法で書く


