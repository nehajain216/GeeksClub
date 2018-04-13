package com.nj.geeksclub.config;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.expression.ParseException;
import org.springframework.format.Formatter;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	@Autowired
    private MessageSource messageSource;

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("login");
		//registry.addViewController("/users").setViewName("users");
		//registry.addViewController("/admin/users").setViewName("adminuser");
		registry.addViewController("/accessDenied").setViewName("403");
	}
	
	@Override
	public Validator getValidator() 
	{
		LocalValidatorFactoryBean factory = new LocalValidatorFactoryBean();
        factory.setValidationMessageSource(messageSource);
        return factory;
	}

	@Bean
	public SpringSecurityDialect securityDialect() {
		return new SpringSecurityDialect();
	}
	
	@Bean
	public Formatter<LocalDate> localDateFormatter() {
	  return new Formatter<LocalDate>() {
	    @Override
	    public LocalDate parse(String text, Locale locale) throws ParseException {
	      return LocalDate.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	    }
	 
	    @Override
	    public String print(LocalDate object, Locale locale) {
	      return DateTimeFormatter.ofPattern("yyyy-MM-dd").format(object);
	    }
	  };
	}
}
