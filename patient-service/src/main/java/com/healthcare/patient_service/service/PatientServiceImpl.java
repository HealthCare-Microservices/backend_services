package com.healthcare.patient_service.service;

import com.healthcare.patient_service.domain.Patient;
import com.healthcare.patient_service.exceptions.DuplicatePatientException;
import com.healthcare.patient_service.exceptions.PatientNotFoundException;
import com.healthcare.patient_service.repository.PatientRepository;
import lombok.extern.slf4j.Slf4j;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PatientServiceImpl implements PatientService {

//    Alternative to using lombok's @Slf4j annotation
//    private Logger log = LoggerFactory.getLogger(PatientServiceImpl.class);

    private final PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public Patient createPatient(Patient patient) {
        log.debug("Creating patient: {}", patient);
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
    }

    @Override
    public Patient getPatient(long id) {
        log.debug("Getting patient, id: {}", id);
        return patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found, id: " + id));
    }

    @Override
    public Patient updatePatient(Patient patient) {
        log.debug("Updating patient: {}", patient);
        return patientRepository.save(patient);
    }

    @Override
    public void deletePatient(long id) {
        log.debug("Deleting patient, id: {}", id);
        var patient = patientRepository
                .findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found, cannot delete, id: " + id));
        patientRepository.delete(patient);
    }

    @Override
    public Patient searchByEmail(String email) {
        log.debug("Searching patient by email: {}", email);
        return patientRepository.findByEmail(email)
                .orElseThrow(() ->
                    new PatientNotFoundException("Patient not found, email: " + email));
    }

    @Override
    public Patient searchByPhone(String phone) {
        log.debug("Searching patient by phone: {}", phone);
        return patientRepository.findByPhone(phone)
                .orElseThrow(() ->
                    new PatientNotFoundException("Patient not found, phone: " + phone));
    }

    @Override
    public List<Patient> getAllPatients() {
        log.debug("Getting all patients");
        return List.copyOf(patientRepository.findAll());
    }
}
