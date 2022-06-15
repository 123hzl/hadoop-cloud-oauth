package com.hzl.hadoop.oauth2.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.sql.DataSource;

/**
 * description
 *
 * @author hzl 2021/12/20 1:50 PM
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


	@Autowired
	private DataSource dataSource;

	@Autowired
	@Qualifier("myAuthenticationSucessHandler")
	private AuthenticationSuccessHandler myAuthenticationSuccessHandler;
	@Autowired
	@Qualifier("myAuthenticationFailureHandler")
	private AuthenticationFailureHandler myAuthenticationFailHander;

	@Autowired
	private CustomLogoutHandler customLogoutHandler;


	/**
	 * 指定token的持久化策略
	 * 其下有   RedisTokenStore保存到redis中，
	 * JdbcTokenStore保存到数据库中，
	 * InMemoryTokenStore保存到内存中等实现类，
	 * 这里我们选择保存在数据库中
	 *
	 * @return
	 */
	@Bean
	public TokenStore jdbcTokenStore() {
		return new JdbcTokenStore(dataSource);
	}


	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		// 1允许任何域名使用
		corsConfiguration.addAllowedOriginPattern("*");
		// 2允许任何头
		corsConfiguration.addAllowedHeader("*");
		// 3允许任何方法（post、get等）
		corsConfiguration.addAllowedMethod("*");

		corsConfiguration.setAllowCredentials(true);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfiguration);
		return source;
	}


	/**
	 * 注入我们自己的AuthenticationProvider
	 */
	@Autowired
	@Qualifier("myAuthenticationProvider")
	private AuthenticationProvider provider;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				//配置跨域 关闭csrf防护
				.cors().configurationSource(corsConfigurationSource()).and()
				.csrf().disable()
				.headers().frameOptions().disable()
				.and();
		http
				//登录处理
				.formLogin() //表单方式，或httpBasic
				//.loginPage("/login")
				.loginProcessingUrl("/api/login/account1")
				.successHandler(myAuthenticationSuccessHandler)
				.failureHandler(myAuthenticationFailHander)
				.permitAll()
				.and();
		http.logout()   //退出登录相关配置
				//自定义退出登录页面
				.logoutUrl("/logout")
				//退出成功后跳转的页面
				.logoutSuccessUrl("/index")
				.addLogoutHandler(customLogoutHandler)
				.logoutSuccessHandler(new CustomLogoutSuccessHandler())
				//退出时要删除的Cookies的名字
				.deleteCookies("JSESSIONID");
		http
				.authorizeRequests() // 授权配置
				//无需权限访问
				.antMatchers("/js/**", "/error404", "/register", "/druid/**","/password/authCode","/recovered/password").permitAll()
				.antMatchers("/oauth/**").authenticated()
				//必须经过认证以后才能访问
				.anyRequest().access("@roleOauthService.hasPermission(request,authentication)");


		//不拦截 oauth 开放的资源
//		http.csrf().disable();
//
//		http.requestMatchers()//使HttpSecurity接收以"/login/","/oauth/"开头请求。
//				.antMatchers("/oauth/**", "/login/**", "/logout/**")
//				.and()
//				.authorizeRequests()
//				.antMatchers("/oauth/**").authenticated()
//				.and()
//				.formLogin()
//				.loginPage("/login")
//				.defaultSuccessUrl("/confirm").and()
//				.logout().deleteCookies("JSESSIONID");


	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) {
		auth.authenticationProvider(provider);
	}


	/**
	 * AuthenticationManager对象在OAuth2认证服务中要使用，提前放入IOC容器中
	 */
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

}
