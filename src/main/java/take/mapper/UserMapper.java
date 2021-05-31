package take.mapper;

import take.bean.Nuser;
import take.bean.User;

import java.util.List;

/**
 * 功能描述：xml方式访问数据库
 *
 * @author EO
 * @date 2021/5/26 23:56
 */
public interface UserMapper {
    /**
     * 获取所有用户
     *
     * @return return
     */
    List<User> getUsers();

    void addUser(User user);

    List<Nuser> getNusers();
}
