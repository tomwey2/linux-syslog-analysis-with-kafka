package de.tomwey2.syslog.kafka.consumer.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "syslogs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Syslog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "filename")
    private String filename;
}
