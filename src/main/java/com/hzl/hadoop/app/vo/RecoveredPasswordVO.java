package com.hzl.hadoop.app.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * description
 * 找回密码实体类
 * @author hzl 2022/05/17 9:54 AM
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecoveredPasswordVO {

	/**
	 * 用户名
	 */
	private String username;

	/**
	 * 手机号
	 */
	@NotNull(message = "手机号不为空")
	private String phone;

	/**
	 * 新的用户密码
	 */
	@NotNull(message = "密码不为空")
	private String password;

	/**
	 * 验证码
	 */
	private String indentifyCode;


}
