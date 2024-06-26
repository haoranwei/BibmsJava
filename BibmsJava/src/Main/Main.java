package Main;
import java.util.Scanner;

import cn.dao.impl.AccountDaoImpl;
import cn.dao.impl.TransferDaoImpl;
import cn.entity.Transfer;
import cn.entity.User;
import cn.service.impl.AccountServiceImpl;
import cn.service.impl.RoleServciceImpl;
import cn.service.impl.SalaryServiceImpl;
import cn.service.impl.UserServiceImpl;

public class Main {
	public static void main(String[] args) {
		while (true) {
			Scanner input = new Scanner(System.in);
			System.out.println("========欢迎使用银行中间业务系统:========");
			System.out.println("========本代码由Java第2小组呈现========");
			System.out.println("=====" + "1.登录;2.注册;3.转账;4.退出======");
			System.out.print("--->>>" + "请选择：");
			int choice = input.nextInt();
			if (choice == 1) {
				UserServiceImpl usi = new UserServiceImpl();
				User user = new User();
				user = usi.check();
				if(user.getUserRole().equals("1")) {
					Role1();
					RoleServciceImpl rsi = new RoleServciceImpl();
					choice = input.nextInt();
					if(choice == 1) rsi.Add();
					else if(choice == 2) rsi.Search();
					else if(choice == 3) rsi.Modify();
					else {
						continue;
					}
				}
				else if(user.getUserRole().equals("2")){
					Role2();
					AccountServiceImpl asi = new AccountServiceImpl();
					SalaryServiceImpl ssi = new SalaryServiceImpl();
					choice = input.nextInt();
					if(choice == 1) {
						//开户
						asi.register();
					}else if(choice == 2) {
						//查询信息
						asi.searchInfo();
					}else if(choice == 3) {
						//修改信息
						asi.modifyInfo();
					}else if(choice == 4){
						//导入代发工资
						ssi.putSalary();
						
					}else {
						continue;
					}
				}else if(user.getUserRole().equals("3")) {
					Role3();
					AccountServiceImpl adi = new AccountServiceImpl();
					choice = input.nextInt();
					SalaryServiceImpl ssi = new SalaryServiceImpl();
					if(choice == 1) {
						ssi.ckeckSalary();
					}else if(choice == 2 || choice == 3) {
						System.out.println("系统正在升级...");
					}else {
						continue;
					}
				}else {
					System.out.println("未识别，登录失败！");
				}
			} else if (choice == 2) {
				UserServiceImpl usi = new UserServiceImpl();
				usi.register();
				continue;
			} else if (choice == 3) {
				Transfer transfer = new Transfer();
				System.out.println("开始转账...");
				System.out.println("请输入转出账户：（bibms_account表中的account字段）");
				String transOutAcc = input.next();
				System.out.println("请输入转入账户：（bibms_account表中的account字段）");
				String transInAcc = input.next();
				System.out.println("请输入转出金额：（对应数据库的account）（例如：100.00 需要精确到小数点后两位！）");
				Double transAmount = input.nextDouble();
				System.out.println("请输入id号");
				int id = input.nextInt();
				
				transfer.setId(id);
				transfer.setTransAmount(id);
				transfer.setTransInAcc(transInAcc);
				transfer.setTransOutAcc(transOutAcc);
				
				TransferDaoImpl tdi = new TransferDaoImpl();
				int i = tdi.insert(transfer);
				if(i != 0) System.out.println("转出成功！");
				else {
					System.out.println("转出失败！");
				}
				
				AccountServiceImpl asi = new AccountServiceImpl();
				asi.modifyInfo(transfer);
			} else {
				System.out.println("感谢您的使用！");
				System.out.println("本代码由Java第2小组呈现");
				System.out.println("主要框架设计及实现者:韦浩然");
			}
		}

	}
	
	public static void Role1() {
		System.out.println("登录成功！");
		System.out.println("========欢迎你：系统管理员========");
		System.out.println("1.新增角色 2.查询角色 3.修改角色 4.返回主菜单");
	}
	
	public static void Role2() {
		System.out.println("登录成功！");
		System.out.println("========欢迎你：营业部职员========");
		System.out.println("1.开户 2.查询账户信息 3.修改账户信息 4.导入代发工资");
		System.out.println("5.查询代发工资(0) 6.统计代发工资(0) 7.返回主菜单");
	}
	
	public static void Role3() {
		System.out.println("登录成功！");
		System.out.println("========欢迎你：营业部经理========");
		System.out.println("1.代发工资审批 2.查询代发工资(0) 3.统计代发工资(0) 4.返回主菜单");
	}
}