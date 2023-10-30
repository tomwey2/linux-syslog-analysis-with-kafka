package de.tomwey2.syslog.kafka.consumer.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(name = "failed_logins")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FailedLogin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name="syslog_id", nullable=false)
    private Syslog sysLog;

    @Column(name = "logtime")
    private LocalDateTime logtime;

    @Column(name = "message")
    private String message;

    @Column(name = "host")
    private String host;

    @Column(name = "user")
    private String user;

    @Column(name = "failure_count")
    private Integer failedCount;

}
