package ru.vyatsu.medical_backend.DTO;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MedicationNotificationDto {
    private Integer id;
    private Integer medicationScheduleId;
    private String userEmail;
    private String relatedUserEmail;
    private LocalDateTime sentAt;
    private String status;
    private LocalDateTime actualTakenAt;
}
