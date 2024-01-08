package com.example.robot.controller

import com.example.robot.command.RegisterMedicationCommand
import com.example.robot.reponse.HttpResponse
import com.example.robot.reponse.HttpResponseWithPayload
import com.example.robot.reponse.enums.ResponseEnum
import com.example.robot.resource.RegisterMedicationResource
import com.example.robot.service.MedicationService
import jakarta.validation.Valid
import jakarta.validation.constraints.NotNull
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/medication")
class MedicationController(
    @Autowired val medicationService : MedicationService
) {
    @PostMapping("/register")
    fun registerMedication(
        @NotNull @Valid @RequestBody registerMedicationResource: RegisterMedicationResource
    ) : ResponseEntity<HttpResponseWithPayload> {
        val registerMedicationCommand : RegisterMedicationCommand = RegisterMedicationCommand(registerMedicationResource)
        val registeredMedication = medicationService.registerMedication(registerMedicationCommand)
        return ResponseEntity(
            HttpResponseWithPayload(
                HttpResponse(
                    description = ResponseEnum.SUCCESS.description,
                    code = ResponseEnum.SUCCESS.code
                ),
                payLoad = registeredMedication
            ),
            ResponseEnum.SUCCESS.httpStatus
        )
    }

}