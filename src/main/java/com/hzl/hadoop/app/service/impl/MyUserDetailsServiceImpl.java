package com.hzl.hadoop.app.service.impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hzl.hadoop.app.entity.SysUser;
import com.hzl.hadoop.app.mapper.SysUserMapper;
import com.hzl.hadoop.app.service.MyUserDetailsService;
import com.hzl.hadoop.app.vo.RecoveredPasswordVO;
import com.hzl.hadoop.app.vo.SysUserVO;
import com.hzl.hadoop.config.exception.CommonException;
import com.hzl.hadoop.config.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * description
 *
 * @author hzl 2021/09/09 5:10 PM
 */
@Service
public class MyUserDetailsServiceImpl implements MyUserDetailsService {


	private SysUserMapper sysUserMapper;

	private PasswordEncoder passwordEncoder;

	public MyUserDetailsServiceImpl(SysUserMapper sysUserMapper, PasswordEncoder passwordEncoder) {
		this.sysUserMapper = sysUserMapper;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public SysUser selectUser(SysUser sysUser) {
		Wrapper<SysUser> queryWrapper = new QueryWrapper<>(sysUser);
		return sysUserMapper.selectOne(queryWrapper);
	}

	@Override
	public SysUser selectUserByUserName(String username) {
		SysUser sysUser = new SysUser();
		sysUser.setUsername(username);
		Wrapper<SysUser> queryWrapper = new QueryWrapper<>(sysUser);
		return sysUserMapper.selectOne(queryWrapper);
	}

	@Override
	public Boolean register(SysUserVO sysUserVO) {
		//用户名，密码为空校验，不能重复注册
		validateUser(sysUserVO);
		String password = sysUserVO.getPassword();
		sysUserVO.setPassword(passwordEncoder.encode(password));
		int i = sysUserMapper.insert(JsonUtils.cloneObject(sysUserVO, SysUser.class));
		if (i > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Boolean recoveredPassword(RecoveredPasswordVO recoveredPasswordVO) {

		return null;
	}


	public void validateUser(SysUserVO sysUserV) {
		if (StringUtils.isBlank(sysUserV.getPassword())) {
			throw new CommonException("密码不能为空");
		}
		if (StringUtils.isBlank(sysUserV.getUsername())) {
			throw new CommonException("用户名不能为空");
		}

		if (StringUtils.isBlank(sysUserV.getPhone())) {
			throw new CommonException("手机号码不能为空");
		}

		SysUser sysUser = this.selectUserByUserName(sysUserV.getUsername());
		if (sysUser != null) {
			throw new CommonException("该用户名已存在");
		}


	}
}
