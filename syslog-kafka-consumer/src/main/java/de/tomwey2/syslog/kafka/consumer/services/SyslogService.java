package de.tomwey2.syslog.kafka.consumer.services;

import de.tomwey2.syslog.kafka.consumer.entities.Syslog;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class SyslogService {
    private final SyslogRepository repository;

    public Optional<Syslog> get(final String filename) {
        return repository.findByFilename(filename);
    }

    public Syslog insert(final String filename) {
        System.out.println("insert: " + filename + "<-");
        Syslog syslog = new Syslog(null, filename);
        return repository.save(syslog);
    }
}
