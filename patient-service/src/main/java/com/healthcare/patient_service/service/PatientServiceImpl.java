package com.healthcare.patient_service.service;

import com.healthcare.patient_service.domain.Patient;
import com.healthcare.patient_service.exceptions.DuplicatePatientException;
import com.healthcare.patient_service.exceptions.PatientNotFoundException;
import com.healthcare.patient_service.repository.PatientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public Patient createPatient(Patient patient) {
        log.debug("Creating patient: {}", patient);
        try {
            patientRepository
                .findByEmailOrPhone(patient.getEmail(), patient.getPhone())
                .ifPresent(existingPatient -> {
                    log.error("Patient already exists with email: {} or phone: {}",
                            existingPatient.getEmail(), existingPatient.getPhone());
                    throw new DuplicatePatientException("Patient already exists with email: " +
                            existingPatient.getEmail() + " or phone: " + existingPatient.getPhone());
                });
            log.debug("Patient does not exist with email: {} or phone: {}", patient.getEmail(), patient.getPhone());
            return patientRepository.save(patient);
        } catch (DataAccessException e) {
            log.error("Database error while creating patient: {}", e.getMessage());
            throw new RuntimeException("Database error while creating patient: " + e.getMessage(), e);
        }
    }

    @Override
    public Patient getPatient(long id) {
        log.debug("Getting patient, id: {}", id);
        try {
            return patientRepository.findById(id)
                    .orElseThrow(() -> new PatientNotFoundException("Patient not found, id: " + id));
        } catch (DataAccessException e) {
            log.error("Database error while retrieving patient with id {}: {}", id, e.getMessage());
            throw new RuntimeException("Database error while retrieving patient: " + e.getMessage(), e);
        }
    }

    @Override
    public Patient updatePatient(Patient patient) {
        log.debug("Updating patient: {}", patient);
        try {
            if (!patientRepository.existsById(patient.getId())) {
                throw new PatientNotFoundException("Patient not found, cannot update, id: " + patient.getId());
            }
            return patientRepository.save(patient);
        } catch (DataAccessException e) {
            log.error("Database error while updating patient: {}", e.getMessage());
            throw new RuntimeException("Database error while updating patient: " + e.getMessage(), e);
        }
    }

    @Override
    public void deletePatient(long id) {
        log.debug("Deleting patient, id: {}", id);
        try {
            var patient = patientRepository
                    .findById(id)
                    .orElseThrow(() -> new PatientNotFoundException("Patient not found, cannot delete, id: " + id));
            patientRepository.delete(patient);
        } catch (DataAccessException e) {
            log.error("Database error while deleting patient with id {}: {}", id, e.getMessage());
            throw new RuntimeException("Database error while deleting patient: " + e.getMessage(), e);
        }
    }

    @Override
    public Patient searchByEmail(String email) {
        log.debug("Searching patient by email: {}", email);
        try {
            return patientRepository.findByEmail(email)
                    .orElseThrow(() ->
                            new PatientNotFoundException("Patient not found, email: " + email));
        } catch (DataAccessException e) {
            log.error("Database error while searching for patient by email {}: {}", email, e.getMessage());
            throw new RuntimeException("Database error while searching patient by email: " + e.getMessage(), e);
        }
    }

    @Override
    public Patient searchByPhone(String phone) {
        log.debug("Searching patient by phone: {}", phone);
        try {
            return patientRepository.findByPhone(phone)
                    .orElseThrow(() ->
                            new PatientNotFoundException("Patient not found, phone: " + phone));
        } catch (DataAccessException e) {
            log.error("Database error while searching for patient by phone {}: {}", phone, e.getMessage());
            throw new RuntimeException("Database error while searching patient by phone: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Patient> getAllPatients() {
        log.debug("Getting all patients");
        try {
            return List.copyOf(patientRepository.findAll());
        } catch (DataAccessException e) {
            log.error("Database error while fetching all patients: {}", e.getMessage());
            throw new RuntimeException("Database error while fetching all patients: " + e.getMessage(), e);
        }
    }
}
