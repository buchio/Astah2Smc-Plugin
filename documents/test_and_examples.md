# テスト

## コマンドラインツール

### コンパイル時にバッチ実行を行い、サンプルastahファイルからsmファイルを生成する

CLIディレクトリにて

    $ mvn integration-test

を実行すると、jarファイルを作成し、
作成したjarを用いて examples/smcExamples.asta を変換し、
examples/output 以下にsmファイルを生成します。

### 生成したsmファイルを用いてsmc付属のサンプルコードをコンパイル/テストする

examplesディレクトリにて

    $ make

を実行すると、生成したsmファイルを用いてテストを実施します。





<a rel="license" href="http://creativecommons.org/licenses/by/3.0/"><img alt="Creative Commons License" style="border-width:0" src="http://i.creativecommons.org/l/by/3.0/80x15.png" /></a><br />This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by/3.0/">Creative Commons Attribution 3.0 Unported License</a>.
