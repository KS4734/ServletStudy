package sevlet_study.com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ScottDBConn {
	private static Connection conn;
	private static String url="jdbc:mysql://localhost:3306/scott";
	private static String user="root";
	private static String pw="mysql";
	private static String driver="com.mysql.cj.jdbc.Driver";
	//throws ClassNotFoundException 예외 위임 : 최종 사용자에서 예외처리(try)할 것을 강제함
	public static Connection getConn() throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		conn=DriverManager.getConnection(url, user, pw);
		return conn;
	}
	
	//test
//	public static void main(String[] args) {
//		try {
//			System.out.println(ScottDBConn.getConn());
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
}
