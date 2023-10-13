# 【Level3-4】

## テーマ
既存プログラムを参考に、CRUD機能が作成できること
ページ遷移とバリデーションの仕組みが理解できること
templateへの記載ができるようになること

## 演習課題の準備（開発環境について）
* この演習では、Codespacesと呼ばれるサービスを使って開発を行います。
* ブラウザ上で動作する開発環境です、インストール不要で使う事ができます。
* レベル0で実施した[手順](/Codespacesの実行手順.md)を参照して、演習課題の準備をしましょう。

## 演習内容
Lesson(授業管理機能)を参考にArchievement(実績管理機能)の実績変更画面を作成しましょう。

## 演習内容詳細
実績登録画面を作成しましょう。
実績管理画面の詳細ボタンを押下して遷移します。
![image](https://user-images.githubusercontent.com/32722128/191185003-a0e1c820-6d86-457a-9243-74c04a28f5da.png)
![image](https://user-images.githubusercontent.com/32722128/191185120-3884cc0d-79fb-4084-b0e3-947508e7734e.png)
以下の順序で実装を行ってください。

1. リクエスト情報を保持する、Formクラスである`ArchievementForm`クラスに`id`項目を追加してください。
2. 変更画面で表示する、登録済みの実績情報取得するため、`ArchievementDao`クラスに`findById`メソッドを追加してください。
3. 変更画面で入力された内容で、実績を更新するため`ArchievementDao`クラスに`updateArchievement`メソッドを追加してください。
4. 今回追加したDAOクラスのメソッドを使用するため`ArchievementService`クラスに`findArchievement`メソッドを追加してください。
5. 今回追加したDAOクラスのメソッドを使用するため`ArchievementService`クラスに`updateArchievement`メソッドを追加してください。
6. `ArchievementController`クラスに、変更画面を表示するための、`detail`メソッドを追加してください。
7. `ArchievementController`クラスに、変更処理を行うための、`update`メソッドを追加してください。


それぞれに対応する、授業管理機能(Lesson)のファイルを参考に作成してください。

|Archievement|Lesson|
|---|---|
|ArchievementController|LessonController|
|ArchievementService|LessonService|
|ArchievementForm|LessonForm|
|Archievement|Lesson|
|ArchievementDao|LessonDao|

## 1.`ArchievementForm`クラスへの`id`項目の追加
実績の変更画面を作るに当たって、どの実績に対する更新処理を行うかを特定する必要があります。<br>
<br>
更新時に、実績毎に割り振られている`id`もリクエストデータ含めるようにし、どの実績に対する更新であるかを特定出来るようにします。<br>
ビューのテンプレートファイルとして`~\templates\archievement\detail.html`がすでに用意されています。<br>
このテンプレートファイルでは、`id`と`name`と`memo`という名前のフィールド変数が存在する、クラスのオブジェクトをやり取りして、リクエスト送信する、画面表示を行うようになってますので、`id`と`name`と`memo`という名前のフィールド変数持つクラスが必要です。<br>
前回の演習で作成した、`ArchievementForm`クラスへ`id`を追加して対応する事としてください。<br>
<br>
なお、`id`はユーザ入力値とはなりませんので、入力チェックの必要有りません。<br>

## 2.`ArchievementDao`クラスに`findById`メソッドを追加
`ArchievementDao`クラスに`findById`メソッドを追加してください。<br>
戻り値のデータ型は`Archievement`クラス、引数は`id`を`int`型で受け付けるようにしてください。<br>
インターフェイス、実装クラスどちらにも追加してください。<br>
<br>
変数`sql`に、`ARCHIEVEMENT`テーブルから、`id`を指定して、データを抽出するSELECT文を設定してください。<br>
この時条件となる`id`の値を指定する個所には、「?」を指定してください。<br>
```
String sql = "SELECT id, name, memo FROM ARCHIEVEMENT WHERE id = ?";
```
主キーである、`id`を指定して抽出してますので、必ず1件のみ取得されるはずです。<br>
このような場合は、`JdbcTemplate`クラスの`queryForMap`メソッドを使用してSQL文を実行し結果を取得します。<br>
<br>
第一引数には、SQL文を、第二引数以降は、バインドパラメータに指定したい値を設定します。<br>
List型ではない、単純にMap型のオブジェクトで得られますので、`Archievement`クラスのオブジェクトに詰め替え、戻り値とします。<br>

```
Map<String, Object> result = jdbcTemplate.queryForMap(sql, id);
Archievement archievement = new Archievement();
archievement.setId((int) result.get("id"));
archievement.setName((String) result.get("name"));
archievement.setMemo((String) result.get("memo"));
return archievement;
```

## 3.`ArchievementDao`クラスに`updateArchievement`メソッドを追加
`ArchievementDao`クラスに`updateArchievement`メソッドを追加してください。<br>
戻り値のデータ型は`int`、引数は`Archievement`クラスのオブジェクトを受け付けるようにしてください。<br>
インターフェイス、実装クラスどちらにも追加してください。<br>
<br>
変数`sql`に、`ARCHIEVEMENT`テーブルに対して更新を行う、UPDATE文を設定してください。<br>
値を指定する個所には、「?」を指定してください。<br>
```
String sql = "UPDATE ARCHIEVEMENT SET name = ?, memo = ? WHERE id = ?";
```
`JdbcTemplate`クラスの`update`メソッドを使用してSQL文を実行します。<br>
第一引数には、SQL文を、第二引数以降は、バインドパラメータに指定したい値を設定します。<br>
戻り値として、実行されたSQL文が影響を与えた行数が返却されます。<br>
そのまま、メソッドの戻り値として返して下さい。<br>
今回は`id`を指定して、1件UPDATEを行いますので、必ず1が返されるはずです。<br>

## 4.`ArchievementService`クラスに`findArchievement`メソッドを追加
`ArchievementService`クラスに`findArchievement`メソッドを追加してください。<br>
戻り値のデータ型は`Archievement`クラス、引数は`id`を`int`型で受け付けるようにしてください。<br>
インターフェイス、実装クラスどちらにも追加してください。<br>
<br>
先ほど実装した、`ArchievementDao`クラスの`findById`メソッドを呼び出し、`Archievement`型の変数を取得し、取得した変数を返却してください。<br>

## 5.`ArchievementService`クラスに`updateArchievement`メソッドを追加
`ArchievementService`クラスに`updateArchievement`メソッドを追加してください。<br>
戻り値のデータ型は`int`、引数は`Archievement`クラスのオブジェクトで受け付けるようにしてください。<br>
インターフェイス、実装クラスどちらにも追加してください。<br>
<br>
先ほど実装した、`ArchievementDao`クラスの`updateArchievement`メソッドを呼び出し、実行されたSQL文が影響を与えた行数を取得してください。<br>
0が返された場合は、INSERTの処理が正常に完了してませんので、`NotUpdateException`クラスの例外を投げるようにしてください。<br>

## 6. `ArchievementController`クラスに、変更画面を表示するための、`detail`メソッドを追加
実績管理が面面の詳細ボタンのリンクには`id`が含まれています。<br>
例えば画像の「id=1」のリンクを押下すると`/archievement/detail/1`というパスへ遷移します。<br>
<br>
![image](https://user-images.githubusercontent.com/32722128/191185003-a0e1c820-6d86-457a-9243-74c04a28f5da.png)<br>
<br>
このようにパスの最後にどのIDの内容を変更するかを指定し、画面遷移をしています。<br>
このように、パスに引数を持たせた物を、パスパラメータと呼びます。<br>
<br>
詳細ボタン押下時のリクエストを処理するために、`detail`メソッドに次のようなアノテーションと引数を指定します。<br>
```
@GetMapping("/detail/{id}")
  public String detail(@PathVariable("id") int id, Model model) {
```
`{id}`で「id」という属性名で、パスパラメータがある事を示し、`@PathVariable("id")`のアノテーションを引数の前につける事で、「id」という属性名のパラメータ値を`int id`に代入するといった事を行ってくれています。<br>
取得した`id`で`ArchievementService`クラスの`findArchievement`メソッドを利用し、`Archievement`クラスのオブジェクトを取得し、`ArchievementForm`クラスのオブジェクトへ入れ替え、`model`オブジェクトの`archievementForm`属性に値を設定し、変更画面のビューのテンプレートファイルである、`archievement/update`を設定してください。

## 7. `ArchievementController`クラスに、変更処理を行うための、`update`メソッドを追加
`ArchievementController`クラスに、登録のためのリクエストを受け付ける`update`メソッドを追加してください。<br>
<br>
ビューのテンプレートファイル、`archievement/update`にて、更新ボタンを押した際には、パス`/archievement/update`に対して、POSTリクエストを送信するように指定されています。<br>
このリクエストを受け取る事が出来るように、`@PostMapping("/update")`とアノテーションを付与してください。<br>
<br>
引数部分は以下のように設定してください、それぞれの引数と付与されているアノテーションの意味について解説します。<br>
```
@PostMapping("/update")
  public String update(
      @Validated @ModelAttribute("archievementForm") ArchievementForm archievementForm,
      BindingResult result, Model model) {
```
* @Validated @ModelAttribute("archievementForm") ArchievementForm archievementForm
  * @Validated ・・・ バリデーション(入力チェックを有効にするアノテーションです。)
  * @ModelAttribute("archievementForm") ・・・ 属性名の指定が`archievementForm`であるリクエストデータを引数に格納する事を示しています。
* BindingResult result ・・・ 入力チェックの結果などが格納されます。
* Model model ・・・ ビューに値を渡すための引数です。

入力チェック結果にエラーが存在しないか確認し、存在すれば、再度画面表示を行うように指定します。<br>
この時入力チェックの結果も返されます、入力チェックの結果をテンプレートエンジンが確認し、赤字でエラーメッセージを表示するようHTMLの生成を行っています。<br>
```
if (result.hasErrors()) {
  model.addAttribute("archievementForm", archievementForm);
  return "archievement/update";
}
```
<br>

その後、Formクラスのオブジェクトから、Entityクラスのオブジェクトに入力データを移し替え、`ArchievementService`クラスの`updateArchievement`メソッドを呼び出し、更新処理を実施します。

```
Archievement archievement = new Archievement();
archievement.setId(archievementForm.getId());
archievement.setName(archievementForm.getName());
archievement.setMemo(archievementForm.getMemo());

archievementService.updateArchievement(archievement);
```
<br>

その後、`/archievement`のパスに対してリダイレクトを行うよう、戻り値に指定します。<br>
リダイレクトする事によって、同クラスの`index`メソッドが実行され、実績一覧の画面が表示されます。<br>

## 課題の提出
* 課題の提出は[課題提出の操作](/課題の提出手順.md)のコミット・プッシュ・プルリクエストを実施しましょう。

