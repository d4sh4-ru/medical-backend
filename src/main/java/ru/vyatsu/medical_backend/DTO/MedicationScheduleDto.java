package ru.vyatsu.medical_backend.DTO;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MedicationScheduleDto {
    private Integer id;
    private Integer medicationId;
    private String userEmail;
    private LocalDate startDate;
    private LocalDate endDate;
    private AdministrationMethodDto administrationMethod;
}
