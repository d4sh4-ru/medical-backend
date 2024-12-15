package ru.vyatsu.medical_backend.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class MedicationDto {
    private Integer id;
    private String tradeName;
    private String storageConditions;
    private boolean isPrescription;
    private boolean isDietarySupplement;

    private Set<String> legalEntityDetails; // Название и страна
    private Set<String> pharmacologicalGroupNames; // Название
    private Set<String> releaseFormDetails; // Дозировка и количество таблеток
}