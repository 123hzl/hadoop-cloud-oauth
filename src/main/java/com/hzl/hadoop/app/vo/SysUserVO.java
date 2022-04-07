package com.hzl.hadoop.app.vo;

import lombok.*;

import java.time.LocalDateTime;

/**
 * description
 *
 * @author hzl 2021/09/09 9:21 PM
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SysUserVO {


	private Long id;

	private String username;

	private String password;
	/*
	 * 电话号码
	 * */
	private String phone;

	private LocalDateTime creationDate;

	private Long createdBy;

	private LocalDateTime lastUpdateDate;

	private Long lastUpdatedBy;

	private Long objectVersionNumber;

	private Long tenantId;

}
