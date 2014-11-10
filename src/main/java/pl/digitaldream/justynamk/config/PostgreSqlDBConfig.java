package pl.digitaldream.justynamk.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Created by Witek on 2014-11-10.
 */

@Configuration
@Profile("openshift")
@EnableTransactionManagement
public class PostgreSqlDBConfig {

//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
//        entityManagerFactory.setDataSource(dataSource());
//        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
//        hibernateJpaVendorAdapter.setDatabase(Database.POSTGRESQL);
//        hibernateJpaVendorAdapter.setGenerateDdl(true);
//        hibernateJpaVendorAdapter.setShowSql(true);
//        entityManagerFactory.setJpaVendorAdapter(hibernateJpaVendorAdapter);
//        return entityManagerFactory;
//    }

    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        String username = System.getenv("OPENSHIFT_POSTGRESQL_DB_USERNAME");
        String password = System.getenv("OPENSHIFT_POSTGRESQL_DB_PASSWORD");
        String host = System.getenv("OPENSHIFT_POSTGRESQL_DB_HOST");
        String port = System.getenv("OPENSHIFT_POSTGRESQL_DB_PORT");
        String databaseName = System.getenv("OPENSHIFT_APP_NAME");
        String url = "jdbc:postgresql://" + host + ":" + port + "/"+databaseName;

        HikariConfig config = new HikariConfig();
        config.setDataSourceClassName("org.postgresql.Driver");
        config.addDataSourceProperty("url", url);
        config.addDataSourceProperty("user", username);
        config.addDataSourceProperty("password", password);

        return new HikariDataSource(config);

    }
}
