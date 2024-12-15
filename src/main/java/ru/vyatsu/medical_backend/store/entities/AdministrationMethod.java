package ru.vyatsu.medical_backend.store.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "administration_methods", schema = "core")
public class AdministrationMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "medication_id", referencedColumnName = "id")
    private Medication medication;

    @Column(name = "single_dosage", length = 255)
    @Pattern(regexp = "^[0-9]+ ?[A-Za-zА-Яа-я]+$")
    private String singleDosage;

    @Column(name = "interval")
    private Integer interval;
}
