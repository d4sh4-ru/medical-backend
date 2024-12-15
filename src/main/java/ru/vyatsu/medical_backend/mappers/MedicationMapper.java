package ru.vyatsu.medical_backend.mappers;

import org.springframework.stereotype.Component;
import ru.vyatsu.medical_backend.DTO.DietarySupplementDto;
import ru.vyatsu.medical_backend.DTO.MedicationDto;
import ru.vyatsu.medical_backend.store.entities.*;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class MedicationMapper {
    public MedicationDto toDto(Medication medication) {
        if (medication == null) {
            return null;
        }

        MedicationDto medicationDto = new MedicationDto();
        medicationDto.setId(medication.getId());
        medicationDto.setTradeName(medication.getTradeName());
        medicationDto.setStorageConditions(medication.getStorageConditions());
        medicationDto.setPrescription(medication.isPrescription());
        medicationDto.setDietarySupplement(medication.isDietarySupplement());

        medicationDto.setLegalEntityDetails(
                (medication.getLegalEntities() == null || medication.getLegalEntities().isEmpty())
                        ? Set.of()
                        : mapLegalEntities(medication.getLegalEntities())
        );

        medicationDto.setPharmacologicalGroupNames(
                (medication.getPharmacologicalGroups() == null || medication.getPharmacologicalGroups().isEmpty())
                        ? Set.of()
                        : mapPharmacologicalGroups(medication.getPharmacologicalGroups())
        );

        medicationDto.setReleaseFormDetails(
                (medication.getReleaseForms() == null || medication.getReleaseForms().isEmpty())
                        ? Set.of()
                        : mapReleaseForms(medication.getReleaseForms())
        );

        return medicationDto;
    }

    public Medication dietarySupplementToMedication(DietarySupplementDto dietarySupplementDto) {
        if (dietarySupplementDto == null) {
            return null;
        }

        Medication medication = new Medication();
        medication.setTradeName(dietarySupplementDto.getTradeName());
        medication.setPrescription(false);
        medication.setDietarySupplement(true);

        return medication;
    }

    private Set<String> mapLegalEntities(Set<LegalEntity> legalEntities) {
        return legalEntities.stream()
                .map(entity -> entity.getName() + " (" + entity.getCountry() + ")")
                .collect(Collectors.toSet());
    }

    private Set<String> mapPharmacologicalGroups(Set<PharmacologicalGroup> pharmacologicalGroups) {
        return pharmacologicalGroups.stream()
                .map(PharmacologicalGroup::getName)
                .collect(Collectors.toSet());
    }

    private Set<String> mapReleaseForms(Set<ReleaseForm> releaseForms) {
        return releaseForms.stream()
                .map(releaseForm -> releaseForm.getDosagePerTablet() + " (" + releaseForm.getTabletsCount() + " таблеток)")
                .collect(Collectors.toSet());
    }
}
