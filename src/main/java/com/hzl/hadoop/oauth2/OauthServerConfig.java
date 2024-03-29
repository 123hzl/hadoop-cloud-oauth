package com.hzl.hadoop.oauth2;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.hzl.hadoop.oauth2.security.WebSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.JdbcApprovalStore;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

/**
 * description
 * oauth2授权
 *
 * @author hzl 2021/12/20 1:52 PM
 */
@Configuration
@EnableAuthorizationServer
@AutoConfigureAfter({WebSecurityConfig.class})
public class OauthServerConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	@Qualifier("hadoopUserService")
	private UserDetailsService userDetailsService;

	//token保存策略
	@Bean
	public TokenStore tokenStore() {
		return new JdbcTokenStore(dataSource);
	}

	@Bean
	public ApprovalStore approvalStore() {
		return new JdbcApprovalStore(dataSource);
	}

	//授权码模式专用对象
	@Bean
	public AuthorizationCodeServices authorizationCodeServices() {
		return new JdbcAuthorizationCodeServices(dataSource);
	}

	@Autowired
	@Qualifier("authorizationCodeServices")
	private AuthorizationCodeServices authorizationCodeServices;

	@Autowired
	@Qualifier("tokenStore")
	private TokenStore tokenStore;

	@Autowired
	private ApprovalStore approvalStore;

	//从数据库中查询出客户端信息
	@Bean
	public JdbcClientDetailsService clientDetailsService() {
		JdbcClientDetailsService jdbcClientDetailsService = new JdbcClientDetailsService(dataSource);
		jdbcClientDetailsService.setPasswordEncoder(passwordEncoder);
		return jdbcClientDetailsService;
	}


	//授权信息保存策略



	//指定客户端登录信息来源
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		//从数据库取数据
		clients.withClientDetails(clientDetailsService());

		// 从内存中取数据
//        clients.inMemory()
//                .withClient("baidu")
//                .secret(passwordEncoder.encode("12345"))
//                .resourceIds("product_api")
//                .authorizedGrantTypes(
//                        "authorization_code",
//                        "password",
//                        "client_credentials",
//                        "implicit",
//                        "refresh_token"
//                )// 该client允许的授权类型 authorization_code,password,refresh_token,implicit,client_credentials
//                .scopes("read", "write")// 允许的授权范围
//                .autoApprove(false)
//                //加上验证回调地址
//                .redirectUris("http://www.baidu.com");
	}

	//检测token的策略
	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		oauthServer.allowFormAuthenticationForClients()    //允许form表单客户端认证,允许客户端使用client_id和client_secret获取token
				.checkTokenAccess("isAuthenticated()")     //通过验证返回token信息
				.tokenKeyAccess("permitAll()")        // 获取token请求不进行拦截
				.passwordEncoder(passwordEncoder);

	}

	//OAuth2的主配置信息
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints
				.approvalStore(approvalStore)
				.authenticationManager(authenticationManager)
				.authorizationCodeServices(authorizationCodeServices)
				.tokenStore(tokenStore)
				.userDetailsService(userDetailsService);
	}
}
