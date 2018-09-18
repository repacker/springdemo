package com.company.springdemo.dao;

import com.company.springdemo.model.UserDomain;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface UserDao {

    int insert(UserDomain record);

    List<UserDomain> selectUsers();

    boolean deleteById(int userId);

}
