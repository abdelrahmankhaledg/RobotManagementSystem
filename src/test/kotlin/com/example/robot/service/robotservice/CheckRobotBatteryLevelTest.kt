package com.example.robot.service.robotservice

import com.example.robot.exception.RobotNotFoundException
import com.example.robot.query.CheckRobotBatteryLevelQuery
import com.example.robot.repository.RobotRepository
import com.example.robot.service.RobotService
import com.example.robot.service.impl.RobotServiceImpl
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class CheckRobotBatteryLevelTest() {
    private val robotRepository: RobotRepository = mockk()
    private val robotService : RobotService = RobotServiceImpl(robotRepository)
    private val serialNumber = "ABC123"
    @Test
    fun checkBatteryLevelHappy(){
        val batteryLevel : Int = 85
        every { robotRepository.existsById(serialNumber) } returns true
        every { robotRepository.findBatteryCapacity(serialNumber) } returns batteryLevel
        val checkRobotBatteryLevelQuery : CheckRobotBatteryLevelQuery = CheckRobotBatteryLevelQuery(serialNumber)
        assert(robotService.checkRobotBatteryLevel(checkRobotBatteryLevelQuery) == batteryLevel)
    }

    @Test
    fun checkBatteryLevelOfNonExistentRobot(){
        every { robotRepository.existsById(serialNumber) } returns false
        val checkRobotBatteryLevelQuery : CheckRobotBatteryLevelQuery = CheckRobotBatteryLevelQuery(serialNumber)
        assertThrows<RobotNotFoundException> { robotService.checkRobotBatteryLevel(checkRobotBatteryLevelQuery) }
    }


}