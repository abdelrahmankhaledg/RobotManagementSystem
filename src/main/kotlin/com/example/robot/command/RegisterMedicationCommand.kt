package com.example.robot.command

import com.example.robot.resource.RegisterMedicationResource


data class RegisterMedicationCommand(

    val name : String,
    val weight : Int,
    val code : String,
    val imageUrl : String

) {
    constructor(registerMedicationResource: RegisterMedicationResource): this(
        name = registerMedicationResource.name!!,
        weight = registerMedicationResource.weight!!,
        code = registerMedicationResource.code!!,
        imageUrl = registerMedicationResource.imageUrl!!
    )

}