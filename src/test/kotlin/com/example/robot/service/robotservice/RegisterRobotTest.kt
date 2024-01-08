package com.example.robot.service.robotservice

import com.example.robot.command.RegisterRobotCommand
import com.example.robot.exception.RobotAlreadyExistsException
import com.example.robot.model.Robot
import com.example.robot.model.RobotDynamicState
import com.example.robot.model.enums.RobotModel
import com.example.robot.model.enums.RobotState
import com.example.robot.repository.RobotRepository
import com.example.robot.service.RobotService
import com.example.robot.service.impl.RobotServiceImpl
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class RegisterRobotTest() {
    private val robotRepository: RobotRepository = mockk()
    private val robotService : RobotService = RobotServiceImpl(robotRepository)
    private val serialNumber = "ABC123"
    private fun createRobot(registerRobotCommand: RegisterRobotCommand) : Robot{
        return Robot(
            serialNumber = registerRobotCommand.serialNumber,
            robotModel = registerRobotCommand.robotModel,
            weightLimit = registerRobotCommand.weightLimit,
            RobotDynamicState(
                serialNumber = registerRobotCommand.serialNumber,
                batteryCapacity = registerRobotCommand.batteryCapacity,
                robotState = registerRobotCommand.robotState
            )
        )
    }
    @Test
    fun registerRobotHappy(){
        val registerRobotCommand : RegisterRobotCommand = RegisterRobotCommand(
            serialNumber = serialNumber,
            robotModel = RobotModel.LIGHTWEIGHT,
            weightLimit =  300,
            batteryCapacity = 100,
            robotState = RobotState.IDLE
        )
        val robot : Robot = createRobot(registerRobotCommand)
        every { robotRepository.existsById(robot.serialNumber) } returns false
        every { robotRepository.save(robot) } returns robot
        val savedRobot : Robot = robotService.registerRobot(registerRobotCommand)
        assert(savedRobot.serialNumber == robot.serialNumber)
        assert(savedRobot.robotModel == robot.robotModel)
        assert(savedRobot.weightLimit == robot.weightLimit)
        assert(savedRobot.robotDynamicState.batteryCapacity == robot.robotDynamicState.batteryCapacity)
        assert(savedRobot.robotDynamicState.robotState == robot.robotDynamicState.robotState)
    }

    @Test
    fun registerExistentRobot(){
        val registerRobotCommand : RegisterRobotCommand = RegisterRobotCommand(
            serialNumber = serialNumber,
            robotModel = RobotModel.LIGHTWEIGHT,
            weightLimit =  300,
            batteryCapacity = 100,
            robotState = RobotState.IDLE
        )
        every { robotRepository.existsById(registerRobotCommand.serialNumber) } returns true
        assertThrows<RobotAlreadyExistsException> {  robotService.registerRobot(registerRobotCommand)}
    }


}