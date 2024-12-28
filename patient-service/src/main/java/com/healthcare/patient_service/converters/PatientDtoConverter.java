package com.healthcare.patient_service.converters;

import com.healthcare.patient_service.domain.Illness;
import com.healthcare.patient_service.domain.Patient;
import com.healthcare.patient_service.dto.PatientDto;
import org.springframework.stereotype.Component;

@Component
public class PatientDtoConverter {

    public PatientDto toDto(Patient patient) {
        return new PatientDto(patient.getId(), patient.getFullName(),
                patient.getEmail(), patient.getPhone(), patient.getAddress(),
                patient.getDob(),
                patient.getIllnesses() != null ? patient.getIllnesses().stream().map(Illness::getIllnessName).toList() : null);
    }

    public Patient toEntity(PatientDto dto) {
        Patient patient = new Patient();
        patient.setFullName(dto.fullName());
        patient.setEmail(dto.email());
        patient.setPhone(dto.phone());
        patient.setAddress(dto.address());
        patient.setDob(dto.dob());
        if (!dto.illnesses().isEmpty()) {
            patient.setIllnesses(dto.illnesses().stream().map(Illness::new).toList());
        }
        return patient;
    }
}
