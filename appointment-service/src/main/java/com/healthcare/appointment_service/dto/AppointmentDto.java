package com.healthcare.appointment_service.dto;

import com.healthcare.appointment_service.domain.Appointment;

import java.util.List;

public record AppointmentDto(Long id, String appointmentDate, String appointmentTime,
                             String patientId, String patientName, String patientNumber,
                             String doctorId, String doctorName, String status) {

    public static AppointmentDto fromAppointment(Appointment response) {
        return new AppointmentDto( response.getId(), response.getAppointmentDate(),
                response.getAppointmentTime(), String.valueOf(response.getPatientId()), response.getPatientName(),
                response.getPatientNumber(), response.getDoctorId(), response.getDoctorName(),
                response.getStatus());
    }

    public Appointment toAppointment() {
        return new Appointment( appointmentDate, appointmentTime, Long.parseLong(patientId),
                patientName, patientNumber, doctorId, doctorName, status);
    }

    public static List<AppointmentDto> toDtos(List<Appointment> appointments) {
        return appointments.stream().map(AppointmentDto::fromAppointment).toList();
    }

}
