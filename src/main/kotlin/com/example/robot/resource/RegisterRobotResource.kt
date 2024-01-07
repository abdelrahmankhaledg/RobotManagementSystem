package com.example.robot.resource

import com.example.robot.validator.*

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