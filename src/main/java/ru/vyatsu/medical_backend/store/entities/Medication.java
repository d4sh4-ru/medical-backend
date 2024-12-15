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
@Table(name = "medications", schema = "core")
public class Medication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 255, name = "trade_name")
    @Pattern(regexp = "^[A-Za-zА-Яа-я0-9 ]+$")
    private String tradeName;

    @Column(nullable = true, columnDefinition = "TEXT")
    private String storageConditions;

    @Column(nullable = false)
    private boolean isPrescription;

    @Column(nullable = false)
    private boolean isDietarySupplement;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "medication_legal_entities",
            joinColumns = @JoinColumn(name = "medication_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "legal_entity_id", referencedColumnName = "id")
    )
    private Set<LegalEntity> legalEntities;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "medication_pharmacological_groups",
            joinColumns = @JoinColumn(name = "medication_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "pharmacological_group_id", referencedColumnName = "id")
    )
    private Set<PharmacologicalGroup> pharmacologicalGroups;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "medication_release_forms",
            joinColumns = @JoinColumn(name = "medication_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "release_form_id", referencedColumnName = "id")
    )
    private Set<ReleaseForm> releaseForms;
}
