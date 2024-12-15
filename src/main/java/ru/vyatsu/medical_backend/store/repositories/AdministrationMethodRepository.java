package ru.vyatsu.medical_backend.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vyatsu.medical_backend.store.entities.AdministrationMethod;

import java.util.List;

public interface AdministrationMethodRepository extends JpaRepository<AdministrationMethod, Integer> {
}

