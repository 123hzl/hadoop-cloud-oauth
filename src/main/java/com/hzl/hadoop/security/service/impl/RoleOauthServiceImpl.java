package com.hzl.hadoop.security.service.impl;

import com.hzl.hadoop.security.service.RoleOauthService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

/**
 * description
 *
 * @author hzl 2021/09/10 11:53 AM
 */
@Component("roleOauthService")
@ConditionalOnProperty(prefix = "httpbasic", name = "isOpen", havingValue = "true", matchIfMissing = false)
public class RoleOauthServiceImpl implements RoleOauthService {

	private AntPathMatcher antPathMatcher = new AntPathMatcher();

	@Override
	public Boolean hasPermission(HttpServletRequest request, Authentication authentication) {
	/*	Object principal = authentication.getPrincipal();
		boolean hasPermission = false;
		if (principal instanceof UserDetails) { //首先判断先当前用户是否是我们UserDetails对象。
			String userName = ((UserDetails) principal).getUsername();
			Set<String> urls = new HashSet<>(); // 数据库读取 //读取用户所拥有权限的所有URL
			urls.add("/query/pinyin");
			// 注意这里不能用equal来判断，因为有些URL是有参数的，所以要用AntPathMatcher来比较
			for (String url : urls) {
				if (antPathMatcher.match(url, request.getRequestURI())) {
					hasPermission = true;
					break;
				}
			}
		}
		return hasPermission;*/
	//以上代码暂时注释，等后期url表设计，和自动把url注册进表再放开
		return true;
	}
}