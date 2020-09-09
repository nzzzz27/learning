# [Metals](https://scalameta.org/metals/docs/editors/vim.html) 
## coc-metalsで入れる方法

## curlコマンドで入れる方法
```
cd ~/
```
### scala version: 2.12.8の場合
```
// 0.5.2のmeatlsをinstall
$curl -L -o coursier https://git.io/coursier-cli
$chmod +x coursier
$./coursier bootstrap \
  --java-opt -Xss4m \
  --java-opt -Xms100m \
  --java-opt -Dmetals.client=vim-lsc \
  org.scalameta:metals_2.12:0.7.6 \
  -r bintray:scalacenter/releases \
  -r sonatype:snapshots \
  -o /usr/local/bin/metals-vim -f
```
### scala version: 2.12.6 ~ 7の場合
```
// 0.5.2のmeatlsをinstall
$curl -L -o coursier https://git.io/coursier-cli
$chmod +x coursier
$./coursier bootstrap \
  --java-opt -Xss4m \
  --java-opt -Xms100m \
  --java-opt -Dmetals.client=vim-lsc \
  org.scalameta:metals_2.12:0.5.2 \
  -r bintray:scalacenter/releases \
  -r sonatype:snapshots \
  -o /usr/local/bin/metals-vim -f
  ```
  ### scala version: 2.12.4 ~ 6の場合
  ```
  // 0.5.0のmeatlsをinstall
$curl -L -o coursier https://git.io/coursier-cli
$chmod +x coursier
$./coursier bootstrap \
  --java-opt -Xss4m \
  --java-opt -Xms100m \
  --java-opt -Dmetals.client=vim-lsc \
  org.scalameta:metals_2.12:0.5.0 \
  -r bintray:scalacenter/releases \
  -r sonatype:snapshots \
  -o /usr/local/bin/metals-vim -f
  ```
  
## エラー
ポップアップ画面から、builf fileをimportしたあと、failになり、コンソールに以下のようなエラーが表示される。 
```
Resolution error: Conflicting dependencies:
com.google.guava:guava:27.0.1-jre or [14.0,22) wanted by

  org.eclipse.xtext:org.eclipse.xtext.xbase.lib:2.16.0 wants [14.0,22)
  ├─ org.eclipse.xtend:org.eclipse.xtend.lib:2.16.0
  │  └─ org.eclipse.lsp4j:org.eclipse.lsp4j.generator:0.7.1
  │     ├─ ch.epfl.scala:bsp4j:2.0.0-M3 wants org.eclipse.lsp4j:org.eclipse.lsp4j.generator:0.5.0
  │     │  └─ org.scalameta:metals_2.12:0.5.2
  │     └─ org.eclipse.lsp4j:org.eclipse.lsp4j:0.7.1
  │        ├─ org.scalameta:metals_2.12:0.5.2 wants org.eclipse.lsp4j:org.eclipse.lsp4j:0.5.0
  │        └─ org.scalameta:mtags-interfaces:0.5.2
  │           └─ org.scalameta:mtags_2.12.8:0.5.2
  │              └─ org.scalameta:metals_2.12:0.5.2
  └─ org.eclipse.xtend:org.eclipse.xtend.lib.macro:2.16.0
     └─ org.eclipse.xtend:org.eclipse.xtend.lib:2.16.0
        └─ org.eclipse.lsp4j:org.eclipse.lsp4j.generator:0.7.1
           ├─ ch.epfl.scala:bsp4j:2.0.0-M3 wants org.eclipse.lsp4j:org.eclipse.lsp4j.generator:0.5.0
           │  └─ org.scalameta:metals_2.12:0.5.2
           └─ org.eclipse.lsp4j:org.eclipse.lsp4j:0.7.1
              ├─ org.scalameta:metals_2.12:0.5.2 wants org.eclipse.lsp4j:org.eclipse.lsp4j:0.5.0
              └─ org.scalameta:mtags-interfaces:0.5.2
                 └─ org.scalameta:mtags_2.12.8:0.5.2
                    └─ org.scalameta:metals_2.12:0.5.2

  org.scalameta:metals_2.12:0.5.2 wants 27.0.1-jre
  ```
### 解決方法
```
// バージョン毎のsbtファイルがある
cd ~/.sbt/

// 使用するsbtバージョンのディレクトリに移動
cd 1.0/plugins
```
中のファイルの、`sbt-metals`とsbt-bloop`のバージョンを書き換える
```
libraryDependencies := {
  import Defaults.sbtPluginExtra
  val oldDependencies = libraryDependencies.value
  if (System.getenv("METALS_ENABLED") == "true") {
    val bloopModule = "ch.epfl.scala" % "sbt-bloop" % "1.2.5"
    val metalsModule = "org.scalameta" % "sbt-metals" % "0.5.2"
    val sbtVersion = Keys.sbtBinaryVersion.in(TaskKey[Unit]("pluginCrossBuild")).value
    val scalaVersion = Keys.scalaBinaryVersion.in(update).value
    val bloopPlugin = sbtPluginExtra(bloopModule, sbtVersion, scalaVersion)
    val metalsPlugin = sbtPluginExtra(metalsModule, sbtVersion, scalaVersion)
    List(bloopPlugin, metalsPlugin) ++ oldDependencies.filterNot { dep =>
      (dep.organization == "ch.epfl.scala" && dep.name == "sbt-bloop") ||
      (dep.organization == "org.scalameta" && dep.name == "sbt-metals")
    }
  } else {
    oldDependencies
  }
}
```
scalaを使用するrootディレクトリに移動し、`sbt`コマンドで、Metalsの設定を再度インストール 
