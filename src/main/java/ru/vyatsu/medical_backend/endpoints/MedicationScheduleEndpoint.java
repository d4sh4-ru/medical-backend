package ru.vyatsu.medical_backend.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vyatsu.medical_backend.DTO.MedicationScheduleDto;
import ru.vyatsu.medical_backend.services.MedicationScheduleService;

import java.util.List;

@RestController
@RequestMapping("/api/medication-schedules")
public class MedicationScheduleEndpoint {

    @Autowired
    private MedicationScheduleService medicationScheduleService;

    @GetMapping
    public ResponseEntity<List<MedicationScheduleDto>> getAllSchedules() {
        List<MedicationScheduleDto> schedules = medicationScheduleService.findAll();
        return ResponseEntity.ok(schedules);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<List<MedicationScheduleDto>> getAllSchedulesByEmail(@PathVariable String email) {
        List<MedicationScheduleDto> schedules = medicationScheduleService.findByUserEmail(email);
        return ResponseEntity.ok(schedules);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<MedicationScheduleDto> getScheduleById(@PathVariable Long id) {
        MedicationScheduleDto schedule = medicationScheduleService.findById(id);
        return schedule != null ? ResponseEntity.ok(schedule) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<MedicationScheduleDto> createSchedule(@RequestBody MedicationScheduleDto dto) {
        MedicationScheduleDto savedSchedule = medicationScheduleService.save(dto);
        return ResponseEntity.ok(savedSchedule);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id) {
        medicationScheduleService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
