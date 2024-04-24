package work;

import java.util.Arrays;
import java.util.Scanner;

import accessDB.AccessDB;

public class Search {

	public void searchResult() {
		int id,checkId;
		
		AccessDB accessdb = new AccessDB();

		System.out.println("---受験結果確認機能---");
		System.out.print("あなたの名前を入力してください：");

		Scanner scanner = new Scanner(System.in);

		String _name = scanner.next();

		System.out.println();
		
		int[] ids = accessdb.getIdDataByName(_name);
		
		//名前が重複していた場合の処理
		if(ids.length>2) {
			System.out.println("あなたのIDを入力してください:");
			checkId=scanner.nextInt();
			if(Arrays.asList(ids).contains(checkId)) {
				id=checkId;
			}else {
				System.out.println("IDが間違っています。");
			}
		}else {
			id=ids[0];
		}
		
		//Idから結果取得
		
		scanner.close();
	}

}
