package cn.org.angry.dao.impl;


import cn.org.angry.dao.UserDao;
import cn.org.angry.entity.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import cn.org.angry.util.DBUtil;

public class UserDaoImpl implements UserDao
{
    @Override
    public int addUser(User user) {
        String sql = "insert into users (username, password, realname, gender, phone, email) values(?, ?, ?, ?, ?, ?)";
        return DBUtil.getInstance().update(sql,
                user.getUsername(),user.getPassword(),user.getRealname(),user.getGender(),user.getPhone(),user.getEmail());
    }

    @Override
    public int updateUser(User user) {
        String sql = "update users set password=?,realname=?,gender=?,icon=?,phone=?,email=? where id=?";
        return DBUtil.getInstance().update(sql,
                user.getPassword(),user.getRealname(),user.getGender(),user.getIcon(),user.getPhone(),user.getEmail(),user.getId());
    }

    @Override
    public int deleteUser(int uid) {
        // 文章、评论表和用户表关联有外键，只删除用户或许会报错，暂时搁置，后续更改
        String sql = "delete from users where id=?";
        return DBUtil.getInstance().update(sql,uid);
    }

    @Override
    public User queryUserById(int uid) {
        String sql = "select * from users where id=?";
        try {
            return DBUtil.getInstance().queryForObject(sql, new BeanPropertyRowMapper<>(User.class), uid);
        } catch (DataAccessException e){
            return null;
        }
    }

    @Override
    public User queryUserByUsername(String username) {
        String sql = "select * from users where username=?";
        try {
            return DBUtil.getInstance().queryForObject(sql, new BeanPropertyRowMapper<>(User.class), username);
        } catch (DataAccessException e){
            return null;
        }

    }
}
