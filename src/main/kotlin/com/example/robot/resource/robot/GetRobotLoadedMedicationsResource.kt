package com.example.robot.resource.robot

import com.example.robot.validator.robot.SerialNumber
import org.springframework.validation.annotation.Validated

@Validated
data class GetRobotLoadedMedicationsResource(
    @SerialNumber
    val serialNumber: String?
) {}