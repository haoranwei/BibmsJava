package cn.dao;
import java.util.List;

//202134071125 韦浩然 2023/6/7/18：06
import cn.entity.*;

public interface UserDao {
	int insert(User user);
	User checkDao(User user);
}
