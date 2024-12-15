package ru.vyatsu.medical_backend.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vyatsu.medical_backend.store.entities.PharmacySearchTemplate;

import java.util.Optional;

public interface PharmacySearchTemplateRepository extends JpaRepository<PharmacySearchTemplate, Long> {
    Optional<PharmacySearchTemplate> findByPharmacyName(String pharmacyName);
}
