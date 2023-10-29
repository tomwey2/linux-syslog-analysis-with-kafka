package de.tomwey2.syslog.kafka.consumer.services;

import de.tomwey2.syslog.kafka.consumer.entities.FailedLogin;
import de.tomwey2.syslog.kafka.consumer.entities.Syslog;
import de.tomwey2.syslog.kafka.data.FailedLoginEvent;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FailedLoginService {
    private final SyslogService syslogService;
    private final FailedLoginRepository repository;

    public FailedLogin insert(final String key, final FailedLoginEvent failedLoginEvent) {
        Syslog sysLog = syslogService.get(key).orElse(syslogService.insert(key));

        FailedLogin failedLogin = new FailedLogin(null, sysLog,
                failedLoginEvent.timestamp(), failedLoginEvent.message(),
                failedLoginEvent.host(), failedLoginEvent.user(), 0);

        return repository.save(failedLogin);
    }}
