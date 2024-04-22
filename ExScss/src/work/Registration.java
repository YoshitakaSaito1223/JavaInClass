package work;

import java.util.Scanner;

public class Registration {
	private String name="";
	private int studenId=0;
	
	public void inputName(String _name) {
		
		Scanner scanner=new Scanner(System.in);
		
		System.out.println("---受験社登録機能---");
		System.out.print("登録したい名前を入力してください：");
		
		_name = scanner.next();
		
		//ここに受験番号生成機能
		
		scanner.close();
	}
	
	private int registerNumber(String _name) {
		
		
	}
	
	public void returnNumber(String _name) {
		
	}
	
	
}
