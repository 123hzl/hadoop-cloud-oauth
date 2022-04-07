package com.hzl.hadoop.app.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hzl.hadoop.config.mybatis.BaseEntity;
import lombok.*;

/**
 * description
 *
 * @author hzl 2021/09/09 5:11 PM
 */
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName("xlweb_images")
public class SysUser extends BaseEntity {

	@TableId
	private Long id;

	private String username;

	private String password;

}
