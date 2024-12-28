package com.healthcare.appointment_service.service;

import com.healthcare.appointment_service.domain.Appointment;
import com.healthcare.appointment_service.dto.Doctor;
import com.healthcare.appointment_service.dto.Patient;

import java.util.List;

public interface AppointmentService {

    Appointment createAppointment(Appointment appointment);

    public Appointment getAppointmentById(String id);

    List<Appointment> findAllByPatientId(String patientId);

    List<Appointment> findAllByDoctorId(String doctorId);

    List<Appointment> findAllByPatientIdAndDoctorId(String patientId, String doctorId);

    Patient getPatientById(String patientId);

    Doctor getDoctorById(String doctorId);

}