package com.healthcare.doctor_service.service;

import com.healthcare.doctor_service.domain.Doctor;

import java.util.List;
import java.util.Optional;

public interface DoctorService {

    Doctor createDoctor(Doctor doctor);

//    boolean checkIfDoctorExists(String doctorId);
//
//    List<Doctor> getDoctorsByOpDays(String[] days);

    List<Doctor> getAllDoctors();

    Optional<Doctor> getDoctor(String id);
}
