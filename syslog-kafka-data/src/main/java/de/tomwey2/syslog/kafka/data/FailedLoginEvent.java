package de.tomwey2.syslog.kafka.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public record FailedLoginEvent(
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime timestamp,
        String message,
        String host,
        String user,
        int failureCount
) implements Serializable {
    @JsonIgnore
    public long getTimestampMillis() {
        return timestamp().toEpochSecond(ZoneOffset.UTC) * 1000;
    }
}