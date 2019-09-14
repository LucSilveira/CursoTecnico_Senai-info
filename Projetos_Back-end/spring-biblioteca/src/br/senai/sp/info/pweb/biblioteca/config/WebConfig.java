package br.senai.sp.info.pweb.biblioteca.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import br.senai.sp.info.pweb.biblioteca.inteceptors.AutenticacaoSessaoInterceptor;

@Configuration
@ComponentScan("br.senai.sp.info.pweb.biblioteca")
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer{
	
	/*
	 * @Beand
	 * Ao chama este método o Spring injetará as dependencias no objeto retornado
	 */
	@Bean
	public AutenticacaoSessaoInterceptor getInteceptor() {
		return new AutenticacaoSessaoInterceptor();
	}
	
	/**
	 * Este metodo registro meus inteceptors no Spring
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(getInteceptor())
			.addPathPatterns("/**");
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		InternalResourceViewResolver resolver = new 	InternalResourceViewResolver();
		resolver.setViewClass(JstlView.class);
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		
		//Registrar a configuração
		registry.viewResolver(resolver);
	}
	

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/assets/**")
		.addResourceLocations("/assets/");
	}
	
}
