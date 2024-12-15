package ru.vyatsu.medical_backend.store.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "instructions", schema = "core")
public class Instruction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "medication_id", referencedColumnName = "id")
    private Medication medication;

    @Column(columnDefinition = "TEXT")
    private String content;
}
