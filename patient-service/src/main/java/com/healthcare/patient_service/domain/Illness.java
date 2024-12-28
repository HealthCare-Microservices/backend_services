package com.healthcare.patient_service.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "patient_illness")
public class Illness {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "illness_name")
    private String illnessName;

    public Illness(String illnessName) {
        this.illnessName = illnessName;
    }
}
