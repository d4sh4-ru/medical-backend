package ru.vyatsu.medical_backend.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MedicationScheduleDto {
    private Integer id;
    private Integer medicationId;
    private String userEmail;
    private LocalDate startDate;
    private LocalDate endDate;
    private AdministrationMethodDto administrationMethod;
}
