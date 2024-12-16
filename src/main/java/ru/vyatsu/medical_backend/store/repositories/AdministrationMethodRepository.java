package ru.vyatsu.medical_backend.store.repositories;

import jakarta.validation.constraints.Pattern;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.vyatsu.medical_backend.store.entities.AdministrationMethod;

import java.util.List;
import java.util.Optional;

public interface AdministrationMethodRepository extends JpaRepository<AdministrationMethod, Integer> {
    List<AdministrationMethod> findAdministrationMethodByMedication_Id(Integer id);

    Optional<AdministrationMethod> findAdministrationMethodByMedication_IdAndSingleDosageAndIntervalAndAdministrationTimes
            (Integer medication_id,
             @Pattern(regexp = "^[0-9]+ ?[A-Za-zА-Яа-я]+$") String singleDosage,
             Integer interval,
             String administrationTimes
            );
}

