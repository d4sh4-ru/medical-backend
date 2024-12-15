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
@Table(name = "related_user", schema = "core")
public class RelatedUsers {
    @EmbeddedId
    private RelatedUsersId id;

    @Column(name = "notification_settings", columnDefinition = "json")
    private String notificationSettings;

    @Column(name = "is_deleted")
    private Boolean isDeleted = false;

    @Embeddable
    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RelatedUsersId implements Serializable {
        @ManyToOne
        @JoinColumn(name = "patient_email", referencedColumnName = "email")
        private User patient;

        @ManyToOne
        @JoinColumn(name = "related_email", referencedColumnName = "email")
        private User related;
    }
}
