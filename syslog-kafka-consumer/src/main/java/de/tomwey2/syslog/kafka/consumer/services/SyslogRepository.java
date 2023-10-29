package de.tomwey2.syslog.kafka.consumer.services;

import de.tomwey2.syslog.kafka.consumer.entities.Syslog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SyslogRepository extends JpaRepository<Syslog, Integer> {
    Optional<Syslog> findByFilename(final String filename);
}
