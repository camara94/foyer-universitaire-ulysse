package tn.uud.ulysse.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration 
public class MvcConfig extends WebMvcConfigurerAdapter { 
	
	@Bean 
	public ViewResolver getViewResolver() { 
		InternalResourceViewResolver resolver = new InternalResourceViewResolver(); 
		resolver.setPrefix("/WEB-INF/views/"); 
		resolver.setSuffix(".html"); 
		resolver.setSuffix(".jpg"); 
		resolver.setSuffix(".png"); 
		resolver.setSuffix(".jpeg"); 
		resolver.setSuffix(".giff");
		return resolver; 
	} 
	
	@Override 
	public void configureDefaultServletHandling( DefaultServletHandlerConfigurer configurer) { 
		configurer.enable(); 
	} 
	
	@Override 
	public void addResourceHandlers(ResourceHandlerRegistry registry) { 
		registry.addResourceHandler("/resource/**").addResourceLocations("WEB-INF/resources/"); 
	} 
}