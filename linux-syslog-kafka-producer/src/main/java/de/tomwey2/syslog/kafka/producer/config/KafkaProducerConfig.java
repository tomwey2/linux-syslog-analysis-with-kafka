package de.tomwey2.syslog.kafka.producer.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;

@Configuration
public class KafkaProducerConfig {
    @Value("${spring.kafka.bootstrap.servers}")
    private String bootstrapServers;
    @Value("${spring.kafka.output.topic.name}")
    private String outputTopicName;
    @Value("${spring.kafka.group.id}")
    private String groupId;
    @Value("${spring.kafka.application.id}")
    private String applicationId;

    @Bean
    public ProducerFactory<String, String> producerFactory() {
        HashMap<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put("spring.kafka.output.topic.name", outputTopicName);
        props.put("spring.kafka.group.id", groupId);
        props.put("spring.kafka.application.id", applicationId);

        System.out.println("BOOTSTRAP_SERVERS_CONFIG:" + bootstrapServers);
        return new DefaultKafkaProducerFactory<>(props);
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public NewTopic outputTopic() {
        return TopicBuilder
                .name(outputTopicName)
                .build();
    }}
