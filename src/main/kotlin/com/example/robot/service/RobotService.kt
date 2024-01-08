package com.example.robot.service

import com.example.robot.command.robot.RegisterRobotCommand
import com.example.robot.model.Robot
import com.example.robot.query.CheckRobotBatteryLevelQuery


interface RobotService {
    fun registerRobot(registerRobotCommand : RegisterRobotCommand) : Robot
    fun getAvailableRobotsForLoading() : List<Robot>
    fun checkRobotBatteryLevel(checkRobotBatteryLevelQuery : CheckRobotBatteryLevelQuery) : Int


}