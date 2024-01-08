package com.example.robot.resource

import com.example.robot.validator.robot.SerialNumber
import org.springframework.validation.annotation.Validated

@Validated
data class CheckRobotBatteryLevelResource(
    @SerialNumber
    val serialNumber: String?
) {}