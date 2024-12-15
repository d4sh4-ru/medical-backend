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
@Table(name = "releaseforms", schema = "core")
public class ReleaseForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "dosage_per_tablet", length = 15)
    @Pattern(regexp = "^[0-9]+ ?[A-Za-zА-Яа-я]+$")
    private String dosagePerTablet;

    @Column(name = "tablets_count")
    private Integer tabletsCount;
}
