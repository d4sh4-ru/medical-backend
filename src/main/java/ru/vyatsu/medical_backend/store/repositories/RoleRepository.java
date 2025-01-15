package ru.vyatsu.medical_backend.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vyatsu.medical_backend.store.entities.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String name);
}
