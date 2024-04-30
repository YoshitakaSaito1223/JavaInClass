package work;

import java.util.Scanner;

import accessDB.AccessDB;

public class Examination {
	
	public void tryExam() {
		AccessDB db =new AccessDB();
		Scanner sc =new Scanner(System.in);
		
		System.out.println("---受験機能---");
		System.out.print("あなたの名前を入力してください：");
		String examinee_name=sc.nextLine();
		System.out.print("あなたのIDを入力してください：");
		int examinee_id=Integer.parseInt(sc.nextLine());
		
		if(!(db.getNameByID(examinee_id).equals(examinee_name))) {
			System.out.println("IDと名前が一致しません");
			System.exit(0);
		}
		
		System.out.println("受験する教科を以下から選んでください。");
		db.getAllSubjectNameAndId();
		System.out.print("\n教科番号：");
		int sub_id=Integer.parseInt(sc.nextLine());
		String sub_name=db.getSubjectNameBySub_Id(sub_id);
		
		int result_point=(int)(Math.random()*71+30);
		
		System.out.println("\nあなたの"+sub_name+"の得点は"+result_point+"点です。");
		
		db.InsertResultByName(examinee_id, sub_id, result_point, examinee_name);
		
			
		
	}
	
	
}
