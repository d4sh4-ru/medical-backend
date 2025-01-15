package ru.vyatsu.medical_backend.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vyatsu.medical_backend.store.entities.MedicationRestock;
import ru.vyatsu.medical_backend.store.entities.User;

import java.util.List;

public interface MedicationRestockRepository extends JpaRepository<MedicationRestock, Integer> {
    List<MedicationRestock> findByUser(User user);
}
