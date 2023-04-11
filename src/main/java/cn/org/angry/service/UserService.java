package cn.org.angry.service;


import cn.org.angry.entity.Result;
import cn.org.angry.entity.User;

/**
 * 用户业务接口
 */
public interface UserService {

    /**
     * 登陆
     * @param user
     * @return true表示登陆成功，false表示账号或密码有误
     */
    Result login(User user);

    /**
     * 注册
     * @param user
     * @return 注册结果
     */
    Result register(User user);

    /**
     * 检查用户名是否已存在
     * @param username
     * @return true表示存在，false表示不存在
     */
    boolean checkUsernameIsExists(String username);
}
