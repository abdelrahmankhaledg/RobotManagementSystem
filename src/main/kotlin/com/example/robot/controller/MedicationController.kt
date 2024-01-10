package com.example.robot.controller

import com.example.robot.command.RegisterMedicationCommand
import com.example.robot.reponse.HttpResponse
import com.example.robot.reponse.HttpResponseWithPayload
import com.example.robot.reponse.enums.ResponseEnum
import com.example.robot.resource.RegisterMedicationResource
import com.example.robot.service.MedicationService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
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
    @Operation(summary = "Register a new medication")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "The medication has been registered successfully",
            content = [ Content(mediaType = "application/json",
                schema = Schema(implementation = HttpResponseWithPayload::class)
            ) ])])
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