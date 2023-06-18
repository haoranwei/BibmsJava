package cn.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.dao.BaseDao;
import cn.dao.RoleDao;
import cn.entity.Role;

public class RoleDaoImpl extends BaseDao implements RoleDao{

	@Override
	public int insert(Role role) {
		String sql = "INSERT INTO `bibms_role`(`roleNo`,`roleName`) VALUES (?,?)";
	    Object[] params = { role.getRoleNo(),role.getRoleName()};
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
	    Object[] params = { role.getRoleNo(), role.getRoleName()};
	    return this.executeUpdate(sql, params);
	}

	@Override
	public int select(Role role) {
		String sql = "SELECT roleNo FROM `bibms_role` WHERE roleName = ?";
	    Object[] params = { role.getRoleName()};
	    return this.executeUpdate(sql, params);
	}
	
//	public Role getRoleById(int roleID) {
//		Connection conn = this.getConn();
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			pstmt = conn.prepareStatement("SELECT * FROM grade WHERE gradeID = ?");
//			pstmt.setInt(1, GradeID);
//			rs = pstmt.executeQuery();
//			Grade g = new Grade();
//			while(rs.next()) {
//				g.setGradeId(rs.getInt("GradeID"));
//				g.setGradeName(rs.getString("GradeName"));
//			}
//			return g;
//		} catch (SQLException e) {
//			
//			e.printStackTrace();
//			return null;
//		}
//		finally {
//			this.closeAll(conn, pstmt, rs);
//		}
//	}

}
