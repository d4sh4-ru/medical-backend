package ru.vyatsu.medical_backend.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vyatsu.medical_backend.DTO.AdministrationMethodDto;
import ru.vyatsu.medical_backend.services.AdministrationMethodService;

import java.util.List;

@RestController
@RequestMapping("/api/administration-method")
public class AdministrationMethodEndpoint {
    @Autowired
    AdministrationMethodService administrationMethodService;

    @GetMapping("/medication/{id}")
    public ResponseEntity<List<AdministrationMethodDto>> getByMedicalId(@PathVariable Integer id) {
        List<AdministrationMethodDto> administrationMethods = administrationMethodService.getAllAdministrationMethodsByMedicalId(id);
        return ResponseEntity.ok(administrationMethods);
    }
}
