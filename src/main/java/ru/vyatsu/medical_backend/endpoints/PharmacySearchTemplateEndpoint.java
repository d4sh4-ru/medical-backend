package ru.vyatsu.medical_backend.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vyatsu.medical_backend.DTO.CoordinatesDto;
import ru.vyatsu.medical_backend.store.entities.PharmacySearchTemplate;
import ru.vyatsu.medical_backend.services.PharmacySearchTemplateService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pharmacy-search-templates")
public class PharmacySearchTemplateEndpoint {

    @Autowired
    private PharmacySearchTemplateService pharmacySearchTemplateService;

    @GetMapping("/nearest")
    public ResponseEntity<List<String>> findNearest(@RequestBody CoordinatesDto coordinatesDto) {
        List<String> urls = pharmacySearchTemplateService.findNearest(coordinatesDto);
        return ResponseEntity.ok(urls);
    }

    // Заблокировать только для админа
    @GetMapping
    public ResponseEntity<List<PharmacySearchTemplate>> getAllPharmacySearchTemplates() {
        List<PharmacySearchTemplate> pharmacySearchTemplates = pharmacySearchTemplateService.findAll();
        return ResponseEntity.ok(pharmacySearchTemplates);
    }

    // Заменить на поиск по name
    @GetMapping("/{id}")
    public ResponseEntity<PharmacySearchTemplate> getPharmacySearchTemplateByName(@PathVariable Long id) {
        Optional<PharmacySearchTemplate> pharmacySearchTemplate = pharmacySearchTemplateService.findById(id);
        return ResponseEntity.ok(pharmacySearchTemplate.orElseThrow());
    }

    // Заблокировать только для админа
    @PostMapping
    public ResponseEntity<PharmacySearchTemplate> createPharmacySearchTemplate(@RequestBody PharmacySearchTemplate pharmacySearchTemplate) {
        PharmacySearchTemplate savedPharmacySearchTemplate = pharmacySearchTemplateService.save(pharmacySearchTemplate);
        return ResponseEntity.ok(savedPharmacySearchTemplate);
    }

    // Заблокировать только для админа
    @PutMapping("/{id}")
    public ResponseEntity<PharmacySearchTemplate> updatePharmacySearchTemplate(@RequestBody PharmacySearchTemplate pharmacySearchTemplate) {
        PharmacySearchTemplate updatedPharmacySearchTemplate = pharmacySearchTemplateService.update(pharmacySearchTemplate);
        return ResponseEntity.ok(updatedPharmacySearchTemplate);
    }

    // Заблокировать только для админа
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePharmacySearchTemplate(@PathVariable Long id) {
        pharmacySearchTemplateService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
