package com.hzl.hadoop.oauth2.entity;

import com.hzl.hadoop.config.mybatis.BaseEntity;

/**
 * description
 *
 * @author hzl 2021/12/20 3:31 PM
 */
public class SysUserRole extends BaseEntity {

	private Long id;

	private Long uid;

	private Long rid;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public Long getRid() {
		return rid;
	}

	public void setRid(Long rid) {
		this.rid = rid;
	}
}
