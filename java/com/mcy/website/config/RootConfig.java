package com.mcy.website.config;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Configuration
@ComponentScan(value = "com.mcy.website.*", includeFilters =
        {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = Component.class)})
public class RootConfig {
    /**
     * 单独拿出来,因为MapperScannerConfigurer实现了BeanFactoryPostProcessor会在解析加载bean定义阶段运行,
     * 导致所在类提前初始化,无法注入类(@Autowired无法使用),同时PropertyPlaceholderConfigurer还没来得及替换定义中的变量
     * PropertyPlaceholderConfigurer(@PropertySource)无法使用
     * 通过自动扫描,发现MyBatis Mapper接口
     * @return 扫描器
     */
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(){
        MapperScannerConfigurer msc = new MapperScannerConfigurer();
        msc.setBasePackage("com.mcy.website.*");
        msc.setSqlSessionFactoryBeanName("sqlSessionFactory");
        msc.setAnnotationClass(Repository.class);
        return msc;
    }
}
