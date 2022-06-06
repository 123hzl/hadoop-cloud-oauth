package com.hzl.hadoop.app.controller;


import com.hzl.hadoop.app.service.MyUserDetailsService;
import com.hzl.hadoop.app.vo.RecoveredPasswordVO;
import com.hzl.hadoop.app.vo.SysUserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * description
 * 登陆注册授权，刷新token接口
 *
 * @author hzl 2020/10/31 5:20 PM
 */
@Slf4j
@Controller
public class LoginController {

	@Autowired
	MyUserDetailsService myUserDetailsService;

	/**
	 * <p>
	 * 登陆页面
	 * </p>
	 *
	 * @author hzl 2020/01/08 12:41 PM
	 */
	@GetMapping(value = "/loginPage")
	public String login() {
		log.info("跳转登陆接口");
		return "login_page";
	}


	/**
	 * <p>
	 * 注册页面
	 * </p>
	 *
	 * @author hzl 2020/01/08 12:41 PM
	 */
	@PostMapping(value = "/register")
	public ResponseEntity<Boolean> register(@RequestBody SysUserVO sysUserVO) {

		return new ResponseEntity(myUserDetailsService.register(sysUserVO), HttpStatus.OK);
	}




	/**
	 * <p>
	 * 点击忘记密码后，输入手机号码，密码，确认密码后，点击获取验证码，然后输入验证码确认
	 * </p>
	 *
	 * @author hzl 2020/01/08 12:41 PM
	 */
	@GetMapping(value = "/password/authCode")
	public ResponseEntity<Boolean> authCodePassword(String phone) {

		return new ResponseEntity(myUserDetailsService.authCodePassword(phone), HttpStatus.OK);
	}


	/**
	 * <p>
	 * 忘记密码，发送短信或者邮件验证码，用户输入新密码和确认码进行修改密码。。验证码存入缓存并设置失效时间。
	 * </p>
	 *
	 * @author hzl 2020/01/08 12:41 PM
	 */
	@PostMapping(value = "/recovered/password")
	public ResponseEntity<Boolean> recoveredPassword(@Valid @RequestBody RecoveredPasswordVO recoveredPasswordVO) {

		return new ResponseEntity(myUserDetailsService.recoveredPassword(recoveredPasswordVO), HttpStatus.OK);
	}

	/**
	 * <p>
	 * 退出界面
	 * </p>
	 *
	 * @author hzl 2020/01/08 12:41 PM
	 */
	@GetMapping(value = "/login/out/{username}")
	public String loginout(@PathVariable("username") String userName) {
		return "loginout";
	}

	/**
	 * <p>
	 * 获取当前登陆人信息 todo
	 * </p>
	 *
	 * @author hzl 2020/01/08 12:41 PM
	 */
	@PostMapping(value = "/userinfo")
	public ResponseEntity<Boolean> getCurrentUserInfo(@RequestBody SysUserVO sysUserVO) {

		return new ResponseEntity(myUserDetailsService.register(sysUserVO), HttpStatus.OK);
	}


	/**
	 * <p>
	 * 获取token todo
	 * </p>
	 *
	 * @author hzl 2020/01/08 12:41 PM
	 */
	@PostMapping(value = "/token")
	public ResponseEntity<Boolean> getToken(@RequestBody SysUserVO sysUserVO) {

		return new ResponseEntity(myUserDetailsService.register(sysUserVO), HttpStatus.OK);
	}
}
