package com.tarena.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tarena.common.BusinessException;
import com.tarena.dao.UserTestMapper;
import com.tarena.entity.UserTest;

@Service
public class UserTestService {

	@Resource
	private UserTestMapper userTestMapper;
	
	/**
	 * 测试
	 */
	public UserTest findUserByUserName(String userName) {
		if(userName == null)
			throw new BusinessException("参数为空.");
		return userTestMapper.findByUserName(userName);
	}
}
