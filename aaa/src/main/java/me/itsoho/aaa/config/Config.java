package me.itsoho.aaa.config;

import javax.sql.DataSource;

import me.itsoho.rbac.util.PermissionDetect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Configuration
public class Config {
	
	@Bean
	@ConfigurationProperties(prefix="datasource.druid")
    public DataSource createDruidDatasource(){
    	return new com.alibaba.druid.pool.DruidDataSource();
    }
	
	@Bean
	@Autowired
	PermissionDetect permissionDetect(@Qualifier("requestMappingHandlerMapping")RequestMappingHandlerMapping handlerMapping,
			WebApplicationContext webApplicationContext){
		//TODO:add license control
		String[] ss = {"me.itsoho.rbac.controller"};
		return PermissionDetect.getPermissionDetect(handlerMapping, webApplicationContext,ss);
	}
}
