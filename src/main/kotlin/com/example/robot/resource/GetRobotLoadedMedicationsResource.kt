package com.example.robot.resource

import com.example.robot.validator.SerialNumber

data class GetRobotLoadedMedicationsResource(
    @SerialNumber
    val serialNumber: String?
) {}