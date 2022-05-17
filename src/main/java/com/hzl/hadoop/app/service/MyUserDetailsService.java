package com.hzl.hadoop.app.service;

import com.hzl.hadoop.app.entity.SysUser;
import com.hzl.hadoop.app.vo.RecoveredPasswordVO;
import com.hzl.hadoop.app.vo.SysUserVO;

/**
 * description
 *
 * @author hzl 2021/09/09 5:06 PM
 */
public interface MyUserDetailsService {

	SysUser selectUser(SysUser sysUser);

	SysUser selectUserByUserName(String username);

	Boolean register(SysUserVO sysUserVO);

	Boolean recoveredPassword(RecoveredPasswordVO recoveredPasswordVO);
}
