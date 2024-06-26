package cn.service.impl;

import java.util.Scanner;

import cn.dao.UserDao;
import cn.dao.impl.UserDaoImpl;
import cn.entity.User;
import cn.service.UserService;

public class UserServiceImpl implements UserService {
	private String userName;
	private String password;
	private String realName;
	Scanner input = new Scanner(System.in);

	@Override
	public void register() {
		System.out.println("系统开始注册进程...");
		System.out.print("请输入用户名(userName)：");
		userName = input.next();
		System.out.print("请输入密码(password)：");
		password = input.next();
		System.out.print("请输入真实姓名(realName)：");
		realName = input.next();

		User user = new User();

		user.setUserName(userName);
		user.setPassword(password);
		user.setRealName(realName);

		System.out.print("--->>>请选择角色：1.系统管理员;2.营业部职员;3.营业部经理;");
		int choice = input.nextInt();
		if (choice == 1) {
			user.setUserRole("1");
		}else if(choice == 2) {
			user.setUserRole("2");
		}else {
			user.setUserRole("3");
		}
		UserDaoImpl ud = new UserDaoImpl();
		int i = ud.insert(user);
		if (i != 0) {
			System.out.println("注册成功!");
		} else {
			System.out.println("注册失败!");
		}
	}

	@Override
	public User check() {
	    User user = new User();
	    Scanner input = new Scanner(System.in);
	    System.out.print("请输入类型1系统管理员的姓名：");
	    userName = input.next();
	    user.setUserName(userName);
	    System.out.print("请输入其密码：");
	    password = input.next();
	    user.setPassword(password);
	    
	    UserDaoImpl udi = new UserDaoImpl();
	    return udi.checkDao(user);
	}

}
