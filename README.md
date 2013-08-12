# Astah2Smc

AstahのモデルからSMC(State Machine Compiler)ファイルを生成する。

## 使い方

### CLI

### Plugin


## 記述方法

### ステートチャートの配置

*   親となるパッケージ、サブシステムもしくはクラス毎に.smファイルを作成する。
*   同じクラス内に複数のステートチャートが存在することが可能だが、同じ.smファイルに配置されるステートチャートは、開始疑似状態を一つだけ持つものとする。

### ステートチャートの記述

ほぼ、通常の状態遷移機械の記述を行えばよい。記述に注意が必要な部分についてのみ述べる。

#### 複数行に渡るActionの記述
#### Push
#### Pop
#### Jump

#### 未対応項目
*   サブ状態
*   浅い履歴状態
*   ジャンクション
*   選択


## コンパイル

コンパイル及びテストを実施するために、ローカルレポジトリにastah関連のjarファイルを登録する必要がある。

### OSX(Astah Community)の場合
    $ mvn install:install-file -Dfile=/Applications/astah\ community/astah\ community.app/Contents/Java/astah-api.jar -DgroupId=astah_api -DartifactId=astah_api -Dversion=6.7 -Dpackaging=jar -DgeneratePom=true
    $ mvn install:install-file -Dfile=/Applications/astah\ community/astah\ community.app/Contents/Java/astah-community.jar -DgroupId=astah_product -DartifactId=astah_product -Dversion=6.7 -Dpackaging=jar -DgeneratePom=true

### Windows(Astah UML)の場合
    $ mvn install:install-file -Dfile='C:\Program Files\astah-UML\astah-api.jar' -DgroupId=astah_api -DartifactId=astah_api -Dversion=6.7 -Dpackaging=jar -DgeneratePom=true
    $ mvn install:install-file -Dfile='C:\Program Files\astah-UML\astah-uml.jar' -DgroupId=astah_product -DartifactId=astah_product -Dversion=6.7 -Dpackaging=jar -DgeneratePom=true
