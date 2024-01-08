package com.example.robot.command.robot

import com.example.robot.model.enums.RobotModel
import com.example.robot.model.enums.RobotState
import com.example.robot.resource.robot.RegisterRobotResource


class RegisterRobotCommand (
    val serialNumber : String,
    val robotModel : RobotModel,
    val weightLimit : Int,
    val batteryCapacity : Int,
    val robotState : RobotState
){
    constructor(registerRobotResource: RegisterRobotResource) :
            this(
                serialNumber = registerRobotResource.serialNumber!!,
                robotModel = RobotModel.valueOf(registerRobotResource.robotModel!!),
                weightLimit = registerRobotResource.weightLimit!!,
                batteryCapacity = registerRobotResource.batteryCapacity!!,
                robotState = RobotState.valueOf(registerRobotResource.robotState!!)
            )
}