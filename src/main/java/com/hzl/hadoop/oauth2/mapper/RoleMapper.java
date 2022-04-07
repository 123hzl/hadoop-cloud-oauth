package com.hzl.hadoop.oauth2.mapper;

import com.hzl.hadoop.oauth2.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * description
 *
 * @author hzl 2021/12/20 1:23 PM
 */
@Mapper
public interface RoleMapper {
	@Select("select r.id,r.role_name roleName ,r.role_desc roleDesc " +
			"FROM sys_role r,sys_user_role ur " +
			"WHERE r.id=ur.rid AND ur.uid=#{uid}")
	public List<SysRole> findByUid(@Param("uid") Long uid);

}
