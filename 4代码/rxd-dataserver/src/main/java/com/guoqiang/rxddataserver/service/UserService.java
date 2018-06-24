package com.guoqiang.rxddataserver.service;

import com.guoqiang.rxddataserver.dao.UserDao;
import com.guoqiang.rxddataserver.dao.impl.UserDaoImpl;
import com.guoqiang.rxddataserver.entity.rxd.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: zgq
 * @Date: 2018/6/24 11:18
 * @Description:
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public List<User> selectAll(){
        return userDao.selectAll();
    }





}
