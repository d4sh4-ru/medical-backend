package ru.vyatsu.medical_backend.store.repositories;

import jakarta.validation.constraints.Pattern;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.vyatsu.medical_backend.store.entities.MedicationNotification;
import ru.vyatsu.medical_backend.store.entities.User;

import java.util.List;

public interface MedicationNotificationRepository extends JpaRepository<MedicationNotification, Integer> {
    List<MedicationNotification> findByStatus(@Pattern(regexp = "^[A-Za-zА-Яа-я ]+$") String status);
    List<MedicationNotification> findByStatusAndUser(@Pattern(regexp = "^[A-Za-zА-Яа-я ]+$") String status, User user);

    List<MedicationNotification> findByUser(User user);
}
