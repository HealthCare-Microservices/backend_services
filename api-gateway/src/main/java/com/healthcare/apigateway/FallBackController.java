package com.healthcare.apigateway;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackController {

    @GetMapping("/doctorServiceFallBack")
    public String doctorServiceFallBackMethod() {
        return "Doctor Service is taking longer than Expected." +
                " Please try again later";
    }

    @GetMapping("/patientServiceFallBack")
    public String patientServiceFallBackMethod() {
        return "Patient Service is taking longer than Expected." +
                " Please try again later";
    }

    @GetMapping("/appointmentServiceFallBack")
    public String appointmentServiceFallBackMethod() {
        return "Appointment Service is taking longer than Expected." +
                " Please try again later";
    }
}
