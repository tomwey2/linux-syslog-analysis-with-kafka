package de.tomwey2.linux.syslog.kafka.producer;

import de.tomwey2.linux.syslog.kafka.producer.UploadException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
@AllArgsConstructor
@Slf4j
public class UploadService {
    private final KafkaTemplate<String, String> kafkaTemplate;

    //@Value("${spring.kafka.output.topic.name}")
    //private String topic;

    public void upload(final MultipartFile file) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                //log.info(line);
                kafkaTemplate.send("log-raw-data", line);
            }
        } catch (IOException e) {
            throw new UploadException(e.getMessage());
        }
    }
}
