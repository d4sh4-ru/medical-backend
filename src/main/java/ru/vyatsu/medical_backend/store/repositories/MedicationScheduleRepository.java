package ru.vyatsu.medical_backend.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vyatsu.medical_backend.store.entities.MedicationSchedule;

public interface MedicationScheduleRepository extends JpaRepository<MedicationSchedule, Long> {
}
