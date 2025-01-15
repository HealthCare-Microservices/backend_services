package com.healthcare.appointment_service.feign_clients;

import com.healthcare.appointment_service.dto.Patient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "PATIENT-SERVICE", url = "http://localhost:8202/patients")
public interface PatientServiceClient {

    @GetMapping("/{id}")
    public Patient getPatientById(@PathVariable long id);

}
