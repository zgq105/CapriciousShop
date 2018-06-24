package com.guoqiang.rxddataserver.dao;

import com.guoqiang.rxddataserver.entity.rxd.User;

import java.util.List;

public interface UserDao {
    int deleteByPrimaryKey(String id);

    int insert(User record);

    User selectByPrimaryKey(String id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);
}