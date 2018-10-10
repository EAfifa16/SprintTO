package com.jlcindia.spring.controller;

import java.util.Properties; 

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.*;  
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceView;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@EnableWebMvc //works by importing the Spring MVC Configuration from WebMvcConfigurationSupport, XML equivalent is <mvc:annotation-driven/>
@Configuration
@ComponentScan(basePackages={"com.jlcindia.spring.*"})
@EnableTransactionManagement
public class JlcConfig extends WebMvcConfigurerAdapter{

//1.
	@Bean
	ViewResolver createViewResolver() {
		InternalResourceViewResolver vr=new InternalResourceViewResolver();
		vr.setViewClass(InternalResourceView.class);
		vr.setPrefix("/");
		vr.setSuffix(".jsp");
		return vr;
	}
	
//2.
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource=new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/springdb");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		return dataSource;
	}
	
//3.
	@Bean
	public LocalSessionFactoryBean sessionFactory(DataSource ds) {
		LocalSessionFactoryBean sessionFactory=new LocalSessionFactoryBean();
		sessionFactory.setDataSource(ds);
		sessionFactory.setPackagesToScan("com.jlcindia.spring.model");
		Properties props=new Properties();
		props.put("hibernate.dialect","org.hibernate.dialect.MySQL5Dialect");
		sessionFactory.setHibernateProperties(props);
		return sessionFactory;
	}
	
//4.
	@Bean
	public HibernateTemplate hibernateTamplet(SessionFactory sf) {
		HibernateTemplate hTemp=new HibernateTemplate(sf);
		return hTemp;
	}
	
//5.
	@Bean
	public HibernateTransactionManager transactionManager(SessionFactory sf) {
		HibernateTransactionManager txManager=new HibernateTransactionManager(sf);
		return txManager;
	}
}
