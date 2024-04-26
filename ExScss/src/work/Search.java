package work;

import java.util.ArrayList;
import java.util.Scanner;

import accessDB.AccessDB;

public class Search {

	public void searchResult() {
		int id=0,checkId,sub_id,result_point=0;
		String sub_name;
		
		AccessDB accessdb = new AccessDB();

		System.out.println("---受験結果確認機能---");
		System.out.print("あなたの名前を入力してください：");

		Scanner scanner = new Scanner(System.in);

		String _name = scanner.next();

		System.out.println();
		
		ArrayList<Integer> ids=new ArrayList();
		ids = accessdb.getIdDataByName(_name);
		
		//名前が重複していた場合の処理
		if(ids.size()>1) {
			System.out.print("あなたのIDを入力してください:");
			checkId=scanner.nextInt();
			if(ids.contains(checkId)) {
				id=checkId;
			}else {
				System.out.println("IDが間違っています。");
			}
		}else {
			id=ids.get(0);
		}
		
		
//		System.out.println(id);  //デバッグ用
		
		//教科一覧を取得,表示
		System.out.println("\n受験結果を知りたい教科に対応する数字を入力してください。");
		accessdb.getAllSubjectNameAndId();
		
		System.out.print("教科の番号：");
		sub_id=scanner.nextInt();
		sub_name=accessdb.getSubjectNameBySub_Id(sub_id);
		
		//受験者ID,教科IDから結果取得
		result_point=accessdb.getResultByUserIdAndSubId(id, sub_id);
		System.out.println("\n"+_name+"さんの"+sub_name+"の得点："+result_point);
		
		
		scanner.close();
	}

}
