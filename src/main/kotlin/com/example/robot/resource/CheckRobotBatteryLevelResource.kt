package com.example.robot.resource

import com.example.robot.validator.SerialNumber

data class CheckRobotBatteryLevelResource(
    @SerialNumber
    val serialNumber: String?
) {}