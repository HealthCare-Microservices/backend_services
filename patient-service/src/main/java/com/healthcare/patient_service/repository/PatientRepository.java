package com.healthcare.patient_service.repository;

import com.healthcare.patient_service.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    // findByPropertyName

    // select * from patients where email = 'email'
    // @Query("select p from Patient p where p.email = :email")
    Optional<Patient> findByEmail(String email);

    // select * from patients where phone = 'phone'
    // @Query("select p from Patient p where p.phone = :phone")
    Optional<Patient> findByPhone(String phone);

    // select * from patients where phone = 'phone' OR email = 'email'
    // @Query("select p from Patient p where p.phone = :phone OR p.email = :email")
    Optional<Patient> findByEmailOrPhone(String email, String phone);

    // select * from patients where dob between startDate and endDate
    // @Query("select p from Patient p where p.dob between :startDate and :endDate")
    @Query(name = "Patient.findByDobBetween")
    List<Patient> findByDobBetween(LocalDate startDate, LocalDate endDate);

    // Find the Illnesses of a Patient
    // select * from patient_illness where patient_id = 'patientId'
    @Query("select p.illnesses from Patient p where p.id = :patientId")
    Optional<List<Patient>> findIllnessesOfPatient(@Param("patientId") long pid);

}
