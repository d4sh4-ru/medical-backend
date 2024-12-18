package ru.vyatsu.medical_backend.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vyatsu.medical_backend.store.entities.MedicationNotification;

public interface MedicationNotificationRepository extends JpaRepository<MedicationNotification, Integer> {
}
