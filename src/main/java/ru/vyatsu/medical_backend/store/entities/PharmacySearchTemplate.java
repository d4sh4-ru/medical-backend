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
@Table(name = "pharmacy_search_templates", schema = "core")
public class PharmacySearchTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "pharmacy_name", length = 255)
    @Pattern(regexp = "^[A-Za-zА-Яа-я ]+$")
    private String pharmacyName;

    @Column(name = "search_url_template", columnDefinition = "TEXT")
    private String searchUrlTemplate;
}
