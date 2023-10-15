package de.tomwey2.syslog.kafka.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

import java.beans.ConstructorProperties;

@SpringBootApplication
public class SyslogKafkaProducer {

    public static void main(String[] args) {
        SpringApplication.run(SyslogKafkaProducer.class, args);
    }

}
