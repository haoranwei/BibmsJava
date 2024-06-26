package cn.dao;
import java.util.List;

import cn.entity.*;

public interface UserDao {
	int insert(User user);
	User checkDao(User user);
}
