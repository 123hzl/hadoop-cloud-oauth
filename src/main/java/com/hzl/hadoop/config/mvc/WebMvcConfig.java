package com.hzl.hadoop.config.mvc;

import ch.qos.logback.classic.pattern.DateConverter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * description
 * 和WebMvcConfigurerAdapter对比
 * 启动该配置需要在启动类上添加@EnableWebMvc注解
 * 参考资料：https://www.cnblogs.com/hellxz/p/8735602.html
 * https://blog.csdn.net/u010246789/article/details/52539576
 *
 * @author hzl 2020/01/03 11:25 AM
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

	/**
	 * <p>
	 * 配置拦截器
	 * </p>
	 *
	 * @author hzl 2020/01/03 11:33 AM
	 */
	@Override
	protected void addInterceptors(InterceptorRegistry registry) {
	}

	/**
	 * <p>
	 * 配置跨域路径映射
	 * </p>
	 *
	 * @author hzl 2020/01/03 11:34 AM
	 */
	@Override
	protected void addCorsMappings(CorsRegistry registry) {
	}

	/**
	 * <p>
	 * 配置格式化器
	 * </p>
	 *
	 * @author hzl 2020/01/03 11:34 AM
	 */
	@Override
	protected void addFormatters(FormatterRegistry registry) {
//		if (true) {
//			registry.addConverter(new DateConverter());
//			registry.addConverter(new LocalDateConverter());
//		}
//		super.addFormatters(registry);
	}

	/**
	 * <p>
	 * 配置视图控制器映射
	 * </p>
	 *
	 * @author hzl 2020/01/03 12:37 PM
	 */
	@Override
	protected void addViewControllers(ViewControllerRegistry registry) {
	}

	/**
	 * <p>
	 * 自定义消息转换器,针对springmvc
	 * </p>
	 *
	 * @author hzl 2020/01/03 3:02 PM
	 */
	@Override
	protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		super.configureMessageConverters(converters);
		//1.需要定义一个convert转换消息的对象;
		FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
		//2.添加fastJson的配置信息，比如：是否要格式化返回的json数据;
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat,
				//数值字段如果为null,输出为0,而非null
				SerializerFeature.WriteNullNumberAsZero,
				//List字段如果为null,输出为[],而非null
				SerializerFeature.WriteNullListAsEmpty,
				//字符类型字段如果为null,输出为"",而非null
				SerializerFeature.WriteNullStringAsEmpty,
				//Boolean字段如果为null,输出为falseJ,而非null
				SerializerFeature.WriteNullBooleanAsFalse,
				//消除对同一对象循环引用的问题，默认为false（如果不配置有可能会进入死循环）
				SerializerFeature.DisableCircularReferenceDetect,
				//是否输出值为null的字段,默认为false。设置后为null的字段会输出
				SerializerFeature.WriteMapNullValue,
				//对斜杠’/’进行转义
				SerializerFeature.WriteSlashAsSpecial
				//将对象转为array输出
				//SerializerFeature.BeanToArray
				);

		//3处理中文乱码问题
		List<MediaType> fastMediaTypes = new ArrayList<>();
		fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);

		//4.在convert中添加配置信息.
		fastJsonHttpMessageConverter.setSupportedMediaTypes(fastMediaTypes);
		fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);

		//5.将convert添加到converters当中.
		converters.add(fastJsonHttpMessageConverter);
	}
}