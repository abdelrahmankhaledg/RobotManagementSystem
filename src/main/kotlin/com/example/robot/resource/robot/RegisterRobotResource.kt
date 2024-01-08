package com.example.robot.resource.robot

import com.example.robot.validator.robot.*
import org.springframework.validation.annotation.Validated

@Validated
data class RegisterRobotResource (
    @SerialNumber
    val serialNumber : String?,

    @RobotModel
    val robotModel : String?,

    @WeightLimit
    val weightLimit : Int?,

    @BatteryCapacity
    val batteryCapacity : Int? = 100,

    @RobotState
    val robotState : String? = "IDLE"

){

}