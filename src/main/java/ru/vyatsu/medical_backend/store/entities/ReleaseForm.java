package ru.vyatsu.medical_backend.store.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "release_forms", schema = "core")
public class ReleaseForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "dosage_per_tablet", length = 15)
    @Pattern(regexp = "^[0-9]+ ?[A-Za-zА-Яа-я]+$")
    private String dosagePerTablet;

    @Column(name = "tablets_count")
    private Integer tabletsCount;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "medication_release_forms",
            joinColumns = @JoinColumn(name = "release_form_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "medication_id", referencedColumnName = "id")
    )
    private Set<Medication> medications;
}
