package com.example.robot.repository

import com.example.robot.model.Robot
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface RobotRepository : CrudRepository<Robot, String> {
    @Query("SELECT R.robotDynamicState.batteryCapacity from Robot R WHERE R.serialNumber = :serialNumber")
    fun findBatteryCapacity(serialNumber : String): Int


    @Query("SELECT R from Robot R where R.robotDynamicState.robotState = 'IDLE' and R.robotDynamicState.batteryCapacity > 25")
    fun findAvailableRobotsForLoading() : List<Robot>
}