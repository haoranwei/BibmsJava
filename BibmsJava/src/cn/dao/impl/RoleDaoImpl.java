package cn.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import cn.dao.BaseDao;
import cn.dao.RoleDao;
import cn.entity.Role;

public class RoleDaoImpl extends BaseDao implements RoleDao {

	@Override
	public int insert(Role role) {
		String sql = "INSERT INTO `bibms_role`(`roleNo`,`roleName`) VALUES (?,?)";
		Object[] params = { role.getRoleNo(), role.getRoleName() };
		return this.executeUpdate(sql, params);
	}

	@Override
	public int delete(int roleId) {
		String sql = "DELETE FROM grade WHERE gradeID = ?";
		Object[] params = { roleId };
		return this.executeUpdate(sql, params);
	}

	@Override
	public int update(Role role) {
		String sql = "UPDATE `bibms_role` SET roleNo = ? WHERE roleName = ?";
		Object[] params = { role.getRoleNo(), role.getRoleName() };
		return this.executeUpdate(sql, params);
	}

	@Override
	public Role select(Role role) {
		Connection conn = null;
		conn = this.getConn();
		Statement stmt = null;
		boolean dataMatches = false;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM `bibms_role`");

			while (rs.next()) {
				String roleNo = rs.getString("roleNo");
				String roleName = rs.getString("roleName");

				if (roleNo.equals(role.getRoleNo())) {
					dataMatches = true; // 数据不匹配
					role.setRoleName(roleName);
					break; // 提前结束比较
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, stmt, null);
		}
		return role;
	}

}
