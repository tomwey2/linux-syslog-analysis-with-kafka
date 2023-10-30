package de.tomwey2.syslog.kafka.consumer.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SuccessLogin {
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

    @Column(name = "url")
    private String url;

    @Column(name = "host")
    private String host;

    @Column(name = "login_time")
    private String loginTime;

}
