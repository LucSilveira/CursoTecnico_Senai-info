package br.senai.sp.info.ianes.config;

import java.util.Properties;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class PersistenceConfig {
	
	/**
	 * Cria um DataSource contendo as informações de conexão
	 * com o banco de dados que será utilizado no hibernate
	 * @return
	 */
	@Bean
	public DataSource getDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/ianes?serverTimezone=UTC");
		dataSource.setUsername("root");
		dataSource.setPassword("root132");
		return dataSource;
	}
	
	/**
	 * 
	 * @return
	 */
	public Properties getHibernateProperties() {
		Properties props = new Properties();
		
		/*Exibe os sql do hibernate no console*/
		props.setProperty("hibernate.show_sql", "true");
		
		/*DDL = Data Definition Language = CREATE TABLE, CREATE DATABASE*/
		/* validate : Não altera o banco*/
		/* update : Altera as classes alteradas*/
		/* create : Altera as classes alteradas*/
		/* create-drop : Sempre derruba as terminar a execução da aplicação (Session)*/
		props.setProperty("hibernate.hbm2ddl.auto", "update");
		
		/*Dialect - Define qual linguagem do banco o hibernate usara*/
		props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		
		return props;
	}
	/**
	 * Cria o objeto que abrirá as conexoes com o banco de dados
	 * atraves do dataSource e do HibernateProperties que criamos nesta classe
	 * @return
	 */
	@Bean
	public LocalSessionFactoryBean getSessionFactory() {
		
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(getDataSource());
		sessionFactory.setHibernateProperties(getHibernateProperties());
		
		//Onde se localiza os models do sistema
		sessionFactory.setPackagesToScan("br.senai.sp.info.pweb.ianes.models");
		
		return sessionFactory;
		
	}

	@Bean
	@Autowired
	public HibernateTransactionManager getTransactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(getSessionFactory().getObject());
		
		return transactionManager;
}
	
}
