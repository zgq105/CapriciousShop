package com.guoqiang.rxddataserver.controller;

import com.guoqiang.rxddataserver.entity.rxd.User;
import com.guoqiang.rxddataserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zgq
 * @Date: 2018/6/23 17:12
 * @Description:
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/getAll")
    public List<User> selectAll(){
        return userService.selectAll();
    }
}

