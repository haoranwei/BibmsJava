package cn.dao;

import cn.entity.Account;
import cn.entity.Salary;

public interface SalaryDao  {
	public int update(String oldRow,String neoRow,Salary salary);
	public Salary select(Salary salary);
	public int update(int id,String approve);
}
