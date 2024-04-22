package accessDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccessDB {

    public static void connectDB(String _sql){
    	
    	//サーバー接続用プロファイル
    	final String DRIVER="com.mysql.cj.jdbc.Driver";
    	final String CONNECTION="jdbc:mysql://localhost:3306/java_ex_db?serverTimezone=JST";
    	final String USER="root";
    	final String PASSWORD="password1223";

    // 変数の準備
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // JDBCドライバのロード
            Class.forName(DRIVER);
            // データベース接続
            con = DriverManager.getConnection(CONNECTION,USER,PASSWORD);
            // SQL実行準備
            stmt = con.prepareStatement(_sql);
            // 実行結果取得
            rs = stmt.executeQuery();

        } catch (ClassNotFoundException e) {
            System.out.println("JDBCドライバのロードでエラーが発生しました");
        } catch (SQLException e) {
            System.out.println("データベースへのアクセスでエラーが発生しました。");
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
