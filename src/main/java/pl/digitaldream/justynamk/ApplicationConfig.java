package pl.digitaldream.justynamk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.core.env.SimpleCommandLinePropertySource;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Arrays;

@ComponentScan
@EnableAutoConfiguration
public class ApplicationConfig {
    private static final Logger log = LoggerFactory.getLogger(ApplicationWebXml.class);

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(ApplicationConfig.class);
        app.setShowBanner(false);

        app.run(args);
    }


}
