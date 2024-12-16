package ru.vyatsu.medical_backend.store.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.List;

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
    @JoinColumn(name = "medication_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_administration_methods_medication"))
    private Medication medication;

    @Column(name = "single_dosage", nullable = false)
    @Pattern(regexp = "^[0-9]+ ?[A-Za-zА-Яа-я]+$")
    private String singleDosage;

    @Column(name = "interval", nullable = false)
    private Integer interval;

    @Column(name = "administration_times", nullable = false)
    private String administrationTimes;

    @OneToMany(mappedBy = "administrationMethod", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MedicationSchedule> medicationSchedules;
}
