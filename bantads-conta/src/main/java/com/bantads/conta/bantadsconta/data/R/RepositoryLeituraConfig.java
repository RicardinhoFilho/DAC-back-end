package com.bantads.conta.bantadsconta.data.R;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@PropertySource({ "classpath:application.properties" })
@EnableJpaRepositories(
    basePackages = "com.bantads.conta.bantadsconta.data.R", 
    entityManagerFactoryRef = "readEntityManager", 
    transactionManagerRef = "readTransactionManager"
)
public class RepositoryLeituraConfig {
	@Autowired
    private Environment env;

    public RepositoryLeituraConfig() {
        super();
    }

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean readEntityManager() {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(readDataSource());
        em.setPackagesToScan("com.bantads.conta.bantadsconta.model.R");

        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        final HashMap<String, Object> properties = new HashMap<String, Object>();
        properties.put("hibernate.dialect", env.getProperty("spring.jpa.properties.hibernate.dialect"));
        em.setJpaPropertyMap(properties);
        
        return em;
    }
	
	@Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.read-datasource")
    public DataSource readDataSource() {
        return DataSourceBuilder.create().build();
    }
	
	@Primary
    @Bean
    public PlatformTransactionManager readTransactionManager() {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(readEntityManager().getObject());
        return transactionManager;
    }
}
