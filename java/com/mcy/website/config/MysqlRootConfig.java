package com.mcy.website.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.annotation.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;
import javax.sql.DataSource;
import java.util.Properties;

//配置数据库和数据库事务
@Configuration
@PropertySource(value = "classpath:jdbc.properties")
@EnableTransactionManagement
//定义spring扫描的包
@ComponentScan(value = "com.mcy.website.*", includeFilters =
        {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = {Service.class})})
public class MysqlRootConfig implements TransactionManagementConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(MysqlRootConfig.class);

    private DataSource dataSource = null;

    @Value("${jdbc.driverClassName}")
    private String driverClassName = null;
    @Value("${jdbc.url}")
    private String url = null;
    @Value("${jdbc.username}")
    private String username = null;
    @Value("${jdbc.password}")
    private String password = null;

    /**
     * 配置数据库
     * @return 数据库连接池
     */
    @Bean(name = "datasource")
    public DataSource dataSource(){
        if (dataSource != null){
            return dataSource;
        }
        Properties props = new Properties();
        props.setProperty("driverClassName", driverClassName);
        props.setProperty("url", url);
        props.setProperty("username", username);
        props.setProperty("password", password);

        DruidDataSource druidDataSource = null;
        try {
            druidDataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(props);
        } catch (Exception e) {
            logger.error("创建数据库连接池dataSource失败", e);
            e.printStackTrace();
        }
        return druidDataSource;
    }

    /**
     * 配置SqlSessionFactoryBean
     * @return 返回SqlSessionFactoryBean
     */
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactoryBean sqlSessionFactoryBean(){
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource());
        Resource resource = new ClassPathResource("mybatis-config.xml");
        sqlSessionFactory.setConfigLocation(resource);
        return sqlSessionFactory;
    }

    /**
     *实现接口方法,注册注解事务,当Transaction使用时产生数据库事务
     */
    @Override
    @Bean
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource());
        return transactionManager;
    }
}
