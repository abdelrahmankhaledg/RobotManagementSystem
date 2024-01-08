package com.example.robot.resource

import com.example.robot.validator.medication.MedicationCode
import com.example.robot.validator.medication.MedicationName
import com.example.robot.validator.medication.MedicationWeight
import org.hibernate.validator.constraints.URL
import org.springframework.validation.annotation.Validated

@Validated
data class RegisterMedicationResource (

    @MedicationName
    val name : String?,

    @MedicationWeight
    val weight : Int?,

    @MedicationCode
    val code : String?,

    @URL
    val imageUrl : String?

){

}