package com.example.robot.repository

import com.example.robot.logging.model.BatteryLogEntry
import com.example.robot.model.RobotDynamicState
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface RobotDynamicStateRepository : CrudRepository<RobotDynamicState, String>{
    @Query("SELECT new com.example.robot.logging.model.BatteryLogEntry(RDS.serialNumber, RDS.batteryCapacity) FROM RobotDynamicState RDS")
    fun findBatteryLevelForAllRobots() : List<BatteryLogEntry>
}