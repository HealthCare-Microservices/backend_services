package com.healthcare.doctor_service.service;

import com.healthcare.doctor_service.domain.Doctor;
import com.healthcare.doctor_service.repository.DoctorRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public Doctor createDoctor(Doctor doctor) {
        try {
            return doctorRepository.save(doctor);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error creating doctor: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Doctor> getAllDoctors() {
        try {
            return List.copyOf(doctorRepository.findAll());
        } catch (DataAccessException e) {
            throw new RuntimeException("Error fetching all doctors: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<Doctor> getDoctor(String id) {
        try {
            return doctorRepository.findById(Long.valueOf(id));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid doctor ID format: " + id, e);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error fetching doctor by ID: " + e.getMessage(), e);
        }
    }


    @Override
    public String authenticateDoctor(String email, String password) {
        try {
            Optional<Doctor> doctor = doctorRepository.findByEmail(email);
            if (doctor.isEmpty()) {
                throw new IllegalArgumentException("Doctor with email " + email + " not found.");
            }
            if(doctor.get().getPassword().equals(password)) return doctor.get().getId()+"";
            return "";
        } catch (DataAccessException e) {
            throw new RuntimeException("Error during doctor authentication: " + e.getMessage(), e);
        }
    }
}
