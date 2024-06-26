package cn.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.dao.BaseDao;
import cn.dao.UserDao;
import cn.entity.User;

public class UserDaoImpl extends BaseDao implements UserDao {
	public int insert(User user) {
		String sql = "INSERT INTO `bibms_user`(`userName`,`realName`,`password`,`userRole`)VALUES(?,?,?,?)";
//		在SQL语句中，应该使用问号作为占位符，而不是反引号,不能是：`?`
		Object[] params = { user.getUserName(), user.getRealName(), user.getPassword(), user.getUserRole() };
		return this.executeUpdate(sql, params);
	}

	@Override
	public User checkDao(User user) {
		Connection conn = null;
		conn = this.getConn();
		
		Statement stmt = null;
		
		boolean dataMatches = false; // 默认数据匹配为假
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT userName,password,userRole FROM `bibms_user`");
			
			while (rs.next()) {
				String userName = rs.getString("userName");
				String password = rs.getString("password");
				String userRole = rs.getString("userRole");
				
				if (userName.equals(user.getUserName()) && password.equals(user.getPassword())) {
					dataMatches = true; // 数据不匹配
					user.setUserName(userName);
					user.setPassword(password);
					user.setUserRole(userRole);
					break; // 提前结束比较
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeAll(conn, stmt, null);
		}
		if(dataMatches == false) user.setUserRole("0");
		return user;
	}

}
