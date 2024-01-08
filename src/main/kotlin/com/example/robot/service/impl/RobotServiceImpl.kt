package com.example.robot.service.impl

import com.example.robot.command.RegisterRobotCommand
import com.example.robot.exception.RobotAlreadyExistsException
import com.example.robot.exception.RobotNotFoundException
import com.example.robot.model.Robot
import com.example.robot.model.RobotDynamicState
import com.example.robot.query.CheckRobotBatteryLevelQuery
import com.example.robot.repository.RobotRepository
import com.example.robot.service.RobotService
import org.springframework.stereotype.Service

@Service
class RobotServiceImpl(
    private val robotRepository: RobotRepository
    ): RobotService {
    override fun registerRobot(registerRobotCommand: RegisterRobotCommand): Robot {
        throwIfRobotAlreadyRegistered(registerRobotCommand.serialNumber)
        val newRobot : Robot = createRobot(registerRobotCommand)
        return robotRepository.save(newRobot)
    }
    fun throwIfRobotAlreadyRegistered(serialNumber : String){
        if(robotRepository.existsById(serialNumber)){
            throw RobotAlreadyExistsException()
        }
    }
    private fun createRobot(registerRobotCommand : RegisterRobotCommand): Robot{
        return Robot(
            serialNumber = registerRobotCommand.serialNumber,
            robotModel = registerRobotCommand.robotModel,
            weightLimit = registerRobotCommand.weightLimit,
            robotDynamicState = RobotDynamicState(
                serialNumber = registerRobotCommand.serialNumber,
                batteryCapacity = registerRobotCommand.batteryCapacity,
                robotState = registerRobotCommand.robotState
            )
        )
    }
    override fun getAvailableRobotsForLoading(): List<Robot> {
        return robotRepository.findAvailableRobotsForLoading()
    }

    override fun checkRobotBatteryLevel(checkRobotBatteryLevelQuery: CheckRobotBatteryLevelQuery): Int {
        val serialNumber : String = checkRobotBatteryLevelQuery.serialNumber
        throwIfRobotNotExistent(serialNumber)
        return robotRepository.findBatteryCapacity(serialNumber)
    }
    fun throwIfRobotNotExistent(serialNumber: String){
        if(!robotRepository.existsById(serialNumber)){
            throw RobotNotFoundException()
        }
    }

}