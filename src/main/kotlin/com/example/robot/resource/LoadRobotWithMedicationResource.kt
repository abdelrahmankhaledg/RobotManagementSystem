package com.example.robot.resource

import com.example.robot.validator.robot.SerialNumber
import jakarta.validation.constraints.NotEmpty
import org.springframework.validation.annotation.Validated

@Validated
data class LoadRobotWithMedicationResource (
    @SerialNumber
    val serialNumber : String?,

    @field:NotEmpty(message = "The list of medications to be loaded cannot be empty")
    val medicationNames : List<String>?
){
}