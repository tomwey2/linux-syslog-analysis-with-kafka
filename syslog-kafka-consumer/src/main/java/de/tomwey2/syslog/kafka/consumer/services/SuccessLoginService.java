package de.tomwey2.syslog.kafka.consumer.services;

import de.tomwey2.syslog.kafka.consumer.entities.SuccessLogin;
import de.tomwey2.syslog.kafka.consumer.entities.Syslog;
import de.tomwey2.syslog.kafka.data.SuccessLoginEvent;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class SuccessLoginService {
    private final SyslogService syslogService;
    private final SuccessLoginRepository repository;

    public SuccessLogin insert(final String key, final SuccessLoginEvent successLoginEvent) {
        Syslog sysLog = syslogService.get(key).orElse(syslogService.insert(key));

        SuccessLogin successLogin = new SuccessLogin(null, sysLog,
                successLoginEvent.timestamp(), successLoginEvent.message(),
                successLoginEvent.url(), successLoginEvent.host(),
                successLoginEvent.loginTime());

        return repository.save(successLogin);
    }
}
