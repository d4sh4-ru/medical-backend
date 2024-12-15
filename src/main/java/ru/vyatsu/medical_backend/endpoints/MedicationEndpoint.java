package ru.vyatsu.medical_backend.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vyatsu.medical_backend.DTO.DietarySupplementDto;
import ru.vyatsu.medical_backend.DTO.MedicationDto;
import ru.vyatsu.medical_backend.services.MedicationService;

import java.util.List;

@RestController
@RequestMapping("/api/medications")
public class MedicationEndpoint {
    @Autowired
    private MedicationService medicationService;

    /**
     * Получить все названия лекарств.
     */
    @GetMapping("/names")
    public ResponseEntity<List<String>> getAllMedicationsNames() {
        List<String> medicationNames = medicationService.findAllNames();
        return ResponseEntity.ok(medicationNames);
    }

    /**
     * Получить все лекарства.
     */
    @GetMapping
    public ResponseEntity<List<MedicationDto>> getMedications() {
        List<MedicationDto> medications = medicationService.findAll();
        return ResponseEntity.ok(medications);
    }

    /**
     * Получить лекарство по торговому наименованию.
     */
    @GetMapping("/{tradeName}")
    public ResponseEntity<MedicationDto> getMedication(@PathVariable String tradeName) {
        MedicationDto medication = medicationService.findByTradeName(tradeName);
        return ResponseEntity.ok(medication);
    }

    /**
     * Создать БАД.
     */
    @PostMapping
    public ResponseEntity<MedicationDto> createMedication(@RequestBody DietarySupplementDto dietarySupplementDto) {
        MedicationDto savedMedication = medicationService.save(dietarySupplementDto);
        return ResponseEntity.ok(savedMedication);
    }
}
