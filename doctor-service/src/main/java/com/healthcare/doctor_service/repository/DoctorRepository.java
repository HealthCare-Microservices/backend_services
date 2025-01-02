package com.healthcare.doctor_service.repository;

import com.healthcare.doctor_service.domain.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    /**
     * Find all doctors by operating days containing a specific day.
     *
     * @param day The day to search for.
     * @return A list of doctors operating on the specified day.
     */
    List<Doctor> findAllByOpDaysContaining(String day);

    Optional<Doctor> findByEmail(String email);
}
