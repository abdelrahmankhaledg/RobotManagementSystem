package com.example.robot.resource.robot

import com.example.robot.validator.robot.SerialNumber
import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import org.springframework.validation.annotation.Validated

@Validated
data class LoadRobotWithMedicationResource (
    @SerialNumber
    val serialNumber : String?,

    @NotEmpty(message = "The list of medications to be loaded cannot be empty")
    @Valid
    val medicationNames : List<@NotBlank(message = "Medication names cannot be empty") String>?
){}