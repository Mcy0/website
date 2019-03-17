package com.mcy.website.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.servlet.config.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Configuration
//定义Spring MVC扫描的包
@ComponentScan(value = "com.mcy.website.*")
@EnableWebMvc
public class AppConfig implements WebMvcConfigurer {

    //添加json转换器
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter jackson2HttpMessageConverter =
              new MappingJackson2HttpMessageConverter();
        //设置返回支持json格式
        MediaType applicationJsonUtf8 = MediaType.APPLICATION_JSON_UTF8;
        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(applicationJsonUtf8);
        jackson2HttpMessageConverter.setSupportedMediaTypes(mediaTypes);
        //设置全局返回日期格式
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        jackson2HttpMessageConverter.setObjectMapper(objectMapper);
        converters.add(jackson2HttpMessageConverter);
    }

    //全局跨域设置
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/")
//                .allowedOrigins("http://localhost:8080")
//                .allowCredentials(true)//允许cookie
//                .allowedMethods("GET", "POST", "OPTIONS")
//                .allowedHeaders("*")//允许的Request Headers
//                .maxAge(1800);//默认30分钟
//    }

    //静态资源设置
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/", "classpath:/")
                .setCachePeriod(31556926);
    }

    //路径匹配设置
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setUseSuffixPatternMatch(false);//关闭后缀模式匹配
    }
    //参数校验
    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor(){
        return new MethodValidationPostProcessor();
    }
}
