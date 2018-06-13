package com.tarena.dao;


import com.tarena.entity.UserTest;

public interface UserTestMapper {
	UserTest findByUserName(String userName);
}
