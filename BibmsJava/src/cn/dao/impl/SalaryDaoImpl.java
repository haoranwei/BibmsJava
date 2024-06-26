package cn.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

import cn.dao.BaseDao;
import cn.dao.SalaryDao;
import cn.entity.Salary;

public class SalaryDaoImpl extends BaseDao implements SalaryDao{

	@Override
	public int update(String oldRow, String neoRow, Salary salary) {
		String sql = "UPDATE `bibms_salary` SET ? = ? WHERE idCard = ?";
		Object[] params = {oldRow,neoRow,salary.getIdCard()};
		return this.executeUpdate(sql, params);
	}

	@Override
	public Salary select(Salary salary) {
		Connection conn = null;
		conn = this.getConn();
		
		Statement stmt = null;
		
		boolean dataMatches = false; // 默认数据匹配为假
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM `bibms_salary`");
			
			while (rs.next()) {
				String idCard = rs.getString("idCard");
				double salaryCount = rs.getDouble("salary");
				
				if (idCard.equals(salary.getIdCard())) {
					dataMatches = true; // 数据不匹配
					salary.setIdCard(idCard);
					salary.setSalary(salaryCount);
					break; // 提前结束比较
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeAll(conn, stmt, null);
		}
		if(dataMatches == false) salary.setApprove("-1");
		return salary;
	}

	

	@Override
	public int update(int id,String approve) {
		String sql = "UPDATE `bibms_salary` SET approve = ? WHERE id = ?";
		Object[] params = {approve,id};
		return this.executeUpdate(sql, params);
	}
	
}
