package com.hzl.hadoop.oauth2.service.impl;


import com.hzl.hadoop.interfacemanager.service.InterfaceManageService;
import com.hzl.hadoop.oauth2.service.RoleOauthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

/**
 * description
 *
 * @author hzl 2021/09/10 11:53 AM
 */
@Slf4j
@Component("roleOauthService")
public class RoleOauthServiceImpl implements RoleOauthService {

	private AntPathMatcher antPathMatcher = new AntPathMatcher();

	@Autowired
	private InterfaceManageService interfaceManageService;

	@Override
	public Boolean hasPermission(HttpServletRequest request, Authentication authentication) {
		Object principal = authentication.getPrincipal();
		boolean hasPermission = false;
		if (principal instanceof UserDetails) { //首先判断先当前用户是否是我们UserDetails对象。
			String userName = ((UserDetails) principal).getUsername();
			log.info("用户名{}",userName);
			// 数据库读取 //读取用户所拥有权限的所有URL
			Set<String> urls = interfaceManageService.selectUrls(null);

			// 注意这里不能用equal来判断，因为有些URL是有参数的，所以要用AntPathMatcher来比较
			for (String url : urls) {
				if (antPathMatcher.match(url, request.getRequestURI())) {
					hasPermission = true;
					break;
				}
			}
			//System.out.println("接口地址2"+request.getRequestURL());结果http://localhost:8888/error

		}
		return hasPermission;
	//以上代码暂时注释，等后期url表设计，和自动把url注册进表再放开
	}
}
