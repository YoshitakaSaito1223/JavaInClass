package Main;

import java.util.Scanner;

import work.Registration;
import work.Search;

public class Exmain {
	public static void main(String[] args) {

		//explain
		System.out.println("---受験結果管理プログラム---");
		System.out.println("\s使用したい機能の番号を以下から選んでください。\n"
				+ "1.受験登録機能 : 氏名を入力すると受験番号を発行します。\n"
				+ "2.テスト結果登録機能 ： 名前、もしくは受験番号を入力すると試験結果を出力します。\n"
				+ "3.受験結果確認機能 : 名前、もしくは受験番号を入力すると、合否が出力されます。\n");

		//入力受付
		Scanner scanner = new Scanner(System.in);

		//DB接続チェック用
		//AccessDB accessdb = new AccessDB();

		//accessdb.connectDB();

		Registration rgstr = new Registration();
		Search search = new Search();
		try {
			int pgNum = scanner.nextInt();

			//機能判定
			switch (pgNum) {
			case 1:
				rgstr.inputName();
				break;
			case 2:
				System.out.println("未実装です。");
				break;
			case 3:
				search.searchResult();
				break;
			default:
				System.out.println("不正な入力です。機能に対応する番号を入力してください。");
				break;
			}

		} catch (Exception e) {
			System.out.println("不正な入力です。対応する数字を入力してください。");
		}

		//Scanner close
		scanner.close();

	}
}
//EX1  : 試験結果管理プログラムを拡張して、以下のようなシステムを構築してみてください。
//: 1.受験登録機能...名前を登録すると受験番号が発行される。
//: 2.テスト結果登録機能...名前か受験番号を入力して、該当者のレコードに試験結果を登録
//: 3.受験結果確認機能...名前を入力すると合格不合格が表示される。
//: 4.ログイン機能...2のテスト結果を登録できるのは権限を持っている人のみに限定。1,3は誰でも閲覧できるようにお願いします。