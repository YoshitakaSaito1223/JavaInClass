package accessDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
				String id = rs.getString("examinee_id");
				String name = rs.getString("examinee_name");

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
				genId = rs.getInt("examinee_id") + 1;
			}
			// 登録
			stmt = con.prepareStatement(
					"INSERT INTO " + table + " (examinee_id,examinee_name) VALUES('" + genId + "','" + _userName
							+ "')");
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
	public ArrayList<Integer> getIdDataByName(String _userName) {
		String table = "examinee";
		ArrayList<Integer> ids = new ArrayList();
		//int[] result=new int[20];

		try {
			// JDBCドライバのロード
			Class.forName(DRIVER);
			// データベース接続
			con = DriverManager.getConnection(CONNECTION, USER, PASSWORD);
			// ひとつ前の番号取得
			stmt = con
					.prepareStatement("SELECT examinee_id FROM " + table + " WHERE examinee_name='" + _userName + "'");
			// 実行結果取得
			rs = stmt.executeQuery();

			//配列に格納
			while (rs.next()) {
				ids.add(rs.getInt("examinee_id"));
			}

			//値を返す
			return ids;
		} catch (ClassNotFoundException e) {
			System.out.println("JDBCドライバのロードでエラーが発生しました");
		} catch (SQLException e) {
			System.out.println("データベースへのアクセスでエラーが発生しました。");
			//error原因の調査用コマンド
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
		//		int[] none = new int[2];
		return ids;

	}

	/**
	 * 受験者IDと教科IDを入力すると試験結果を表示
	 * @param _user_id
	 * @param _sub_id
	 * @return result_point
	 */
	public int getResultByUserIdAndSubId(int _user_id, int _sub_id) {
		int result_point = 0;
		try {
			// JDBCドライバのロード
			Class.forName(DRIVER);
			// データベース接続
			con = DriverManager.getConnection(CONNECTION, USER, PASSWORD);

			stmt = con.prepareStatement(
					"SELECT result_point FROM results WHERE (sub_id=" + _sub_id + " AND examinee_id=" + _user_id + ")");
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
	 * 教科名を入れると対応する教科IDを出力
	 * @param _sub_name
	 * @return
	 */
	public int getSubjectIDBySub_Name(String _sub_name) {
		int sub_id = 9999;
		try {
			// JDBCドライバのロード
			Class.forName(DRIVER);
			// データベース接続
			con = DriverManager.getConnection(CONNECTION, USER, PASSWORD);

			stmt = con.prepareStatement("SELECT * FROM subjects WHERE sub_name=" + _sub_name);
			// 実行結果取得
			rs = stmt.executeQuery();

			if (rs.next()) {
				sub_id = rs.getInt("sub_id");
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
		return sub_id;
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

				System.out.print(sub_id + ":" + sub_name+"\t");
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
	 * 管理者ログイン機能
	 * @param admin_id
	 * @param admin_password
	 */
	public String Login(int admin_id, String admin_password) {
		try {
			// JDBCドライバのロード
			Class.forName(DRIVER);
			// データベース接続
			con = DriverManager.getConnection(CONNECTION, USER, PASSWORD);

			stmt = con.prepareStatement("SELECT * FROM admin WHERE admin_id=" + admin_id);
			// 実行結果取得
			rs = stmt.executeQuery();


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

		return "error";
	}

	/**
	 * IDを入力して試験結果を登録
	 * @param _examinee_id
	 * @param _sub_id
	 * @param _result_point
	 * @param _registered_by
	 */
	public void InsertResultByName(int _examinee_id, int _sub_id, int _result_point, String _registered_by) {
		int rowAffected=0;
		try {
			// JDBCドライバのロード
			Class.forName(DRIVER);
			// データベース接続
			con = DriverManager.getConnection(CONNECTION, USER, PASSWORD);
			// 登録
			stmt = con.prepareStatement("INSERT INTO results(examinee_id,sub_id,result_point,registered_by) "
					+ "VALUES(" + _examinee_id + "," + _sub_id + ","+_result_point +",'"+_registered_by+"')");
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
	}

}
