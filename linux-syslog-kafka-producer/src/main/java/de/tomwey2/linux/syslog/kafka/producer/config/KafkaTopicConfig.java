package de.tomwey2.linux.syslog.kafka.producer.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic outputTopic() {
        return TopicBuilder.name("log-raw-data")
                .build();
    }
}
