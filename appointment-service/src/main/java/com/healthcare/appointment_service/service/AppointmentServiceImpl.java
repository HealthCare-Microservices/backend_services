package com.healthcare.appointment_service.service;

import com.healthcare.appointment_service.domain.Appointment;
import com.healthcare.appointment_service.dto.Doctor;
import com.healthcare.appointment_service.dto.Patient;
import com.healthcare.appointment_service.feign_clients.DoctorServiceClient;
import com.healthcare.appointment_service.feign_clients.PatientServiceClient;
import com.healthcare.appointment_service.repository.AppointmentRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorServiceClient doctorServiceClient;
    private final PatientServiceClient patientServiceClient;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, DoctorServiceClient doctorServiceClient, PatientServiceClient patientServiceClient) {
        this.appointmentRepository = appointmentRepository;
        this.doctorServiceClient = doctorServiceClient;
        this.patientServiceClient = patientServiceClient;
    }

    @Override
    public Appointment createAppointment(Appointment appointment) {
        try {
            return appointmentRepository.save(appointment);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error creating appointment: " + e.getMessage(), e);
        }
    }

    @Override
    public Appointment getAppointmentById(String id) {
        try {
            Long appointmentId = Long.valueOf(id);
            return appointmentRepository.findById(appointmentId)
                    .orElseThrow(() -> new IllegalArgumentException("Appointment with ID " + id + " not found."));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid appointment ID format: " + id, e);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error fetching appointment by ID: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Appointment> findAllByPatientId(String patientId) {
        try {
            Long parsedPatientId = Long.parseLong(patientId);
            return appointmentRepository.findAllByPatientId(parsedPatientId);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid patient ID format: " + patientId, e);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error fetching appointments for patient ID " + patientId + ": " + e.getMessage(), e);
        }
    }

    @Override
    public List<Appointment> findAllByDoctorId(String doctorId) {
        try {
            return appointmentRepository.findAllByDoctorId(doctorId);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error fetching appointments for doctor ID " + doctorId + ": " + e.getMessage(), e);
        }
    }

    @Override
    public List<Appointment> findAllByPatientIdAndDoctorId(String patientId, String doctorId) {
        try {
            Long parsedPatientId = Long.parseLong(patientId);
            return appointmentRepository.findAllByPatientIdAndDoctorId(parsedPatientId, doctorId);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid patient ID format: " + patientId, e);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error fetching appointments for patient ID " + patientId + " and doctor ID " + doctorId + ": " + e.getMessage(), e);
        }
    }

    @Override
    public Patient getPatientById(String patientId) {
        try {
            Long parsedPatientId = Long.parseLong(patientId);
            return patientServiceClient.getPatientById(parsedPatientId);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid patient ID format: " + patientId, e);
        } catch (RestClientException e) {
            throw new RuntimeException("Error fetching patient details for ID " + patientId + ": " + e.getMessage(), e);
        }
    }

    @Override
    public Doctor getDoctorById(String doctorId) {
        try {
            return doctorServiceClient.getDoctorById(doctorId);
        } catch (RestClientException e) {
            throw new RuntimeException("Error fetching doctor details for ID " + doctorId + ": " + e.getMessage(), e);
        }
    }
}
