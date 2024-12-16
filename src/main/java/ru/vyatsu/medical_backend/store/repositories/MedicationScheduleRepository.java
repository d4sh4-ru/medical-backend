package ru.vyatsu.medical_backend.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vyatsu.medical_backend.store.entities.MedicationSchedule;

import java.util.List;

public interface MedicationScheduleRepository extends JpaRepository<MedicationSchedule, Long> {
    List<MedicationSchedule> findByUser_Email(String email);
}
