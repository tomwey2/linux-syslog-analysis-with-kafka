package de.tomwey2.linux.syslog.kafka.producer;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UploadException extends RuntimeException {
    public UploadException(String message) {
        super(message);
    }
}
