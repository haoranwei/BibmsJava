package cn.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseDao {
	private static String driver = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost:3306/bibms";
	private static String user = "root";
	private static String password = "root";

	Connection conn = null;

//	static {
//		init();
//	}
//
//	public static void init() {
//		Properties prop = new Properties();
//		InputStream is = BaseDao.class.getClassLoader().getResourceAsStream("db.properties");
//		try {
//			prop.load(is);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		driver = prop.getProperty("dirver");
//		url = prop.getProperty("url");
//		user = prop.getProperty("user");
//		password = prop.getProperty("password");
//	}

	public Connection getConn() {
		try {
			if (conn == null || conn.isClosed()) {
				try {
					Class.forName(driver);
					// 加载驱动JDBC

				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}

				try {
					conn = DriverManager.getConnection(url, user, password);
					// 建立连接
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

	public void closeAll(Connection conn, Statement stmt, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public int executeUpdate(String sql, Object[] params) {
		conn = this.getConn();

		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement(sql);

			for (int i = 0; i < params.length; i++) {
				pstmt.setObject(i + 1, params[i]);

			}

			return pstmt.executeUpdate();
			// 返回受影响的行或列
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} finally {
			closeAll(conn, pstmt, null);
		}
	}
}
