package cn.org.angry.service.impl;

import cn.org.angry.dao.UserDao;
import cn.org.angry.dao.impl.UserDaoImpl;
import cn.org.angry.entity.Result;
import cn.org.angry.entity.User;
import cn.org.angry.service.UserService;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();
    @Override
    public Result login(User user) {
        Result result = new Result();
        User u = userDao.queryUserByUsername(user.getUsername());
        if(u!=null){
            if(u.getUsername().equals(user.getUsername()) && u.getPassword().equals(user.getPassword())){
                // 避免密码泄露
                u.setPassword(null);
                result.setOk(true);
                result.setMessage("登陆完成！");
                return result;
            }
        }
        result.setOk(false);
        result.setMessage("用户名或密码有误！请重试！");
        return result;
    }

    @Override
    public Result register(User user) {
        Result result = new Result();
        // 判断用户名是否已经存在
        if(!checkUsernameIsExists(user.getUsername())){
            if(userDao.addUser(user) > 0){
                result.setOk(true);
                result.setMessage("注册完成！");
                return result;
            }
        }
        result.setOk(false);
        result.setMessage("账号已存在！");
        return result;
    }

    @Override
    public boolean checkUsernameIsExists(String username) {
        return userDao.queryUserByUsername(username)!=null;
    }
}
