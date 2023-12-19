package com.hzl.hadoop.config.utils;

import com.hzl.hadoop.oauth2.entity.SysUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

/**
 * description
 * 获取当前登录的用户信息
 *
 * @author hzl 2022/06/17 12:46 PM
 */
public class DetailHepler {


	public static SysUser getUserDetails() {
		if (SecurityContextHolder.getContext() != null && SecurityContextHolder.getContext().getAuthentication() != null) {
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (principal instanceof SysUser) {
				return (SysUser) principal;
			} else {
				Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();
				if (details instanceof OAuth2AuthenticationDetails) {
					Object decodedDetails = ((OAuth2AuthenticationDetails) details).getDecodedDetails();
					if (decodedDetails instanceof SysUser) {
						return (SysUser) decodedDetails;
					}
				}

				return null;
			}
		} else {
			return null;
		}
	}
}
