package pl.digitaldream.justynamk.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import pl.digitaldream.justynamk.ApplicationWebXml;

import javax.sql.DataSource;

@Configuration
@Profile({"in-memory","dev"})
@EnableJpaRepositories("pl.digitaldream.justynamk.repository")
public class HsqlDBConfig {
    private final Logger log = LoggerFactory.getLogger(ApplicationWebXml.class);
    @Bean
    public DataSource datasource(){
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        log.info("creating h2 db");
        return builder.setType(EmbeddedDatabaseType.HSQL).build();
    }

}
