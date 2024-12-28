package com.healthcare.appointment_service.service;

import com.healthcare.appointment_service.domain.Appointment;
import com.healthcare.appointment_service.dto.Doctor;
import com.healthcare.appointment_service.dto.Patient;
import com.healthcare.appointment_service.feign_clients.DoctorServiceClient;
import com.healthcare.appointment_service.feign_clients.PatientServiceClient;
import com.healthcare.appointment_service.repository.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
        appointmentRepository.save(appointment);
        return appointment;
    }

    @Override
    public Appointment getAppointmentById(String id) {
        return appointmentRepository.findById(Long.valueOf(id)).orElse(null);
    }

    @Override
    public List<Appointment> findAllByPatientId(String patientId) {
        return appointmentRepository.findAllByPatientId(Long.parseLong(patientId));
    }

    @Override
    public List<Appointment> findAllByDoctorId(String doctorId) {
        return appointmentRepository.findAllByDoctorId(doctorId);
    }

    @Override
    public List<Appointment> findAllByPatientIdAndDoctorId(String patientId, String doctorId) {
        return appointmentRepository.findAllByPatientIdAndDoctorId(Long.parseLong(patientId), doctorId);
    }

    @Override
    public Patient getPatientById(String patientId) {
        return patientServiceClient.getPatientById(Long.parseLong(patientId));
    }

    @Override
    public Doctor getDoctorById(String doctorId) {
        return doctorServiceClient.getDoctorById(doctorId);
    }
}
