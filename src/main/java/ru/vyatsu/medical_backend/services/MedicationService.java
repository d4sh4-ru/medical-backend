package ru.vyatsu.medical_backend.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vyatsu.medical_backend.DTO.DietarySupplementDto;
import ru.vyatsu.medical_backend.DTO.MedicationDto;
import ru.vyatsu.medical_backend.mappers.MedicationMapper;
import ru.vyatsu.medical_backend.store.entities.Medication;
import ru.vyatsu.medical_backend.store.repositories.MedicationRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicationService {
    @Autowired
    private MedicationRepository medicationRepository;

    @Autowired
    private MedicationMapper medicationMapper;

    public List<MedicationDto> findAll() {
        List<Medication> medications = medicationRepository.findAll();
        return medications.stream().map(medicationMapper::toDto).toList();
    }

    public List<String> findAllNames() {
        List<Medication> medications = medicationRepository.findAll();
        return medications.stream()
                .map(Medication::getTradeName)
                .distinct()
                .collect(Collectors.toList());
    }

    public MedicationDto findByTradeName(String tradeName) {
        Medication medication = medicationRepository.findByTradeName(tradeName)
                .orElseThrow(() -> new EntityNotFoundException("Medication not found"));
        return medicationMapper.toDto(medication);
    }

    public MedicationDto save(DietarySupplementDto dietarySupplementDto) {
        Medication savedMedication = medicationRepository.save(medicationMapper.dietarySupplementToMedication(dietarySupplementDto));
        return medicationMapper.toDto(savedMedication);
    }
}
