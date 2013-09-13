# プラグイン

## 前準備

astah pluginチュートリアルに従って設定を実施してください。

## コマンドライン

    $ astah-build

## Eclipse

pluginは規模が小さいので、自動ビルドを用いることを推奨します。
  

# CLI(Command Line Interface)

## 前準備

コンパイル及びテストを実施するために、ローカルレポジトリにastah関連のjarファイルを登録する必要があります。

### OSX(Astah Community)の場合
    $ mvn install:install-file -Dfile=/Applications/astah\ community/astah\ community.app/Contents/Java/astah-api.jar -DgroupId=astah_api -DartifactId=astah_api -Dversion=6.7 -Dpackaging=jar -DgeneratePom=true
    $ mvn install:install-file -Dfile=/Applications/astah\ community/astah\ community.app/Contents/Java/astah-community.jar -DgroupId=astah_product -DartifactId=astah_product -Dversion=6.7 -Dpackaging=jar -DgeneratePom=true

### Windows(Astah UML)の場合
    $ mvn install:install-file -Dfile='C:\Program Files\astah-UML\astah-api.jar' -DgroupId=astah_api -DartifactId=astah_api -Dversion=6.7 -Dpackaging=jar -DgeneratePom=true
    $ mvn install:install-file -Dfile='C:\Program Files\astah-UML\astah-uml.jar' -DgroupId=astah_product -DartifactId=astah_product -Dversion=6.7 -Dpackaging=jar -DgeneratePom=true

## コンパイル

CLIディレクトリにて

    $ mvn compile

でコンパイルできます。

<a rel="license" href="http://creativecommons.org/licenses/by/3.0/"><img alt="Creative Commons License" style="border-width:0" src="http://i.creativecommons.org/l/by/3.0/80x15.png" /></a><br />This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by/3.0/">Creative Commons Attribution 3.0 Unported License</a>.
