package com.hzl.hadoop;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


/**
 * description
 * 注册中心http://127.0.0.1:8848/nacos/index.html
 * 启动nacos命令：sh startup.sh -m standalone
 * 默认密码：nacos/nacos
 * druid的管理界面http://localhost:8080/druid/login.html
 * 账号密码：admin,admin
 * @author hzl 2019/12/27 3:44 PM
 * @EnableDiscoveryClient去掉后不使用注册中心
 */
@EnableScheduling
@SpringBootApplication(exclude = {DruidDataSourceAutoConfigure.class, DataSourceAutoConfiguration.class})
@EnableDiscoveryClient
@EnableWebMvc
public class OauthApplication {

	public static void main(String args[]) {
		SpringApplication.run(OauthApplication.class, args);
	}

}







