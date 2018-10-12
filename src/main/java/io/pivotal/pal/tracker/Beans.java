package io.pivotal.pal.tracker;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import javax.sql.DataSource;

@Configuration
public class Beans {
    @Bean
    TimeEntryRepository timeEntryRepository(DataSource dataSource) {
        return new JdbcTimeEntryRepository(dataSource);
    }

    @Bean
    public ObjectMapper jsonObjectMapper() {
        return Jackson2ObjectMapperBuilder.json()
                .serializationInclusion(JsonInclude.Include.NON_NULL) // Don’t include null values
                .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS) //ISODate
                .modules(new JavaTimeModule())
                .build();
    }

    @Bean
    InfoContributor infoContributor() {
        return new InfoContributor() {
            @Override
            public void contribute(Info.Builder builder) {
                builder.withDetail("My Custom Key", "My Custom Value")
                        .build();
            }
        };
    }
}
