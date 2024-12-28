package com.healthcare.appointment_service.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * {
 *     "id": 1,
 *     appointmentDate: "2021-09-01",
 *     appointmentTime: "10:00 AM",
 *     patientId: 1,
 *     doctorId: 1,
 *     status: "CONFIRMED"
 * }
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "appointments") // MySQL table name
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment for MySQL
    private Long id; // Change id type to Long for MySQL

    @Column(name = "appointment_date", nullable = false) // Map to appointment_date column
    private String appointmentDate;

    @Column(name = "appointment_time", nullable = false) // Map to appointment_time column
    private String appointmentTime;

    @Column(name = "patient_id", nullable = false) // Map to patient_id column
    private long patientId;

    @Column(name = "patient_name", nullable = false) // Map to patient_name column
    private String patientName;

    @Column(name = "patient_number", nullable = false) // Map to patient_number column
    private String patientNumber;

    @Column(name = "doctor_id", nullable = false) // Map to doctor_id column
    private String doctorId;

    @Column(name = "doctor_name", nullable = false) // Map to doctor_name column
    private String doctorName;

    @Column(nullable = false) // Default status column
    private String status;

    // Constructor with all fields except ID
    public Appointment(String appointmentDate, String appointmentTime, long patientId, String patientName,
                       String patientNumber, String doctorId, String doctorName, String status) {
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.patientId = patientId;
        this.patientName = patientName;
        this.patientNumber = patientNumber;
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.status = status;
    }

    // Optional: Add any additional logic, validations, or toDto() methods as needed
    public void toDto() {
        // Logic to convert to a DTO (if required)
    }
}
