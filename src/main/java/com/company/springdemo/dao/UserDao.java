package com.company.springdemo.dao;

import com.company.springdemo.model.UserDomain;

import java.util.List;

public interface UserDao {

    int insert(UserDomain record);

    List<UserDomain> selectUsers();

}
