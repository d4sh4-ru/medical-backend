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

        return MedicationDto.builder()
                .id(medication.getId())
                .tradeName(medication.getTradeName())
                .storageConditions(medication.getStorageConditions())
                .isPrescription(medication.isPrescription())
                .isDietarySupplement(medication.isDietarySupplement())
                .legalEntityDetails(
                        (medication.getLegalEntities() == null || medication.getLegalEntities().isEmpty())
                                ? Set.of()
                                : mapLegalEntities(medication.getLegalEntities())
                )
                .pharmacologicalGroupNames(
                        (medication.getPharmacologicalGroups() == null || medication.getPharmacologicalGroups().isEmpty())
                                ? Set.of()
                                : mapPharmacologicalGroups(medication.getPharmacologicalGroups())
                )
                .releaseFormDetails(
                        (medication.getReleaseForms() == null || medication.getReleaseForms().isEmpty())
                                ? Set.of()
                                : mapReleaseForms(medication.getReleaseForms())
                )
                .build();
    }

    public Medication dietarySupplementToMedication(DietarySupplementDto dietarySupplementDto) {
        if (dietarySupplementDto == null) {
            return null;
        }

        return Medication.builder()
                .isDietarySupplement(true)
                .isPrescription(false)
                .tradeName(dietarySupplementDto.getTradeName())
                .build();
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
