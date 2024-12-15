package ru.vyatsu.medical_backend.store.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "administrationtimes", schema = "core")
public class AdministrationTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "method_id", referencedColumnName = "id")
    private AdministrationMethod administrationMethod;

    @Column(name = "time", nullable = false)
    private LocalTime time;
}
