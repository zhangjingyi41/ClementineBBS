package cn.org.angry.dao;


import cn.org.angry.entity.User;

public interface UserDao {
    int addUser(User user);
    int updateUser(User user);
    int deleteUser(int uid);

    // 查
    User queryUserById(int uid);
    User queryUserByUsername(String username);
}
