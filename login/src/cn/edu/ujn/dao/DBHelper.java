package cn.edu.ujn.dao;

import java.sql.*;

public class DBHelper {
	private static final String driver = "com.mysql.jdbc.Driver";
	private static final String url = "jdbc:mysql://localhost:3306/jsp?useUnicode=true&characterEncoding=UTF-8";
	private static final String username = "root";
	private static final String password = "root123";

	private static Connection conn = null;

//  ��̬����鸺���������
//  һ�������,�����Щ�����������Ŀ������ʱ���ִ�е�ʱ��,��Ҫʹ�þ�̬�����,���ִ���������ִ�еģ�����������������
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
