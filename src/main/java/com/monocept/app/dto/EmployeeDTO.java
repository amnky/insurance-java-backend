package com.monocept.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    
	private Long employeeId;

    @NotBlank(message = "First name is mandatory")
    private String firstName;

    private String lastName;

    @NotNull(message = "Date of birth is mandatory")
    private LocalDate dateOfBirth;

    @NotBlank(message = "Qualification is mandatory")
    private String qualification;

    @NotNull(message = "Active Status is mandatory")
    private Boolean isActive;

    @NotNull(message = "Credentials are mandatory")
    private CredentialsResponseDTO credentials;
    

}
