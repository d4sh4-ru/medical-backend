package ru.vyatsu.medical_backend.store.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Users", schema = "core")
public class User {
    @Id
    @Column(nullable = false, length = 50)
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
    private String email;

    @Column(nullable = false, length = 30)
    @Pattern(regexp = "^[А-Яа-я]+$")
    private String lastName;

    @Column(nullable = false, length = 30)
    @Pattern(regexp = "^[А-Яа-я]+$")
    private String firstName;

    @Column(nullable = true, length = 30)
    @Pattern(regexp = "^[А-Яа-я]+$")
    private String middleName;

    @Column(nullable = true, length = 12)
    @Pattern(regexp = "^\\+7[0-9]{10}$")
    private String phoneNumber;

    @Column(nullable = false, length = 60)
    private String passwordHash;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;

    @Column(nullable = false)
    private boolean isDeleted;
}
