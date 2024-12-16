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

        AdministrationMethodDto dto = new AdministrationMethodDto();
        dto.setId(method.getId());
        dto.setSingleDosage(method.getSingleDosage());
        dto.setInterval(method.getInterval());
        dto.setAdministrationTimes(method.getAdministrationTimes());

        return dto;
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
            method = new AdministrationMethod();
            method.setId(dto.getId());
            if (dto.getMedication() != null) {
                method.setMedication(medicationRepository.findById(dto.getMedication()).orElse(null));
            }
            method.setSingleDosage(dto.getSingleDosage());
            method.setInterval(dto.getInterval());
            method.setAdministrationTimes(dto.getAdministrationTimes());
        }

        return method;
    }
}
