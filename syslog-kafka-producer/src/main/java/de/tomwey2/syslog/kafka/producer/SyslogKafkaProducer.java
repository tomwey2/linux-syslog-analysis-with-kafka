package de.tomwey2.syslog.kafka.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SyslogKafkaProducer {

    public static void main(String[] args) {
        SpringApplication.run(SyslogKafkaProducer.class, args);
    }

}
