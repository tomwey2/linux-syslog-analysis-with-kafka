package de.tomwey2.syslog.kafka.consumer;

import de.tomwey2.syslog.kafka.consumer.services.FailedLoginService;
import de.tomwey2.syslog.kafka.consumer.services.SuccessLoginService;
import de.tomwey2.syslog.kafka.data.FailedLoginEvent;
import de.tomwey2.syslog.kafka.data.FailedLoginEventFactory;
import de.tomwey2.syslog.kafka.data.SuccessLoginEvent;
import de.tomwey2.syslog.kafka.data.SuccessLoginEventFactory;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@AllArgsConstructor
public class SyslogKafkaConsumer {
    private final SuccessLoginService successLoginService;
    private final FailedLoginService failedLoginService;

    @Value(value = "${spring.kafka.bootstrap.servers}")
    private String bootstrapServers;

    @Value(value = "${spring.kafka.application.id}")
    private String applicationId;

    @Value(value = "${spring.kafka.group.id}")
    private String groupId;

    public static void main(String[] args) {
        SpringApplication.run(SyslogKafkaConsumer.class, args);
    }

    @KafkaListener(id = "${spring.kafka.application.id}-1", topics = "failed-login-data")
    public void listenFailedLoginEvent(String key, String jsonString) {
        FailedLoginEvent failedLoginEvent = FailedLoginEventFactory.fromJsonString(jsonString);
        System.out.println("failed-login-data ->" + failedLoginEvent);
        failedLoginService.insert(key, failedLoginEvent);
    }

    @KafkaListener(id = "${spring.kafka.application.id}-2", topics = "success-login-data")
    public void listenSuccessLoginEvent(String key, String jsonString) {
        SuccessLoginEvent successLoginEvent = SuccessLoginEventFactory.fromJsonString(jsonString);
        System.out.println("success-login-data ->" + successLoginEvent);
        successLoginService.insert(key, successLoginEvent);
    }

    @Bean
    ConcurrentKafkaListenerContainerFactory<Integer, String>
    kafkaListenerContainerFactory(ConsumerFactory<Integer, String> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<Integer, String> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }

    @Bean
    public ConsumerFactory<Integer, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerProps());
    }

    private Map<String, Object> consumerProps() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        // ...
        return props;
    }
}
