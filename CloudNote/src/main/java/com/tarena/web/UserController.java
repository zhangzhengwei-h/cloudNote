package com.tarena.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tarena.common.Md5Util;
import com.tarena.entity.Result;
import com.tarena.entity.User;
import com.tarena.entity.UserTest;
import com.tarena.service.UserService;
import com.tarena.service.UserTestService;

@Controller
@RequestMapping("/user")
public class UserController {
	private static Logger log = Logger
			.getLogger(UserController.class.getName());

	@Resource
	private UserService userService;

	@Resource
	private UserTestService testService;
	
	/**
	 * 测试
	 */
	@RequestMapping("/test.do")
	@ResponseBody
	public Result test(String userName, String password){
		Map<String,Object> data = new HashMap<String,Object>();
		UserTest userTest = testService.findUserByUserName(userName);
		data.put("user", userTest);
		log.info(userTest);
		return new Result(data);
	}
	/**
	 * 注册用户
	 */
	@RequestMapping("/register.do")
	@ResponseBody
	public Result register(User user) {
		log.info(user.getCn_user_name() + "		正在注册新用户");
		boolean b = userService.createUser(user);
		log.info(user.getCn_user_name() + " 	注册成功");
		return new Result(b);
	}

	@RequestMapping("/login.do")
	@ResponseBody
	public Result login(String userName, String password, HttpSession session) {
		Map<String, Object> data = userService.checkUser(userName, password);
		if ("0".equals(data.get("flag").toString())) {
			log.info(userName + "	正在登录！");
			User user = userService.findUser(userName);
			data.put("user", user);
			log.info(user);
			log.info(userName + "	登录成功！");
			session.setAttribute("user", user);
		}
		return new Result(data);
	}

	@RequestMapping("/logout.do")
	@ResponseBody
	public Result logout(HttpSession session) {
		// 注销session
		session.invalidate();
		return new Result();
	}

	@RequestMapping("/changePassword.do")
	@ResponseBody
	public Result changePassword(String lastPassword, String newPassword,
			HttpSession session) {
		User user = (User) session.getAttribute("user");
		log.info(user.getCn_user_name() + "	正在修改密码！");
		if (user.getCn_user_password().equals(Md5Util.md5(lastPassword))) {
			user.setCn_user_password(Md5Util.md5(newPassword));
			userService.update(user);
			log.info(user.getCn_user_name() + "	密码修改成功！");
			return new Result("修改成功.");
		} else {
			log.info(user.getCn_user_name() + "	原密码输入有误！");
			return new Result("原密码输入有误.");
		}
	}
}
