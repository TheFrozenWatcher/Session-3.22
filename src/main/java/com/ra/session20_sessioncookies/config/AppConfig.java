package com.ra.session20_sessioncookies.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

//Config kết nối, security, ...
@Configuration
@EnableWebMvc
@EnableTransactionManagement
@PropertySource("classpath:Application.properties")
public class AppConfig
{
    private Environment env;

    @Autowired
    public AppConfig(Environment env)
    {
        this.env = env;
    }

    @Bean
    public DataSource getDataSouce()
    {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getRequiredProperty("hibernate.driver"));
        dataSource.setUrl(env.getRequiredProperty("hibernate.url"));
        dataSource.setUsername(env.getRequiredProperty("hibernate.username"));
        dataSource.setPassword(env.getRequiredProperty("hibernate.password"));
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean getSessionFactory()
    {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(getDataSouce());
        sessionFactory.setPackagesToScan("com.ra.session20_sessioncookies.model");
        sessionFactory.setHibernateProperties(getHibernateProperties());
        try
        {
            sessionFactory.afterPropertiesSet();
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        return sessionFactory;
    }

    @Bean
    public Properties getHibernateProperties()
    {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.hbm2dll.auto", env.getRequiredProperty("hibernate.hbm2ddl.auto"));
        hibernateProperties.setProperty("hibernate.show_sql", "true");
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        return hibernateProperties;
    }

    @Bean
    public HibernateTransactionManager getTransactionManager()
    {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(getSessionFactory().getObject());
        return transactionManager;
    }

    @Bean
    public CommonsMultipartResolver multipartResolver()
    {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSizePerFile(50 * 1024 * 1024);
        multipartResolver.setMaxUploadSize(200 * 1024 * 1024);
        multipartResolver.setDefaultEncoding("UTF-8");
        return multipartResolver;
    }
}
