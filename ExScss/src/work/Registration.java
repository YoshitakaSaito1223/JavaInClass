package work;

import java.util.Scanner;

import accessDB.AccessDB;

public class Registration {
	private String name="";
	private int studentId=0;
	
	
	
	public Registration() {
		super();
	}

	/**
	 * 名前を入力するとIDを生成し、DBへ登録
	 */
	public void inputName() {
		
		Scanner scanner=new Scanner(System.in);
		
		System.out.println("---受験者登録機能---");
		System.out.print("登録したい名前を入力してください：");
		
		String _name = scanner.next();
		
		//ここに受験番号生成機能
		AccessDB accessdb=new AccessDB();
		
		//ユーザーデータ登録
		int userId=accessdb.setUserNameToGenId(_name);
		System.out.println(_name+"さんのIDは"+userId+"です。");
		
		
	}
	
}
