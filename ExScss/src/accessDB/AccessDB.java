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

	/**
	 * DB接続テスト用クラス
	 */

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

	/**
	 * 名前を入力するとIDを返し、それらをDBへ登録
	 * @param _userName
	 * @return genId
	 */
	public int setUserNameToGenId(String _userName) {
		String table = "examinee";
		int genId = 0, rowAffected;

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
			//error原因の調査用コマンド
			//			System.out.println(e.getMessage());
			//			System.out.println(e.getCause());
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

	/**
	 * 名前を入力するとIDを表示
	 * @param _userName //ユーザー名
	 * @return result　//試験結果
	 */
	public int[] getIdDataByName(String _userName) {
		String table = "examinee";
		int i = 0, j = 0;
		//int[] result=new int[20];

		try {
			// JDBCドライバのロード
			Class.forName(DRIVER);
			// データベース接続
			con = DriverManager.getConnection(CONNECTION, USER, PASSWORD);
			// ひとつ前の番号取得
			stmt = con.prepareStatement("SELECT id FROM " + table + " WHERE name=" + _userName);
			// 実行結果取得
			rs = stmt.executeQuery();

			//帰ってきた値の数をカウント
			while (rs.next())
				i++;
			//返り値を格納する配列を用意
			int[] ids = new int[i + 1];

			//配列に格納
			while (rs.next()) {
				ids[j] = rs.getInt("id");
				j++;
			}

			//値を返す
			return ids;
		} catch (ClassNotFoundException e) {
			System.out.println("JDBCドライバのロードでエラーが発生しました");
		} catch (SQLException e) {
			System.out.println("データベースへのアクセスでエラーが発生しました。");
			//error原因の調査用コマンド
			//			System.out.println(e.getMessage());
			//			System.out.println(e.getCause());
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				System.out.println("データベースへのアクセスでエラーが発生しました。");
			}
		}
		int[] none = new int[2];
		return none;

	}

	/**
	 * 受験者IDと教科IDを入力すると試験結果を表示
	 * @param _user_id
	 * @param _sub_id
	 * @return result_point
	 */
	public int getResultByUserIdAndSubId(int _user_id, int _sub_id) {
		int result_point=0;
		try {
			// JDBCドライバのロード
			Class.forName(DRIVER);
			// データベース接続
			con = DriverManager.getConnection(CONNECTION, USER, PASSWORD);

			stmt = con.prepareStatement("SELECT * FROM results WHERE sub_id=" + _sub_id+" AND examinee_id="+ _user_id);
			// 実行結果取得
			rs = stmt.executeQuery();

			if (rs.next()) {
				result_point = rs.getInt("result_point");
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
		return result_point;

	}

	/**
	 * 教科IDを入れると対応する教科名を出力
	 * @param _sub_id
	 * @return sub_name
	 */
	public String getSubjectNameBySub_Id(int _sub_id) {
		String sub_name = "なし";
		try {
			// JDBCドライバのロード
			Class.forName(DRIVER);
			// データベース接続
			con = DriverManager.getConnection(CONNECTION, USER, PASSWORD);

			stmt = con.prepareStatement("SELECT * FROM subjects WHERE sub_id=" + _sub_id);
			// 実行結果取得
			rs = stmt.executeQuery();

			if (rs.next()) {
				sub_name = rs.getString("sub_name");
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
		return sub_name;
	}

	/**
	 * 教科一覧をIDとともに出力
	 */
	public void getAllSubjectNameAndId() {
		try {
			// JDBCドライバのロード
			Class.forName(DRIVER);
			// データベース接続
			con = DriverManager.getConnection(CONNECTION, USER, PASSWORD);

			stmt = con.prepareStatement("SELECT * FROM subjects");
			// 実行結果取得
			rs = stmt.executeQuery();

			while (rs.next()) {
				int sub_id = rs.getInt("sub_id");
				String sub_name = rs.getString("sub_name");

				System.out.println(sub_id + ":" + sub_name);
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

	/*
	//名前を入力して試験結果を入力
	public float InsertResultByName(String _userName) {
	
	}
	
	//IDを入力して試験結果を入力
	public float InsertResultById(int _id) {
	
	}
	
	
	*/
}
