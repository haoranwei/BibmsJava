package cn.service.impl;

import java.util.Scanner;

import cn.dao.impl.RoleDaoImpl;
import cn.entity.Role;
import cn.service.RoleService;

public class RoleServciceImpl implements RoleService {
	private String roleNo;
	private String roleName;
	Scanner input = new Scanner(System.in);

	@Override
	public void Add() {
		Role role = new Role();

		System.out.println("将进行新增角色程序...");
		System.out.print("请输入新增管理员类型：1.系统管理员;2.营业部职员;3.营业部经理;");
		roleNo = input.next();
		System.out.print("请输入所选角色真实姓名：");
		roleName = input.next();

		role.setRoleNo(roleNo);
		role.setRoleName(roleName);

		RoleDaoImpl rdi = new RoleDaoImpl();
		int i = rdi.insert(role);
		if (i != 0) {
			System.out.println("注册成功!");
		} else {
			System.out.println("注册失败!");
		}
	}

	@Override
	public void Search() {
		Role role = new Role();

		System.out.println("将进行查询角色程序...");
		System.out.print("请输入管理员类型以查得其姓名：");
		roleNo = input.next();
		role.setRoleNo(roleNo);

		RoleDaoImpl rdi = new RoleDaoImpl();
		role = rdi.select(role);
		System.out.println("查询结果为："+role.getRoleName());

	}

	@Override
	public void Modify() {
		Role role = new Role();

		System.out.println("将进行修改角色程序...");
		System.out.print("请输入管理员姓名以修改其类型：");
		roleName = input.next();
		role.setRoleName(roleName);
		System.out.println("输入修改后得类型：：1.系统管理员;2.营业部职员;3.营业部经理;");
		roleNo = input.next();
		role.setRoleNo(roleNo);

		RoleDaoImpl rdi = new RoleDaoImpl();
		int i = rdi.update(role);
		if (i != 0) {
			System.out.println("注册成功!");
		} else {
			System.out.println("注册失败!");
		}
	}

}
