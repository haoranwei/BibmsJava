package cn.dao;

import java.util.List;

import cn.entity.Role;

public interface RoleDao {
    int insert(Role role);

    int delete(int roleId);
    // 可以直接删除键值，不必封装到RoleDaoImpl

    int update(Role role);
    
    Role select(Role role);
//    public Role getRoleById(int roleID);
}