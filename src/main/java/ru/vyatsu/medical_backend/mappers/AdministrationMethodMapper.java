package ru.vyatsu.medical_backend.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.vyatsu.medical_backend.DTO.AdministrationMethodDto;
import ru.vyatsu.medical_backend.store.entities.AdministrationMethod;
import ru.vyatsu.medical_backend.store.repositories.AdministrationMethodRepository;
import ru.vyatsu.medical_backend.store.repositories.MedicationRepository;

@Component
public class AdministrationMethodMapper {
    @Autowired
    MedicationRepository medicationRepository;

    @Autowired
    AdministrationMethodRepository administrationMethodRepository;

    public AdministrationMethodDto toAdministrationMethodDto(AdministrationMethod method) {
        if (method == null) {
            return null;
        }

        return AdministrationMethodDto.builder()
                .id(method.getId())
                .singleDosage(method.getSingleDosage())
                .interval(method.getInterval())
                .administrationTimes(method.getAdministrationTimes())
                .build();
    }

    public AdministrationMethod toAdministrationMethodEntity(AdministrationMethodDto dto) {
        if (dto == null) {
            return null;
        }

        AdministrationMethod method = administrationMethodRepository.
                findAdministrationMethodByMedication_IdAndSingleDosageAndIntervalAndAdministrationTimes(
                        dto.getMedication(),
                        dto.getSingleDosage(),
                        dto.getInterval(),
                        dto.getAdministrationTimes()
                ).orElse(null);
        if (method == null) {
            method = AdministrationMethod.builder()
                    .id(dto.getId())
                    .medication(medicationRepository.findById(dto.getMedication()).orElse(null))
                    .singleDosage(dto.getSingleDosage())
                    .interval(dto.getInterval())
                    .administrationTimes(dto.getAdministrationTimes())
                    .build();
        }

        return method;
    }
}
