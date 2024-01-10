package com.example.robot.resource

import com.example.robot.validator.robot.*
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.validation.annotation.Validated

@Validated
data class RegisterRobotResource (
    @SerialNumber
    @Schema(description = "The serial number of the robot", required = true, example = "ABC1")
    val serialNumber : String?,

    @RobotModel
    @Schema(description = "The model of the robot", required = true, example = "LIGHTWEIGHT")
    val robotModel : String?,

    @WeightLimit
    @Schema(description = "The maximum weight that the robot can handle", required = true, example = "300")
    val weightLimit : Int?,

    @BatteryCapacity
    @Schema(description = "The current battery level of the robot", required = true, example = "85")
    val batteryCapacity : Int? = 100,

    @RobotState
    @Schema(description = "The state of the robot", required = true, example = "IDLE")
    val robotState : String? = "IDLE"

){

}