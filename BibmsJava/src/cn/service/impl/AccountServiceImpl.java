package cn.service.impl;

import java.time.LocalDateTime;
import java.util.Scanner;

import cn.dao.impl.AccountDaoImpl;
import cn.entity.Account;
import cn.entity.Transfer;
import cn.service.AccountService;

public class AccountServiceImpl implements AccountService {

	@Override
	public void register() {
		Scanner input = new Scanner(System.in);
		System.out.println("请按照提示输入被开户者信息：");
		Account account = new Account();
		System.out.print("-->开户人账户account：");
		account.setAccount(input.next());
		System.out.print("-->顾客姓名custName：");
		account.setCustName(input.next());
		System.out.print("-->银行卡号idCard：");
		account.setIdCard(input.next());
		System.out.print("-->开户人公司company：");
		account.setCompany(input.next());
		System.out.print("-->开户人账户address：");
		account.setAddress(input.next());
		account.setOpenTime(LocalDateTime.now());
		System.out.print("-->申请状态accState");
		account.setAccState(input.next());
		System.out.print("-->账户余额banlance");
		account.setBalance(input.nextDouble());

		AccountDaoImpl adi = new AccountDaoImpl();
		int i = adi.insert(account);
		if (i != 0) {
			System.out.println("开户成功！");
		} else {
			System.out.println("开户失败！");
		}
	}

	@Override
	public void searchInfo() {// 查询信息
		Scanner input = new Scanner(System.in);

		Account account = new Account();

		System.out.print("请输入用户idCard:");
		account.setIdCard(input.next());
		System.out.print("请输入用户名:");
		account.setCustName(input.next());

		AccountDaoImpl adi = new AccountDaoImpl();
		account = adi.select1(account);
		System.out.println("查询信息为：");
		System.out.println("Account\tcustName\tidCard\tcompany\taddress\topenTime\taccState\tbalance\t");
		System.out.println(account.getAccount() + "\t" + account.getCustName() + "\t" + account.getIdCard() + "\t"
				+ account.getCompany() + "\t" + account.getAddress() + "\t" + account.getOpenTime() + "\t"
				+ account.getAccState() + "\t" + account.getBalance());
	}

//	@Override
//	public void modifyInfo() {
//		Scanner input = new Scanner(System.in);
//
//		System.out.println("将进行修改信息操作：");
//		System.out.print("请输入要修改的字段oldRow：");
//		String oldRow = input.next();
//		System.out.print("请输入被修改的值neoRow：");
//		String neoRow = input.next();
//		System.out.print("请输入要修改人的idCard:");
//		String idCard = input.next();
//
//		Account account = new Account();
//		account.setIdCard(idCard);
//
//		AccountDaoImpl adi = new AccountDaoImpl();
//		int i = adi.update(oldRow, neoRow, account);
//		if (i != 0) {
//			System.out.println("成功！");
//		} else {
//			System.out.println("失败！");
//		}
//	}
	
	public void modifyInfo(Transfer transfer) {
		Double row = transfer.getTransAmount();
		AccountDaoImpl adi = new AccountDaoImpl();
		int i = adi.update(row,transfer);
		if (i != 0) {
			System.out.println("成功！");
		} else {
			System.out.println("失败！");
		}
	}

	@Override
	public void modifyInfo() {
		// TODO Auto-generated method stub
		
	}
}
