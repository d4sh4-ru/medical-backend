package ru.vyatsu.medical_backend.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.vyatsu.medical_backend.DTO.MedicationScheduleDto;
import ru.vyatsu.medical_backend.store.entities.MedicationSchedule;
import ru.vyatsu.medical_backend.store.repositories.MedicationRepository;
import ru.vyatsu.medical_backend.store.repositories.UserRepository;

@Component
public class MedicationScheduleMapper {
    @Autowired
    MedicationRepository medicationRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AdministrationMethodMapper administrationMethodMapper;

    public MedicationScheduleDto toDto(MedicationSchedule schedule) {
        if (schedule == null) {
            return null;
        }

        MedicationScheduleDto dto = new MedicationScheduleDto();
        dto.setId(schedule.getId());
        dto.setMedicationId(schedule.getMedication() != null ? schedule.getMedication().getId() : null);
        dto.setUserEmail(schedule.getUser() != null ? schedule.getUser().getEmail() : null);
        dto.setStartDate(schedule.getStartDate());
        dto.setEndDate(schedule.getEndDate());
        dto.setAdministrationMethod(administrationMethodMapper.toAdministrationMethodDto(schedule.getAdministrationMethod()));

        return dto;
    }

    public MedicationSchedule toEntity(MedicationScheduleDto dto) {
        if (dto == null) {
            return null;
        }

        MedicationSchedule schedule = new MedicationSchedule();
        schedule.setId(dto.getId());
        schedule.setMedication(medicationRepository.findById(dto.getMedicationId()).orElse(null));
        schedule.setUser(userRepository.findByEmail(dto.getUserEmail()).orElse(null));
        schedule.setStartDate(dto.getStartDate());
        schedule.setEndDate(dto.getEndDate());
        schedule.setAdministrationMethod(administrationMethodMapper.toAdministrationMethodEntity(dto.getAdministrationMethod()));

        return schedule;
    }
}
