package com.hzl.hadoop.oauth2.mapper;

import com.hzl.hadoop.oauth2.entity.SysUser;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * description
 *
 * @author hzl 2021/12/20 1:24 PM
 */
@Mapper
public interface UserMapper {
	@Select("select * from sys_user where username=#{username}")
	@Results({
			@Result(id = true, property = "id", column = "id",javaType = Long.class),
			@Result(property = "roles", column = "id", javaType = List.class,
					many = @Many(select = "com.hzl.hadoop.oauth2.mapper.RoleMapper.findByUid"))
	})
	SysUser findByUsername(@Param("username") String username);
}
