package com.src.main.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;

import com.src.main.service.viewresolver.ExcelViewResolver;

//@EnableWebMvc
@SuppressWarnings("deprecation")
public class WebConfig extends WebMvcConfigurerAdapter {
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer.defaultContentType(MediaType.APPLICATION_JSON).favorPathExtension(true);
	}

	@Bean
	public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager) {
		ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
		resolver.setContentNegotiationManager(manager);

		List<ViewResolver> resolvers = new ArrayList<>();

		resolvers.add(excelViewResolver());

		resolver.setViewResolvers(resolvers);
		return resolver;
	}

	@Bean
	public ViewResolver excelViewResolver() {
		return new ExcelViewResolver();
	}
}
