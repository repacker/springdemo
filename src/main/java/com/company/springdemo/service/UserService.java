package com.company.springdemo.service;

import com.company.springdemo.model.UserDomain;
import com.github.pagehelper.PageInfo;


public interface UserService {

    int addUser(UserDomain user);

    boolean deleteById(int userId);

    PageInfo<UserDomain> findAllUser(int pageNum, int pageSize);

    UserDomain selectUserByUserId(int userId);
}

