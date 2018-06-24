package com.guoqiang.rxddataserver.dao.impl;

import com.guoqiang.rxddataserver.dao.UserDao;
import com.guoqiang.rxddataserver.entity.rxd.User;

import java.util.List;

/**
 * @Auther: zgq
 * @Date: 2018/6/24 11:15
 * @Description:
 */
public class UserDaoImpl implements UserDao {
    @Override
    public int deleteByPrimaryKey(String id) {
        return 0;
    }

    @Override
    public int insert(User record) {
        return 0;
    }

    @Override
    public User selectByPrimaryKey(String id) {
        return null;
    }

    @Override
    public List<User> selectAll() {
        return null;
    }

    @Override
    public int updateByPrimaryKey(User record) {
        return 0;
    }
}
