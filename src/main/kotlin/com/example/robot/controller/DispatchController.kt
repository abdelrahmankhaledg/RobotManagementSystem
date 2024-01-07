package com.example.robot.controller

import com.example.robot.command.LoadRobotWithMedicationCommand
import com.example.robot.reponse.HttpResponse
import com.example.robot.reponse.enums.ResponseEnum
import com.example.robot.resource.LoadRobotWithMedicationResource
import com.example.robot.service.CarriedMedicationService
import jakarta.validation.Valid
import jakarta.validation.constraints.NotNull

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/robot")
@Validated
class DispatchController(
    @Autowired val carriedMedicationService: CarriedMedicationService
) {
    @PostMapping("/load")
    fun loadRobotWithMedication(
        @NotNull @Valid @RequestBody loadRobotWithMedicationResource: LoadRobotWithMedicationResource
    ) : ResponseEntity<HttpResponse>
    {
        val loadRobotWithMedicationCommand : LoadRobotWithMedicationCommand = LoadRobotWithMedicationCommand(loadRobotWithMedicationResource)
        carriedMedicationService.loadRobotWithMedication(
            loadRobotWithMedicationCommand.serialNumber,
            loadRobotWithMedicationCommand.medicationNames);
        return ResponseEntity(
            HttpResponse(
                description = ResponseEnum.SUCCESS.description,
                code = ResponseEnum.SUCCESS.code,
                ),
            ResponseEnum.SUCCESS.httpStatus
        )
    }
}