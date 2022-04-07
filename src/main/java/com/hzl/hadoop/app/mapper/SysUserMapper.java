package com.hzl.hadoop.app.mapper;

import com.hzl.hadoop.app.entity.SysUser;
import com.hzl.hadoop.config.mybatis.BaseMapperUtil;
import org.apache.ibatis.annotations.Mapper;

/**
 * description
 *
 * @author hzl 2021/09/09 5:15 PM
 */
@Mapper
public interface SysUserMapper extends BaseMapperUtil<SysUser> {

	SysUser selectUser(SysUser sysUser);
}
