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

        return MedicationScheduleDto.builder()
                .id(schedule.getId())
                .medicationId(schedule.getMedication() != null ? schedule.getMedication().getId() : null)
                .userEmail(schedule.getUser() != null ? schedule.getUser().getEmail() : null)
                .startDate(schedule.getStartDate())
                .endDate(schedule.getEndDate())
                .administrationMethod(administrationMethodMapper.toAdministrationMethodDto(schedule.getAdministrationMethod()))
                .build();
    }

    public MedicationSchedule toEntity(MedicationScheduleDto dto) {
        if (dto == null) {
            return null;
        }

        return MedicationSchedule.builder()
                .id(dto.getId())
                .medication(medicationRepository.findById(dto.getMedicationId()).orElse(null))
                .user(userRepository.findByEmail(dto.getUserEmail()).orElse(null))
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .administrationMethod(administrationMethodMapper.toAdministrationMethodEntity(dto.getAdministrationMethod()))
                .build();
    }
}
