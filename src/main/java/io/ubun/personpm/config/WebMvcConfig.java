package io.ubun.personpm.config;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.ubun.personpm.utils.ProjectDTOModelMapper;

@EnableWebMvc
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	private final ApplicationContext applicationContext;
	private final EntityManager entityManager;

	@Autowired
	public WebMvcConfig(ApplicationContext applicationContext, EntityManager entityManager) {
		this.applicationContext = applicationContext;
		this.entityManager = entityManager;
	}
	
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		WebMvcConfigurer.super.addArgumentResolvers(resolvers);
		List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
		converters.add(new MappingJackson2HttpMessageConverter());
		resolvers.add(new ProjectDTOModelMapper(converters, entityManager));
	}
}
