package com.hzl.hadoop.app.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hzl.hadoop.config.mybatis.BaseEntity;
import lombok.*;

/**
 * description
 * 不用oauth2的SysUser，便于扩展，减少相互直接的影响
 * @author hzl 2021/09/09 5:11 PM
 */
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName("sys_user")
public class SysUser extends BaseEntity {

	@TableId
	private Long id;

	private String username;

	private String password;

	private String phone;


	/**
	 * 头像
	 */
	private String avatar;

}
