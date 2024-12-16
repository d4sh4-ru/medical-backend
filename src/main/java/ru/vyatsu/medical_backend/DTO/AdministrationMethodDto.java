package ru.vyatsu.medical_backend.DTO;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdministrationMethodDto {
    private Integer id;
    private Integer medication;
    private String singleDosage;
    private Integer interval;
    private String administrationTimes;
}
