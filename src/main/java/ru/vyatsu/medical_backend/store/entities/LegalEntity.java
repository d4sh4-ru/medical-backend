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
@Table(name = "legal_entities", schema = "core")
public class LegalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 255)
    @Pattern(regexp = "^[A-Za-zА-Яа-я0-9 ]+$")
    private String name;

    @Column(nullable = false, length = 30)
    @Pattern(regexp = "^[A-Za-zА-Яа-я ]+$")
    private String country;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "medication_legal_entities",
            joinColumns = @JoinColumn(name = "legal_entity_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "medication_id", referencedColumnName = "id")
    )
    private Set<Medication> medications;
}
