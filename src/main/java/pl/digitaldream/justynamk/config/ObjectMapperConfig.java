package pl.digitaldream.justynamk.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.autoconfigure.web.HttpMapperProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;


@Configuration
@EnableConfigurationProperties(HttpMapperProperties.class)
public class ObjectMapperConfig {
    @Autowired
    private HttpMapperProperties properties = new HttpMapperProperties();
    @Bean
    @Primary
    public ObjectMapper objectMapper(){
        ObjectMapper objectMapper = new JodaMapper();
        if (this.properties.isJsonSortKeys()) {
            objectMapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS,true);
        }
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.configure(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE, false);
        return objectMapper;
    }

}
