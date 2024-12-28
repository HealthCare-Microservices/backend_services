package com.healthcare.appointment_service.repository;

import com.healthcare.appointment_service.domain.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    // Find appointments by patient ID (adjusting type to long for patientId)
    List<Appointment> findAllByPatientId(long patientId);

    // Find appointments by doctor ID (adjusting type to String for doctorId)
    List<Appointment> findAllByDoctorId(String doctorId);

    // Find appointments by both patient ID and doctor ID
    List<Appointment> findAllByPatientIdAndDoctorId(long patientId, String doctorId);

}
