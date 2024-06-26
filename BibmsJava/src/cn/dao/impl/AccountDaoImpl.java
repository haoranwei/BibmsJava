package cn.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

import cn.dao.AccountDao;
import cn.dao.BaseDao;
import cn.entity.Account;
import cn.entity.Transfer;

public class AccountDaoImpl extends BaseDao implements AccountDao{

	@Override
	public int insert(Account account) {
		String sql = "INSERT INTO `bibms_account` VALUES (?,?,?,?,?,?,?,?);";
	    Object[] params = { account.getAccount(),account.getCustName(),account.getIdCard(),account.getCompany(),
	    		account.getAddress(),java.sql.Timestamp.valueOf(account.getOpenTime()),account.getAccState(),account.getBalance()};
	    return this.executeUpdate(sql, params);
	}

	@Override
	public double select(Account account) {
		Connection conn = null;
		conn = this.getConn();
		
		Statement stmt = null;
		double balance = 0;
		boolean dataMatches = false; // 默认数据匹配为假
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM `bibms_account`");
			
			while (rs.next()) {
				String custName = rs.getString("custName");
				String idCard = rs.getString("idCard");
				
				balance = rs.getDouble("balance");
				
				if (idCard.equals(account.getIdCard())  &&  custName.equals(account.getCustName())) {
					dataMatches = true; // 数据不匹配
					break; // 提前结束比较
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeAll(conn, stmt, null);
		}
		return balance;
	}

	@Override
	public int update(Double row,Account account) {//修改信息
			String sql = "UPDATE `bibms_account` SET balance = ? WHERE idCard = ?";
			Object[] params = {row,account.getIdCard()};
			return this.executeUpdate(sql, params);
	}
	
	public int update(Double row,Transfer transfer) {//修改信息
		String sql1 = "UPDATE `bibms_account` SET balance = balance + ? WHERE account = ?";
		Object[] params1 = {row,transfer.getTransInAcc()};
		int result1 = this.executeUpdate(sql1, params1);
		
		String sql2 = "UPDATE `bibms_account` SET balance = balance - ? WHERE account = ?";
		Object[] params2 = {row,transfer.getTransOutAcc()};
		return this.executeUpdate(sql2, params2);
}

	@Override
	public Account select1(Account account) {
		Connection conn = null;
		conn = this.getConn();
		
		Statement stmt = null;
		
		boolean dataMatches = false; // 默认数据匹配为假
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM `bibms_account`");
			
			while (rs.next()) {
				String account1 = rs.getString("account");
				String custName = rs.getString("custName");
				String idCard = rs.getString("idCard");
				String company = rs.getString("company");
				String address = rs.getString("address");
				
				// 获取Timestamp数据
				java.sql.Timestamp timestamp = rs.getTimestamp("openTime");
                // 转换为LocalDateTime对象
                LocalDateTime openTime = timestamp.toLocalDateTime();
                
				String accState = rs.getString("accState");
				double balance = rs.getDouble("balance");
				
				if (idCard.equals(account.getIdCard())  &&  custName.equals(account.getCustName())) {
					dataMatches = true; // 数据不匹配
					account.setAccount(account1);
					account.setAccState(accState);
					account.setAddress(address);
					account.setBalance(balance);
					account.setCompany(company);
					account.setCustName(custName);
					account.setIdCard(idCard);
					account.setOpenTime(openTime);
					break; // 提前结束比较
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeAll(conn, stmt, null);
		}
		if(dataMatches == false) account.setAccState("Error！");;
		return account;
	}
}




