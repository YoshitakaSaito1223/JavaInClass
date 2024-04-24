package accessDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccessDB {

	// 変数の準備
	static Connection con = null;
	static PreparedStatement stmt = null;
	static ResultSet rs = null;
	//サーバー接続用プロファイル
	final String DRIVER = "com.mysql.cj.jdbc.Driver";
	final String CONNECTION = "jdbc:mysql://localhost:3306/java_ex_db?serverTimezone=Asia/Tokyo";
	final String USER = "root";
	final String PASSWORD = "password1223";

	public void connectDB() {

		try {
			// JDBCドライバのロード
			Class.forName(DRIVER);
			// データベース接続
			con = DriverManager.getConnection(CONNECTION, USER, PASSWORD);

			stmt = con.prepareStatement("SELECT * FROM examinee");
			// 実行結果取得
			rs = stmt.executeQuery();

			while (rs.next()) {
				String id = rs.getString("id");
				String name = rs.getString("name");

				System.out.println(id + ":" + name);
			}

		} catch (ClassNotFoundException e) {
			System.out.println("JDBCドライバのロードでエラーが発生しました");
		} catch (SQLException e) {
			System.out.println("データベースへのアクセスでエラーが発生しました。");
			System.out.println(e.getCause());
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				System.out.println("データベースへのアクセスでエラーが発生しました。");
			}
		}
	}

	//名前入力時にID生成
	public int setUserNameToGenId(String _userName) {
		String table = "examinee";
		int genId = 0,rowAffected;

		try {
			// JDBCドライバのロード
			Class.forName(DRIVER);
			// データベース接続
			con = DriverManager.getConnection(CONNECTION, USER, PASSWORD);
			// ひとつ前の番号取得
			stmt = con.prepareStatement("SELECT * FROM " + table + " ORDER BY id DESC LIMIT 1");
			// 実行結果取得
			rs = stmt.executeQuery();
			//数値を代入
			if (rs.next()) {
				genId = rs.getInt("id") + 1;
			}
			// 登録
			stmt = con.prepareStatement(
					"INSERT INTO " + table + " (id,name) VALUES('" + genId + "','" + _userName + "')");
			// 実行結果取得
			rowAffected = stmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			System.out.println("JDBCドライバのロードでエラーが発生しました");
		} catch (SQLException e) {
			System.out.println("データベースへのアクセスでエラーが発生しました。");
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				System.out.println("データベースへのアクセスでエラーが発生しました。");
			}
		}

		return genId;
	}

	/*
	//名前を入力すると試験結果を表示
	public String getTableDataByName(String _userName) {
	
	}
	
	//名前とIDを入力すると試験結果を表示
	public String getTableDataByNameAndId(String _userName, int _id) {
	
	}
	
	//名前を入力して試験結果を入力
	public float InsertResultByName(String _userName) {
	
	}
	
	//IDを入力して試験結果を入力
	public float InsertResultById(int _id) {
	
	}
	
	
	*/
}
