package ru.vyatsu.medical_backend.store.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "medicationreleaseforms", schema = "core")
public class MedicationReleaseForm {
    @EmbeddedId
    private MedicationReleaseFormKey id;

    @Embeddable
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MedicationReleaseFormKey implements Serializable {
        @Column(name = "medication_id", nullable = false, length = 255)
        private String medicationId;

        @Column(name = "release_form_id", nullable = false)
        private Integer releaseFormId;
    }
}

