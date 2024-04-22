package work;

import java.util.Scanner;

import accessDB.AccessDB;

public class Registration {
	private String name="";
	private int studentId=0;
	
	
	
	public Registration() {
		super();
	}

	public void inputName() {
		
		Scanner scanner=new Scanner(System.in);
		
		System.out.println("---受験社登録機能---");
		System.out.print("登録したい名前を入力してください：");
		
		String _name = scanner.next();
		
		//ここに受験番号生成機能
		AccessDB accessdb=new AccessDB();
		//DB接続
		accessdb.connectDB();
		//ユーザーデータ登録
		int userId=accessdb.setUserNameToGenId(_name);
		System.out.println(_name+"さんのIDは"+userId+"です。");
		
		System.out.println("");
		
		
		scanner.close();
	}
	
	private int registerNumber(String _name) {
		
		return 0;
	}
	
	public void returnNumber(String _name) {
		
	}
	
	
}
