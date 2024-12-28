package com.healthcare.doctor_service.service;

import com.healthcare.doctor_service.domain.Doctor;
import com.healthcare.doctor_service.repository.DoctorRepository;
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
        return doctorRepository.save(doctor);
    }

//    @Override
//    public boolean checkIfDoctorExists(String doctorId) {
//        return doctorRepository.existsById(doctorId);
//    }

//    @Override
//    public List<Doctor> getDoctorsByOpDays(String[] days) {
//        return List.copyOf(doctorRepository.findAllByOpDaysContains(days));
//    }

    @Override
    public List<Doctor> getAllDoctors() {
        return List.copyOf(doctorRepository.findAll());
    }

    @Override
    public Optional<Doctor> getDoctor(String id) {
        return doctorRepository.findById(Long.valueOf(id));
    }
}
