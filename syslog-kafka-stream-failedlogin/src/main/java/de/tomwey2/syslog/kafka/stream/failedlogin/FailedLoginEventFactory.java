package de.tomwey2.syslog.kafka.stream.failedlogin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FailedLoginEventFactory {
    private static final Logger logger = LoggerFactory.getLogger(FailedLoginEventFactory.class);
    private static final ObjectMapper objectMapper = JsonMapper.builder()
            .addModule(new JavaTimeModule())
            .build();
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MMM dd HH:mm:ss", Locale.US);
    private static final String timestampRgx = "(?<timestamp>\\w{3}\\s{1,2}\\d{1,2} \\d{2}:\\d{2}:\\d{2})";
    public static final String logDataRgx = ".*sshd.*: (?<message>[^;]+)(.*rhost=(?<host>[^\\s]+))?(.*user=(?<user>[^$]+))?";
    public static final Pattern pattern = Pattern.compile(timestampRgx + logDataRgx);

    public static FailedLoginEvent toFailedLoginEvent(final String logEntry) {
        Matcher matcher = pattern.matcher(logEntry);
        if (!matcher.find()) {
            return null;
        }

        String timestampStr = "2023 " + matcher.group("timestamp");
        if (timestampStr.charAt(9) == ' ') {
            timestampStr = timestampStr.substring(0, 9) + '0' + timestampStr.substring(10);
        }
        String messageStr = matcher.group("message");
        String hostStr = matcher.group("host");
        String userStr = matcher.group("user");
        LocalDateTime timestamp = LocalDateTime.parse(timestampStr, formatter);

        return new FailedLoginEvent(timestamp,
                matcher.group("message"),
                matcher.group("host"),
                matcher.group("user"),
                0);
    }

    public static String toJsonString(FailedLoginEvent failedLoginEvent) {
        try {
            return objectMapper.writeValueAsString(failedLoginEvent);
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage());
            return null;
        }
    }

}
