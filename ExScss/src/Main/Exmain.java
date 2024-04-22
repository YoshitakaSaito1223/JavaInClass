package Main;

import work.Registration;

public class Exmain {
	public static void main(String[] args) {
		
		System.out.println("---受験結果管理プログラム---");
		Registration rgstr=new Registration();
		
		rgstr.inputName();
		
	}
}
//EX1  : 試験結果管理プログラムを拡張して、以下のようなシステムを構築してみてください。
//: 1.受験登録機能...名前を登録すると受験番号が発行される。
//: 2.テスト結果登録機能...名前か受験番号を入力して、該当者のレコードに試験結果を登録
//: 3.受験結果確認機能...名前を入力すると合格不合格が表示される。
//: 4.ログイン機能...2のテスト結果を登録できるのは権限を持っている人のみに限定。1,3は誰でも閲覧できるようにお願いします。