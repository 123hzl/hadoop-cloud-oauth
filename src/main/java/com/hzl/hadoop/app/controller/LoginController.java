package com.hzl.hadoop.app.controller;


import com.hzl.hadoop.app.service.MyUserDetailsService;
import com.hzl.hadoop.app.vo.SysUserVO;
import com.hzl.hadoop.oauth2.security.WebSecurityConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
