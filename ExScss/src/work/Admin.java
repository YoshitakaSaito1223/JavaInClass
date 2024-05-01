package work;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import accessDB.AccessDB;

public class Admin {
	//インスタンス作成
	Scanner sc = new Scanner(System.in);
	Registration rgstr = new Registration();
	Search search = new Search();
	AccessDB db = new AccessDB();
	Login login = new Login();

	String admin_name = login.getName();

	public void SelectFunction() {

		String adloop = "";
		while (!(adloop.equals("exit"))) {
			System.out.println("\n-----管理者機能-----");
			System.out.println("利用したい機能の番号を入力してください。(終了する際には「exit」と入力)\n"
					+ "1.テスト結果登録機能 ： 名前、もしくは受験番号を入力すると試験結果を登録します。\n"
					+ "2.受験結果確認機能 : 名前(必要に応じて受験番号)を入力すると、合否が出力されます。\n"
					+ "3.登録情報変更機能 : 受験結果の変更や、登録情報の削除をすることができます。\n");
			System.out.print("機能番号：");
			adloop = sc.nextLine();
			switch (adloop) {
			case "1":
				RegisterResult();
				break;
			case "2":
				search.searchResult();
				break;
			case "3":
				ChooseUpdateOrDelete();
			case "exit":
				System.out.println("\n終了します。");
				System.exit(0);
			default:
				break;

			}
		}
	}

	private void RegisterResult() {
		String examinee_name, sub_name, tmp;
		int examinee_id = 0, sub_id = 0, result_point = 0;

		//登録する名前
		System.out.print("\n登録したい人の名前もしくはIDを入力してください:");
		tmp = sc.nextLine();

		//名前かIDか判定
		try {
			examinee_id = Integer.parseInt(tmp);
			examinee_name = db.getNameByID(examinee_id);
		} catch (Exception e) {
			examinee_name = tmp;
			ArrayList<Integer> ids = new ArrayList();
			ids = db.getIdDataByName(tmp);

			//名前が重複していた場合の処理
			if (ids.size() > 1) {
				System.out.print(tmp + "さんのIDを入力してください:");
				int checkId = Integer.parseInt(sc.nextLine());
				if (ids.contains(checkId)) {
					examinee_id = checkId;
				} else {
					System.out.println("IDが間違っています。");
					System.exit(0);
				}
			} else {
				examinee_id = ids.get(0);
			}
		}

		System.out.print("\n登録したい教科を入力してください：");
		tmp = sc.nextLine();
		//教科名か教科IDか判定
		try {
			sub_id = Integer.parseInt(tmp);
			sub_name = db.getSubjectNameBySub_Id(sub_id);
		} catch (Exception e) {
			sub_name = tmp;
			sub_id = db.getSubjectIDBySub_Name(tmp);
		}

		System.out.print("\n登録する得点を入力してください:");
		result_point = Integer.parseInt(sc.nextLine());

		System.out.println("\n\n登録する情報は以下の情報でよろしいですか？（y/n）\n"
				+ examinee_name + " - " + sub_name + ":" + result_point + "点 \n登録者:" + admin_name);
		tmp = sc.nextLine();
		if (tmp.equals("y") || tmp.equals("Y")) {
			db.InsertResultByName(examinee_id, sub_id, result_point, admin_name);
			System.out.println("登録が完了しました。");
		} else {
			System.out.println("入力を取り消しました。\n");
		}

	}

	private void ChooseUpdateOrDelete() {
		String tmp = null, function = null;
		String[] table = new String[2];

		System.out.println("\n----登録情報変更機能----");

		System.out.println("変更したい登録情報を、以下から選んでください。");
		System.out.println("0:受験結果 \t 1:受験者氏名");
		System.out.print("選択：");
		tmp = sc.nextLine();

		//操作するテーブルの設定
		switch (tmp) {
		case "0":
		case "受験結果":
			table = new String[] { "results", "受験結果" };
		case "1":
		case "受験者氏名":
			table = new String[] { "examinee", "受験者氏名" };
		}

		System.out.println("\n登録情報の変更、もしくは削除するかを選択してください。");
		System.out.println("0:変更 \t 1:削除");
		System.out.print("選択：");

		function = sc.nextLine();

		//機能の選択
		switch (function) {
		case "0":
		case "変更":
			UpdateData(table);
		case "1":
		case "削除":
			DeleteData(table);
		}

	}

	private void UpdateData(String[] _table) {
		String cell = null, updateData = null, examinee = null, examinee_name = null, sub = null, sub_name = null,
				permition = null;
		int examinee_id = 9999, sub_id = 9999;

		System.out.println("\n" + _table[1] + "を変更したい受験者のIDもしくは氏名を入力してください。");
		System.out.print("受験者:");
		examinee = sc.nextLine();

		//入力が数字かどうか正規表現を使って判断
		Pattern pt = Pattern.compile("^[0-9]+$");
		Matcher mc = pt.matcher(examinee);

		if (mc.find()) {
			examinee_name = examinee;
			examinee_id = Integer.parseInt(examinee);
		} else {
			ArrayList<Integer> ids = db.getIdDataByName(examinee);
			//名前が重複していた場合の処理
			if (ids.size() > 1) {
				System.out.print(examinee + "さんのIDを入力してください:");
				int checkId = Integer.parseInt(sc.nextLine());
				if (ids.contains(checkId)) {
					examinee_id = checkId;
				} else {
					System.out.println("IDが間違っています。");
					System.exit(0);
				}
			} else {
				examinee_id = ids.get(0);
				examinee_name = examinee;
			}
		}

		//変更が教科の得点の場合
		if (!(_table[1].equals("受験結果"))) {
			System.out.println("変更したい教科を入力してください。");
			db.getAllSubjectNameAndId();
			System.out.print("\n教科：");
			sub = sc.nextLine();
			//IDか教科名かチェック
			try {
				sub_id = Integer.parseInt(sub);
				sub_name = db.getSubjectNameBySub_Id(sub_id);
			} catch (Exception e) {
				sub_name = sub;
				sub_id = db.getSubjectIDBySub_Name(sub);
			}
			
			//変更後の得点入力
			System.out.print("\n変更後の得点を入力してください:");
			updateData=sc.nextLine();

			//最終確認
			System.out.println("\n変更は下記の通りでよろしいですか？(y/n)");
			System.out.println("変更前：");
			System.out.println("変更後：");
			System.out.print("y or n :");
			permition = sc.nextLine();
			if (permition.equals("y") || permition.equals("Y"))
				db.UpdateResults(_table[0], cell, examinee_id, sub_id, Integer.parseInt(updateData));
			else
				System.out.println("\n変更を取り消しました。");
		}else {
			//変更が氏名の場合
			System.out.print("\n変更後の氏名を入力してください:");
			updateData=sc.nextLine();
			//最終確認
			System.out.println("\n変更は下記の通りでよろしいですか？(y/n)");
			System.out.println("変更前：");
			System.out.println("変更後：");
			System.out.print("y or n :");
			permition = sc.nextLine();
			if (permition.equals("y") || permition.equals("Y"))
				db.UpdateResults(_table[0], cell, examinee_id, updateData);
			else
				System.out.println("\n変更を取り消しました。");
		}

	}

	private void DeleteData(String[] _table) {

	}

}
