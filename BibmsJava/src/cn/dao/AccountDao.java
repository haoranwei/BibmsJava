package cn.dao;


import cn.entity.Account;
import cn.entity.Transfer;

public interface AccountDao {
	int insert(Account account);
	
	double select(Account account);
	
	Account select1(Account account);
	
	public int update(Double row,Account account);
	
	public int update(Double row,Transfer transfer);
}
