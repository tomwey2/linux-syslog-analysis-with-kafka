package de.tomwey2.syslog.kafka.producer.upload;

import de.tomwey2.syslog.kafka.producer.config.KafkaProducerConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(path = "/producer")
@RequiredArgsConstructor
public class UploadController {

    private final UploadService uploadService;

    @Value("${spring.kafka.output.topic.name}")
    private String topic;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        String filename = file.getOriginalFilename();
        if (filename == null) {
            throw new UploadException("File not valid");
        }

        uploadService.uploadFile(file);
        return ResponseEntity.ok("File " + filename + " uploaded.\n");
    }

    @PostMapping("/message")
    public ResponseEntity<String> uploadMessage(@RequestParam("content") String message) {
        if (message == null) {
            throw new UploadException("Message not valid");
        }

        uploadService.uploadMessage("line", message);
        return ResponseEntity.ok("Message sent at " + topic + ".\n");
    }

}
