package com.healthcare.patient_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.util.List;

public record PatientDto(
        long id,

        @NotEmpty(message = "Name is required")
        @Length(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
        String fullName,

        @NotEmpty(message = "Email is required")
        @Email(message = "Email is invalid")
        String email,

        @NotEmpty(message = "Phone is required")
        @Pattern(regexp = "^\\d{10}$", message = "Phone is invalid")
        String phone,

        @NotEmpty(message = "Address is required")
        String address,

        @Past(message = "Date of birth must be in the past")
        LocalDate dob,

        List<String> illnesses
) {
}
