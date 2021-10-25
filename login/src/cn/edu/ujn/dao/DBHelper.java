package cn.edu.ujn.dao;

import java.sql.*;

public class DBHelper {
	private static final String driver = "com.mysql.jdbc.Driver";
	private static final String url = "jdbc:mysql://localhost:3306/jsp?useUnicode=true&characterEncoding=UTF-8";
	private static final String username = "root";
	private static final String password = "root123";

	private static Connection conn = null;

//  静态代码块负责加载驱动
//  一般情况下,如果有些代码必须在项目启动的时候就执行的时候,需要使用静态代码块,这种代码是主动执行的，且优先于主函数。
	static {
		try {
			Class.forName(driver);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static Connection getConnection() throws Exception {
		if (conn == null || conn.isClosed()) {
			conn = DriverManager.getConnection(url, username, password);
			return conn;
		}
		return conn;
	}
}
