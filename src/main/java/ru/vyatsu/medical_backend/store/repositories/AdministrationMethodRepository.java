package ru.vyatsu.medical_backend.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vyatsu.medical_backend.store.entities.AdministrationMethod;

import java.util.Optional;

public interface AdministrationMethodRepository extends JpaRepository<AdministrationMethod, Integer> {
    Optional<AdministrationMethod> findAdministrationMethodById(Integer id);
    Optional<AdministrationMethod> findAdministrationMethodByMedication_Id(Integer id);
}

