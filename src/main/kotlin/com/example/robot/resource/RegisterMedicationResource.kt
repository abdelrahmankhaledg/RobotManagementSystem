package com.example.robot.resource

import com.example.robot.validator.medication.MedicationCode
import com.example.robot.validator.medication.MedicationName
import com.example.robot.validator.medication.MedicationWeight
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import org.springframework.validation.annotation.Validated

@Validated
data class RegisterMedicationResource (

    @MedicationName
    @Schema(description = "The name of the medication", required = true, example = "PANADOL")
    val name : String?,

    @MedicationWeight
    @Schema(description = "The weight of the medication", required = true, example = "30")
    val weight : Int?,

    @MedicationCode
    @Schema(description = "The code of the medication", required = true, example = "ABC_123")
    val code : String?,

    @field:NotBlank(message = "The url of the image cannot be empty")
    @Schema(description = "The URL of the image of the medication", required = true, example = "https://www.google.com")
    val imageUrl : String?

){

}