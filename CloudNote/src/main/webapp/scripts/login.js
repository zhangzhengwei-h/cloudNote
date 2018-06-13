/**
 * 页面初始化后，绑定函数。
 */
$(function(){
	//记住账号
	$("#count").val(getCookie("userName"));
	
	//注册
	$("#regist_button").click(function(){
		register();
	});
	
	//登录
	$("#login").click(function(){
		login();
	});
	
	//登出
	$("#logout").click(function(){
		logout();
	});
	
	//修改密码
	$("#changePassword").click(function(){
		changepwd();
	})
	
	//测试
	$("#test").click(function(){
		test();
	})
	
});

//注册
function register() {
	// 取值
	var userName = $("#regist_username").val();
	var password = $("#regist_password").val();
	var password2 = $("#final_password").val();
	var nickname = $("#nickname").val();
	
	// 校验用户名格式
	var reg = /^\w{3,20}$/;
	if(!reg.test(userName)) {
		$("#warning_1").text("3-20位的英文、数字、下划线.").show();
		return;
	} else {
		$("#warning_1").hide();
	}
	//校验密码
	if(password.length<6) {
		$("#warning_2").text("密码长度不能小于6位.").show();
		return;
	} else {
		$("#warning_2").hide();
	}
	//校验重复密码
	if(password != password2) {
		$("#warning_3").text("2次输入的密码不一致.").show();
		return;
	} else {
		$("#warning_3").hide();
	}
	
	//进行注册
	var user = {
		"cn_user_name":userName,
		"cn_user_password":password,
		"cn_user_desc":nickname
	};
	$.ajax({
		type:"post",
		url:basePath+"user/register.do",
		dataType:"json",//
		data:user,//发送的数据
		success:function(result) {
			if(result.status==0) {
				if(result.data) {
					alert("注册成功.");
					//$("#zc").attr("class","sig sig_out");
					//$("#dl").attr("class","log log_in");
					$("#back").trigger("click");
				} else {
					$("#warning_1").text("用户名已存在.").show();
				}
			} else {
				alert(result.message);
			}
		},
		error:function(xhr,status,error) {
			alert("请求失败.");
		}
	});
}

//登陆
function login() {
	var count = $("#count").val();
	var password = $("#password").val();
	if(count=="" || password=="") {
	  alert("用户名和密码不能为空！")
		return;
	}
	$.ajax({
		type:"post",
		url:basePath+"user/login.do",
		dataType:"json",
		data:{"userName":count,"password":password},
		success:function(result) {
			if(result.status==0) {
				if(result.data.flag==0) {
					//登陆成功，进入系统
					location.href="edit.html";
					addCookie("userId",result.data.user.cn_user_id,5);
					addCookie("userName",result.data.user.cn_user_name,5);
				} else {
					alert(result.data.msg);
				}
			} else {
				alert(result.message);
			}
		},
		error:function(xhr,status,error) {
			alert("请求失败.");
		}
	});
}

/**
 * 退出登录
 */
function logout(){
	//注销登陆信息
	$.ajax({
		type:"post",
		url:basePath+"user/logout.do",
		dataType:"json",
		data:{},
		success:function(result) {
			if(result.status==0) {
				//退回到登陆页
				location.href="login.html";
			} else {
				alert(result.message);
			}
		},
		error:function(xhr,status,error) {
			alert("请求失败.");
		}
	});
}

/**
 * 修改密码
 */
function changepwd(){
	var newPassword = $("#new_password").val();
	var finalPassword = $("#final_password").val();
	if(newPassword.length < 6) {
		alert("密码长度不能小于6位.");
		return;
	} else if(newPassword != finalPassword) {
		alert("两次输入的新密码不一致.");
		return;
	}
	
	var lastPassword = $("#last_password").val();
	$.ajax({
		type:"post",
		url:basePath+"user/changePassword.do",
		dataType:"json",
		data:{"lastPassword":lastPassword,"newPassword":newPassword},
		success:function(result) {
			if(result.status==0) {
				alert(result.data);
				logout();
			} else {
				alert(result.message);
			}
		},
		error:function(xhr,status,error) {
			alert("请求失败.");
		}
	});
}


//测试
function test() {
	var count = $("#count").val();
	var password = $("#password").val();
	if(count=="" || password=="") {
	  alert("用户名和密码不能为空！")
		return;
	}
	$.ajax({
		type:"post",
		url:basePath+"user/test.do",
		dataType:"json",
		data:{"userName":count,"password":password},
		success:function(result) {
			location.href="test.html";
			alert(result.data.user.cn_user_id);
			alert(result.data.user.cn_user_name);
		},
		error:function(xhr,status,error) {
			alert("请求失败.");
		}
	});
}

