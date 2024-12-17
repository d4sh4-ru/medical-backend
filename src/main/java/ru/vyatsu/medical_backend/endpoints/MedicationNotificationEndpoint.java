package ru.vyatsu.medical_backend.endpoints;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notification")
public class MedicationNotificationEndpoint {
    @GetMapping
    public String getMedicationNotification() {
        return "Medication Notification";
    }
}
