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
@Table(name = "Medications", schema = "core")
public class Medication {
    @Id
    @Column(nullable = false, length = 255)
    @Pattern(regexp = "^[A-Za-zА-Яа-я0-9 ]+$")
    private String tradeName;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "legal_entity_id", referencedColumnName = "id")
    private LegalEntity legalEntity;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "pharmacological_group_id", referencedColumnName = "id")
    private PharmacologicalGroup pharmacologicalGroup;

    @Column(nullable = true, columnDefinition = "TEXT")
    private String storageConditions;

    @Column(nullable = false)
    private boolean isPrescription;

    @Column(nullable = false)
    private boolean isDietarySupplement;
}
