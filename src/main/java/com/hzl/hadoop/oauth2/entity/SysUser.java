package com.hzl.hadoop.oauth2.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hzl.hadoop.config.mybatis.BaseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * description
 *
 * @author hzl 2021/12/20 1:21 PM
 */
public class SysUser extends BaseEntity implements UserDetails {

	private Long id;

	private String username;

	private String password;

	/**
	 * 头像url
	 */
	private String avatar;
	/**
	 * 电话号码
	 */
	private String phone;

	/**
	 * 用户名-真实姓名，可以为空
	 */
	private String realName;

	/**
	 * 租户id
	 * */
	private Long tenantId;

	/**
	 * 用户状态，1为登陆，0为离线
	 */
	private Integer status;

	/**
	 * 是否注销，true为已经注销，false为正常
	 */
	private Boolean deleted;




	private List<SysRole> roles = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<SysRole> getRoles() {
		return roles;
	}

	public void setRoles(List<SysRole> roles) {
		this.roles = roles;
	}

	@JsonIgnore
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isEnabled() {
		return true;
	}


	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	@Override
	public Long getTenantId() {
		return tenantId;
	}

	@Override
	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}
}
