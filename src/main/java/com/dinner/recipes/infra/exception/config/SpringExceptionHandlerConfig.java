package com.dinner.recipes.infra.exception.config;

import com.dinner.recipes.infra.exception.InvalidDinnerRecipeParameterException;
import com.dinner.recipes.infra.exception.InvalidRecipeTitleException;
import com.dinner.recipes.infra.exception.RecipeItemNotFoundException;
import com.dinner.recipes.infra.exception.RecipeNotFoundException;
import com.dinner.recipes.infra.exception.handler.GeneralExceptionHandler;
import cz.jirutka.spring.exhandler.RestHandlerExceptionResolver;
import cz.jirutka.spring.exhandler.support.HttpMessageConverterUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

@Configuration
public class SpringExceptionHandlerConfig {

    @Bean
    public RestHandlerExceptionResolver restExceptionResolver() {
        return RestHandlerExceptionResolver.builder().messageSource(this.httpErrorMessageSource())
            .defaultContentType(MediaType.APPLICATION_JSON)
            .addHandler(new GeneralExceptionHandler<>(RecipeNotFoundException.class, HttpStatus.NOT_FOUND))
            .addHandler(new GeneralExceptionHandler<>(RecipeItemNotFoundException.class, HttpStatus.NOT_FOUND))
            .addHandler(new GeneralExceptionHandler<>(InvalidDinnerRecipeParameterException.class, HttpStatus.INTERNAL_SERVER_ERROR))
            .addHandler(new GeneralExceptionHandler<>(InvalidRecipeTitleException.class, HttpStatus.INTERNAL_SERVER_ERROR))
            .withDefaultHandlers(true).build();
    }

    @Bean
    public MessageSource httpErrorMessageSource() {
        ReloadableResourceBundleMessageSource m = new ReloadableResourceBundleMessageSource();
        m.setBasename("classpath:customMessages");
        m.setDefaultEncoding("UTF-8");
        return m;
    }

    @Bean
    public ExceptionHandlerExceptionResolver exceptionHandlerExceptionResolver() {
        ExceptionHandlerExceptionResolver resolver = new ExceptionHandlerExceptionResolver();
        resolver.setMessageConverters(HttpMessageConverterUtils.getDefaultHttpMessageConverters());
        return resolver;
    }
}
