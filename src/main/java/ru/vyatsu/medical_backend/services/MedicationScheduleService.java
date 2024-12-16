package ru.vyatsu.medical_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vyatsu.medical_backend.DTO.MedicationScheduleDto;
import ru.vyatsu.medical_backend.mappers.MedicationScheduleMapper;
import ru.vyatsu.medical_backend.store.entities.MedicationSchedule;
import ru.vyatsu.medical_backend.store.repositories.AdministrationMethodRepository;
import ru.vyatsu.medical_backend.store.repositories.MedicationScheduleRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicationScheduleService {

    @Autowired
    private MedicationScheduleRepository medicationScheduleRepository;

    @Autowired
    private AdministrationMethodRepository administrationMethodRepository;

    @Autowired
    private MedicationScheduleMapper medicationScheduleMapper;

    public List<MedicationScheduleDto> findAll() {
        List<MedicationSchedule> schedules = medicationScheduleRepository.findAll();
        return schedules.stream()
                .map(medicationScheduleMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<MedicationScheduleDto> findByUserEmail(String email) {
        List<MedicationSchedule> schedules = medicationScheduleRepository.findByUser_Email(email);
        return schedules.stream()
                .map(medicationScheduleMapper::toDto)
                .collect(Collectors.toList());
    }

    public MedicationScheduleDto save(MedicationScheduleDto dto) {
        MedicationSchedule schedule = medicationScheduleMapper.toEntity(dto);
        schedule.setAdministrationMethod(administrationMethodRepository.save(schedule.getAdministrationMethod()));
        MedicationSchedule savedSchedule = medicationScheduleRepository.save(schedule);
        return medicationScheduleMapper.toDto(savedSchedule);
    }

    public MedicationScheduleDto findById(Long id) {
        return medicationScheduleRepository.findById(id)
                .map(medicationScheduleMapper::toDto)
                .orElse(null);
    }

    public void deleteById(Long id) {
        medicationScheduleRepository.deleteById(id);
    }
}
