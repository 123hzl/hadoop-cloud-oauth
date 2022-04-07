package com.hzl.hadoop.oauth2.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hzl.hadoop.config.mybatis.BaseEntity;
import org.springframework.security.core.GrantedAuthority;

/**
 * description
 *
 * @author hzl 2021/12/20 1:22 PM
 */
public class SysRole extends BaseEntity implements GrantedAuthority {

	private Long id;
	private String roleName;
	private String roleDesc;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	//标记此属性不做json处理
	@JsonIgnore
	@Override
	public String getAuthority() {
		return roleName;
	}

}
