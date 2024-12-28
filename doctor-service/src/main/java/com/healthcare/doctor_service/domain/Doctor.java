package com.healthcare.doctor_service.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * Structure of the Doctor entity
 * {
 *     "id": 1,
 *     "fullName": "Dr. John Doe",
 *     "email": "john.doe@hospital.com",
 *     "phone": "123-456-7890",
 *     "address": "123 Main St, City, State, 12345",
 *     "specialization": ["Cardiology", "Neurology"],
 *     "op_days": ["Monday", "Wednesday", "Friday"],
 * }
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "doctors")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String phone;

    @Column(nullable = false)
    private String address;

    @ElementCollection
    @CollectionTable(name = "doctor_specializations", joinColumns = @JoinColumn(name = "doctor_id"))
    @Column(name = "specialization")
    private List<String> specialization;

    @ElementCollection
    @CollectionTable(name = "doctor_op_days", joinColumns = @JoinColumn(name = "doctor_id"))
    @Column(name = "op_day")
    private List<String> opDays;

    public Doctor(String fullName, String email, String phone, String address,
                  List<String> specialization, List<String> opDays) {
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.specialization = specialization;
        this.opDays = opDays;
    }
}
