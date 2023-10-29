package de.tomwey2.syslog.kafka.consumer.services;

import de.tomwey2.syslog.kafka.consumer.entities.FailedLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FailedLoginRepository extends JpaRepository<FailedLogin, Integer> {
}
