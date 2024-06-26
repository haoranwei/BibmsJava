package cn.service.impl;

import java.util.Scanner;

import cn.dao.impl.AccountDaoImpl;
import cn.dao.impl.SalaryDaoImpl;
import cn.entity.Account;
import cn.entity.Salary;
import cn.service.SalaryService;

public class SalaryServiceImpl implements SalaryService{

	@Override
	public void putSalary() {
		Scanner input = new Scanner(System.in);
		Account account = new Account();
		Salary salary = new Salary();
		
		
		System.out.print("请输入要导入工资客户的idCrad");
		String idCard = input.next();
		
		salary.setIdCard(idCard);
		account.setIdCard(idCard);
		
		System.out.print("请输入要导入工资客户的custName");
		account.setCustName(input.next());
		AccountDaoImpl adi = new AccountDaoImpl();
		
		Double balance = adi.select(account);
		
		SalaryDaoImpl sdi = new SalaryDaoImpl();
		salary = sdi.select(salary);
		Double salaryCount = salary.getSalary();
		
		balance = balance + salaryCount;
		
		adi.update(balance,account);
		
		System.out.println("更新成功！");
	}

	@Override
	public void ckeckSalary() {
		Scanner input = new Scanner(System.in);
		
		System.out.println("输入流水单id");
		int id = input.nextInt();
		String approve = null;
		System.out.println("此id的流水单是否审批通过？Y/N");
		if(input.next().equals("Y")) approve = "1";
		else approve = "0";
		
		SalaryDaoImpl sdi = new SalaryDaoImpl();
		int i = sdi.update(id, approve);
		if(i != 0 ) {
			System.out.println("成功！");
		}else {
			System.out.println("失败！");
		}
	}

}
