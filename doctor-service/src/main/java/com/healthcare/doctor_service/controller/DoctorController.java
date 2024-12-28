package com.healthcare.doctor_service.controller;

import com.healthcare.doctor_service.dto.DoctorDto;
import com.healthcare.doctor_service.service.DoctorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping
    public ResponseEntity<DoctorDto> createDoctor(@RequestBody DoctorDto dto) {
        var doctor = doctorService.createDoctor(dto.toDoctor());
        return ResponseEntity.status(HttpStatus.CREATED).body(DoctorDto.fromEntity(doctor));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorDto> getDoctor(@PathVariable String id) {
        var doctor = doctorService.getDoctor(id).orElseThrow();
        return ResponseEntity.ok(DoctorDto.fromEntity(doctor));
    }

    @GetMapping
    public ResponseEntity<List<DoctorDto>> getAllDoctors() {
        var doctors = doctorService.getAllDoctors();
        if(doctors.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(DoctorDto.toDtos(doctors));
    }
}
