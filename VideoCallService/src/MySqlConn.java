
import java.sql.*;

public class MySqlConn {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://10.10.10.110:3306/test";

	static final String USERNAME = "kos_20886";
	static final String PASSWORD = "kos_20886";

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		try{
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
			System.out.println("\n- MySQL Connection");
			stmt = conn.createStatement();
			
			String sql;
			sql = "SELECT groupname, membername FROM idol";
			ResultSet rs = stmt.executeQuery(sql);

			while(rs.next()){
				String groupName = rs.getString("groupName");
				String memberName = rs.getString("memberName");

				System.out.print("\n** Group : " + groupName);
				System.out.print("\n    -> Member: " + memberName);
			}
			rs.close();
			stmt.close();
			conn.close();
		}catch(SQLException se1){
			se1.printStackTrace();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			try{
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2){
			}
			try{
				if(conn!=null)
					conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		System.out.println("\n\n- MySQL Connection Close");
	}
}