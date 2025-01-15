package ru.vyatsu.medical_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vyatsu.medical_backend.store.entities.MedicationNotification;
import ru.vyatsu.medical_backend.store.repositories.MedicationNotificationRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class NotificationService {
    @Autowired
    MedicationNotificationRepository medicationNotificationRepository;

    public void submit(int id){
        MedicationNotification medicationNotification = medicationNotificationRepository.findById(id).orElseThrow();
        medicationNotification.setActualTakenAt(LocalDateTime.now());
        medicationNotification.setStatus("confirmed");
        medicationNotificationRepository.save(medicationNotification);
    }
}
