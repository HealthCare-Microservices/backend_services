package com.healthcare.appointment_service.feign_clients;

import com.healthcare.appointment_service.dto.Doctor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "doctor-service", url = "http://localhost:8200/api/v1/doctors")
public interface DoctorServiceClient {

    // GET /api/v1/doctor/{id}
    @GetMapping("/{id}")
    public Doctor getDoctorById(@PathVariable String id);
}
