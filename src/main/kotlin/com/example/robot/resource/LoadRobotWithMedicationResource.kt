package com.example.robot.resource

import com.example.robot.validator.SerialNumber
import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import org.springframework.validation.annotation.Validated

@Validated
data class LoadRobotWithMedicationResource (
    @field:NotNull(message = "The serial number of the robot cannot be null")
    @field:SerialNumber
    val serialNumber : String?,

    @field:NotNull(message = "The list of medications to be loaded cannot be null")
    @field:Valid
    val medicationNames : List<@NotBlank(message = "Medication names cannot be empty") String>?
){}