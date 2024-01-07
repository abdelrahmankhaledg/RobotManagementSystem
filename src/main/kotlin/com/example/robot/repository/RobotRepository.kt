package com.example.robot.repository

import com.example.robot.model.Robot
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface RobotRepository : CrudRepository<Robot, String> {
    @Query("SELECT R.batteryCapacity from ROBOT R WHERE R.serialNumber = :serialNumber")
    fun findBatteryCapacity(serialNumber : String): Int


    @Query("SELECT R from ROBOT R where R.robotState = 'IDLE' and R.batteryCapacity > 25")
    fun findAvailableRobotsForLoading() : List<Robot>
}