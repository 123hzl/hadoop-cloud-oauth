package com.hzl.hadoop.oauth2.security;

import com.hzl.hadoop.app.entity.SysUser;
import com.hzl.hadoop.app.service.MyUserDetailsService;
import com.hzl.hadoop.oauth2.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * description
 * 自定义退出逻辑
 *
 * @author hzl 2022/06/08 10:39 AM
 */
@Slf4j
@Component
public class CustomLogoutHandler implements LogoutHandler {

	@Autowired
	MyUserDetailsService myUserDetailsService;
	/**
	 * 注入我们自己定义的用户信息获取对象
	 */
	@Autowired
	private UserServiceImpl userService;

	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		String userName = authentication.getName();
		SysUser userInfo = myUserDetailsService.selectUserByUserName(userName);
		log.info("username: {}  is offline now", userInfo.toString());

		//删除token

	}
}
