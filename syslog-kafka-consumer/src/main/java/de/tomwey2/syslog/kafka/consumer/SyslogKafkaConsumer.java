package de.tomwey2.syslog.kafka.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SyslogKafkaConsumer {
    public static void main(String[] args) {
        SpringApplication.run(SyslogKafkaConsumer.class, args);
    }

}
