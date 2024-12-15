package ru.vyatsu.medical_backend.store.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "medicationrestock", schema = "core")
public class MedicationRestock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "medication_trade_name", referencedColumnName = "tradeName")
    private Medication medication;

    @ManyToOne
    @JoinColumn(name = "user_email", referencedColumnName = "email")
    private User user;

    @Column(name = "request_date")
    private LocalDateTime requestDate;

    @Column(name = "restock_date")
    private LocalDateTime restockDate;

    @Column(name = "remaining_quantity")
    private Integer remainingQuantity;
}
