package kr.co.koscom.web;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {
	// 접속
	public static Connection getConnect() throws Exception{
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "kos_20886";
		String password = "kos_20886";
		Connection conn = null;
		// 드라이버로딩
		Class.forName("oracle.jdbc.driver.OracleDriver");
		// 접속
		conn = DriverManager.getConnection(url, user, password);
		return conn;
	}

	// 닫기
	public static void close(Connection conn) {
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static void close(Connection conn, PreparedStatement ps) {
		if(ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		close(conn);
	}
	public static void close (Connection conn, PreparedStatement ps, ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		close(conn,ps);
	}
}
