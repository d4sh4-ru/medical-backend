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
@Table(name = "legalentities", schema = "core")
public class LegalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 255)
    @Pattern(regexp = "^[A-Za-zА-Яа-я0-9 ]+$")
    private String name;

    @Column(nullable = false, length = 20)
    @Pattern(regexp = "^[A-Za-zА-Яа-я ]+$")
    private String country;
}
