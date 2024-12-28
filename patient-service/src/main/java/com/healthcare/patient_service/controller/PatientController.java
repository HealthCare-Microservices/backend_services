package com.healthcare.patient_service.controller;

import com.healthcare.patient_service.converters.PatientDtoConverter;
import com.healthcare.patient_service.domain.Patient;
import com.healthcare.patient_service.dto.PatientDto;
import com.healthcare.patient_service.service.PatientService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/patients")
@Slf4j
public class PatientController {

    private final PatientService patientService;
    private final PatientDtoConverter converter;

    public PatientController(PatientService patientService, PatientDtoConverter converter) {
        this.patientService = patientService;
        this.converter = converter;
    }

    @PostMapping
    public ResponseEntity<PatientDto> createPatient(@Valid @RequestBody PatientDto dto) {
        // TODO handle DuplicatePatientException
        // Create a new patient
        Patient patient = converter.toEntity(dto);
        // Save the patient to the database
        patient = patientService.createPatient(patient);
        var responseBody = converter.toDto(patient);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }

    // GET /api/v1/patients/{id}
    @GetMapping("/{id}")
    public ResponseEntity<PatientDto> getPatient(@PathVariable long id) {
        // TODO handle PatientNotFoundException
        // Get the patient from the database
        Patient patient = patientService.getPatient(id);
        var responseBody = converter.toDto(patient);
        return ResponseEntity.ok(responseBody);
    }

    // GET /api/v1/patients?k=value&v=123
    @GetMapping
    public ResponseEntity<PatientDto> searchForPatient(
            @RequestParam(name = "k") String key,
            @RequestParam(name="v") String data) {
        // TODO handle PatientNotFoundException
        // Search for the patient in the database
        Patient patient ;
        if (key.equals("email")) {
            patient = patientService.searchByEmail(data);
        } else {
            patient = patientService.searchByPhone(data);
        }
        var responseBody = converter.toDto(patient);
        return ResponseEntity.ok(responseBody);
    }

    // TODO Implement the updatePatient method
    // PUT /api/v1/patients/{id}

    // DELETE /api/v1/patients/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePatient(@PathVariable long id) {
        // TODO handle PatientNotFoundException
        // Delete the patient from the database
        patientService.deletePatient(id);
        return ResponseEntity.ok("Patient deleted");
    }

    // TODO Implement the getIllnessForPatient method
    // GET /api/v1/patients/{id}/illnesses

}
