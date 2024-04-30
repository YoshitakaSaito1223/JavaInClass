package work;

import java.util.Scanner;

import accessDB.AccessDB;

public class Login {
	public static String admin_name;
	
	public void adLogin() {
		String admin_password;
		int admin_id=0;
		
		//各種インスタンス生成
		Scanner scanner =new Scanner(System.in);
		AccessDB db=new AccessDB();
		
		//案内
		System.out.println("---管理者用ログイン機能---");
		
		//入力受付
		System.out.print("ID:");
		admin_id=Integer.parseInt(scanner.nextLine());
		System.out.print("Password:");
		admin_password=scanner.nextLine();
		System.out.println();

		//DBアクセス
		admin_name=db.Login(admin_id, admin_password);
		
		if(admin_name.equals("error")) {
			System.out.println("IDもしくはパスワードが間違っています。");
			System.exit(0);
		}else {
			System.out.println("ようこそ "+admin_name+" さん！");
		}
	
		
		
	}
}
