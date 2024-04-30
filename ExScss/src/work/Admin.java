package work;

import java.util.ArrayList;
import java.util.Scanner;

import accessDB.AccessDB;

public class Admin {
	//インスタンス作成
	Scanner scanner = new Scanner(System.in);
	Registration rgstr = new Registration();
	Search search = new Search();
	AccessDB accessdb = new AccessDB();
	Login login = new Login();
	
	String admin_name = login.admin_name;

	public void SelectFunction() {

		String adloop = "";
		while (!(adloop.equals("exit"))) {
			System.out.println("\n-----管理者機能-----");
			System.out.println("利用したい機能の番号を入力してください。(終了する際には「exit」と入力)\n"
					+ "1.テスト結果登録機能 ： 名前、もしくは受験番号を入力すると試験結果を登録します。\n"
					+ "2.受験結果確認機能 : 名前(必要に応じて受験番号)を入力すると、合否が出力されます。\n"
					+ "3.受験結果変更機能 : 受験結果を変更することができます。\n");
			System.out.print("機能番号：");
			adloop = scanner.nextLine();
			switch (adloop) {
			case "1":
				RegisterResult();
				break;
			case "2":
				search.searchResult();
				break;
			case "3":
				System.out.println("未実装です。");
			case "exit":
				System.out.println("\n終了します。");
				System.exit(0);
			default:
				break;

			}
		}
	}

	public void RegisterResult() {
		String examinee_name,sub_name,tmp;
		int examinee_id=0, sub_id=0, result_point=0;

		
		//登録する名前
		System.out.print("\n登録したい人の名前もしくはIDを入力してください:");
		tmp=scanner.nextLine();
		
		//名前かIDか判定
		try {
			examinee_id=Integer.parseInt(tmp);
			examinee_name=accessdb.getNameByID(examinee_id);
		}catch(Exception e) {
			examinee_name=tmp;
			ArrayList<Integer> ids=new ArrayList();
			ids = accessdb.getIdDataByName(tmp);
			
			//名前が重複していた場合の処理
			if(ids.size()>1) {
				System.out.print(tmp+"さんのIDを入力してください:");
				int checkId=Integer.parseInt(scanner.nextLine());
				if(ids.contains(checkId)) {
					examinee_id=checkId;
				}else {
					System.out.println("IDが間違っています。");
					System.exit(0);
				}
			}else {
				examinee_id=ids.get(0);
			}
		}
		
		System.out.print("\n登録したい教科を入力してください：");
		tmp=scanner.nextLine();
		//教科名か教科IDか判定
		try {
			sub_id=Integer.parseInt(tmp);
			sub_name=accessdb.getSubjectNameBySub_Id(sub_id);
		}catch(Exception e) {
			sub_name=tmp;
			sub_id=accessdb.getSubjectIDBySub_Name(tmp);
		}
		
		
		System.out.print("\n登録する得点を入力してください:");
		result_point=Integer.parseInt(scanner.nextLine());
		
		System.out.println("\n\n登録する情報は以下の情報でよろしいですか？（y/n）\n"
				+ examinee_name+" - "+sub_name+":"+result_point+"点 \n登録者:"+admin_name);
		tmp=scanner.nextLine();
		if(tmp.equals("y") || tmp.equals("Y")) {
			accessdb.InsertResultByName(examinee_id, sub_id, result_point, admin_name);
			System.out.println("登録が完了しました。");
		}else {
			System.out.println("入力を取り消しました。\n");
		}
		

	}

}
