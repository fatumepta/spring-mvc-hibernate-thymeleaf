package org.example.app.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

import static org.hibernate.cfg.AvailableSettings.*;


@Configuration
@EnableTransactionManagement
@PropertySource("classpath:db.properties")
public class HibernateConfig {
    private final Environment env;

    @Autowired
    public HibernateConfig(Environment env) {
        this.env = env;
    }

    @Bean
    @Autowired
    public PlatformTransactionManager transactionManager(SessionFactory sessionFactory) {
        return new JpaTransactionManager(sessionFactory);
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(env.getProperty("entities.location.folder"));
        sessionFactory.setHibernateProperties(hibernateProperties());

        return sessionFactory;
    }

    @Bean
    public DataSource dataSource() {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        try {
            dataSource.setDriverClass(env.getProperty("jdbc.driver"));
            dataSource.setJdbcUrl(env.getProperty("jdbc.url"));
            dataSource.setUser(env.getProperty("jdbc.user"));
//            dataSource.setPassword(env.getProperty("jdbc.password"));
        } catch (PropertyVetoException exc) {
            exc.printStackTrace();
        }
        return dataSource;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();

        // Hibernate properties
        properties.put(DIALECT, env.getProperty("hibernate.dialect"));
        properties.put(SHOW_SQL, env.getProperty("hibernate.show_sql"));
        properties.put(FORMAT_SQL, env.getProperty("hibernate.format_sql"));
        properties.put(HBM2DDL_AUTO, env.getProperty("hibernate.hbm2ddl.auto"));

        // C3P0 properties
        properties.put(C3P0_MIN_SIZE, env.getProperty("hibernate.c3p0.min_size"));
        properties.put(C3P0_MAX_SIZE, env.getProperty("hibernate.c3p0.max_size"));
        properties.put(C3P0_ACQUIRE_INCREMENT, env.getProperty("hibernate.c3p0.acquire_increment"));
        properties.put(C3P0_TIMEOUT, env.getProperty("hibernate.c3p0.timeout"));
        properties.put(C3P0_MAX_STATEMENTS, env.getProperty("hibernate.c3p0.max_statements"));

        return properties;
    }
}
