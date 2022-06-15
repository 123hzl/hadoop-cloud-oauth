package com.hzl.hadoop.app.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * description
 * 登陆成功，失败后返回
 * @author hzl 2022/06/14 5:56 PM
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginSuccessVO {

	/**
	 * 当前登陆的用户名字
	 */
	private String currentAuthority;

	/**
	* ok成功，error失败
	* */
	private String status;

}
