package pl.digitaldream.justynamk.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.ApplicationContextException;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Arrays;

/**
 * Created by Witek on 2014-11-10.
 */

@Configuration
@Profile({"openshift","local"})
@EnableTransactionManagement
public class PostgreSqlDBConfig implements EnvironmentAware {


    public static final Logger log = LoggerFactory.getLogger(PostgreSqlDBConfig.class);

    private Environment env;

    private RelaxedPropertyResolver propertyResolver;

    @Override
    public void setEnvironment(Environment environment) {
        this.env = environment;
        this.propertyResolver = new RelaxedPropertyResolver(environment, "jdbc.");
    }

    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        if (propertyResolver.getProperty("url")  == null) {
            log.error("Your database connection pool configuration is incorrect! The application" +
                            "cannot start. Please check your Spring profile, current profiles are: {}",
                    Arrays.toString(env.getActiveProfiles()));

            throw new ApplicationContextException("Database connection pool is not configured correctly");
        }


        HikariConfig config = new HikariConfig();
        config.setDataSourceClassName("org.postgresql.ds.PGPoolingDataSource");
        config.addDataSourceProperty("url", propertyResolver.getProperty("url"));
        config.addDataSourceProperty("user", propertyResolver.getProperty("username"));
        config.addDataSourceProperty("password", propertyResolver.getProperty("password"));

        return new HikariDataSource(config);

    }
}
