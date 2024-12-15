package ru.vyatsu.medical_backend.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vyatsu.medical_backend.store.entities.Medication;

import java.util.Optional;

public interface MedicationRepository extends JpaRepository<Medication, Integer> {
    Optional<Medication> findByTradeName(String tradeName);
}
