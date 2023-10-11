package de.tomwey2.linux.syslog.kafka.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaLogProducer {

    public static void main(String[] args) {
        SpringApplication.run(KafkaLogProducer.class, args);
    }

}
