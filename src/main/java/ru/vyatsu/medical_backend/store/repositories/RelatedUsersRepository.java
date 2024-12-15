package ru.vyatsu.medical_backend.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vyatsu.medical_backend.store.entities.RelatedUsers;

public interface RelatedUsersRepository extends JpaRepository<RelatedUsers, Long> {
}
