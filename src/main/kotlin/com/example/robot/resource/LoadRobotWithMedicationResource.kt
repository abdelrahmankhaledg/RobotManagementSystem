package com.example.robot.resource

import com.example.robot.validator.robot.SerialNumber
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotEmpty
import org.springframework.validation.annotation.Validated

@Validated
data class LoadRobotWithMedicationResource (
    @SerialNumber
    @Schema(description = "The serial number of the robot", required = true, example = "ABC1")
    val serialNumber : String?,

    @field:NotEmpty(message = "The list of medications to be loaded cannot be empty")
    @Schema(description = "The names of medications to be loaded", required = true,
        example = "[\"ADOL\",\"PANADOL\",\"BRUFEN\"]")
    val medicationNames : List<String>?
){
}