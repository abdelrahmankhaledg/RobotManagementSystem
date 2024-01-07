package com.example.robot.logging.repository

import com.example.robot.logging.model.BatteryLog
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.sql.Timestamp
@Repository
interface BatteryLogRepository :  CrudRepository<BatteryLog, Timestamp>{
}