package ru.vyatsu.medical_backend.DTO;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CoordinatesDto {
    private double latitude;
    private double longitude;
}
