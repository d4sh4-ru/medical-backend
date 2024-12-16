package ru.vyatsu.medical_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vyatsu.medical_backend.DTO.AdministrationMethodDto;
import ru.vyatsu.medical_backend.mappers.AdministrationMethodMapper;
import ru.vyatsu.medical_backend.store.entities.AdministrationMethod;
import ru.vyatsu.medical_backend.store.repositories.AdministrationMethodRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdministrationMethodService {
    @Autowired
    private AdministrationMethodRepository administrationMethodRepository;

    @Autowired
    AdministrationMethodMapper administrationMethodMapper;

    public List<AdministrationMethodDto> getAllAdministrationMethodsByMedicalId(Integer medicalId) {
        List<AdministrationMethod> methods = administrationMethodRepository.findAdministrationMethodByMedication_Id(medicalId);
        return methods.stream().map(administrationMethodMapper::toAdministrationMethodDto).collect(Collectors.toList());
    }
}
