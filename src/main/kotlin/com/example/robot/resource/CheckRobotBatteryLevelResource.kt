package com.example.robot.resource

import com.example.robot.validator.robot.SerialNumber
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.validation.annotation.Validated

@Validated
data class CheckRobotBatteryLevelResource(
    @SerialNumber
    @Schema(description = "The serial number of the robot", required = true, example = "ABC1")
    val serialNumber: String?
) {}