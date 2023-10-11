package de.tomwey2.linux.syslog.kafka.producer;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Slf4j
@AllArgsConstructor
public class UploadController {

    private final UploadService uploadService;

    @PostMapping("/api/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) {
        String filename = file.getOriginalFilename();
        if (filename == null) {
            throw new UploadException("File not valid");
        }

        uploadService.upload(file);
        return ResponseEntity.ok("File " + file.getName() + " uploaded.");
    }

}
