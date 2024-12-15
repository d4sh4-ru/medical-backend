package ru.vyatsu.medical_backend.store.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "medication_notifications", schema = "core")
public class MedicationNotification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "schedule_id", referencedColumnName = "id")
    private MedicationSchedule medicationSchedule;

    @ManyToOne
    @JoinColumn(name = "user_email", referencedColumnName = "email")
    private User user;

    @ManyToOne
    @JoinColumn(name = "related_user_email", referencedColumnName = "email")
    private User relatedUser;

    @Column(name = "sent_at")
    private LocalDateTime sentAt;

    @Column(name = "status", length = 50)
    @Pattern(regexp = "^[A-Za-zА-Яа-я ]+$")
    private String status;

    @Column(name = "actual_taken_at")
    private LocalDateTime actualTakenAt;
}
