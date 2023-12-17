package com.ootd.weatherlook.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ootd.weatherlook.interceptor.AuthInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Autowired
	private AuthInterceptor authInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(authInterceptor)
				.addPathPatterns("/**")
				.excludePathPatterns(
						"/login",
						"/loginform",
						"/memberjoin",
						"/memberinsert",
						"/idcheck",
						"/nickcheck",
						"/kakaologin",
						"/idSearchForm",
						"/idSearch",
						"/pwSearchForm",
						"/pwSearch",
						"/logOut",
						"/css/**",
						"/js/**",
						"/upload/**",
						"/assets/**",
						"/ico/**");
	}
}