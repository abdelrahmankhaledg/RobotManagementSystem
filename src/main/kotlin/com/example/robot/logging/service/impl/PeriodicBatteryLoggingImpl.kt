package com.example.robot.logging.service.impl

import com.example.robot.logging.model.BatteryLog
import com.example.robot.logging.model.BatteryLogEntry
import com.example.robot.logging.repository.BatteryLogRepository
import com.example.robot.logging.service.PeriodicBatteryLogging
import com.example.robot.repository.RobotDynamicStateRepository
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class PeriodicBatteryLoggingImpl(
    @Autowired private val robotDynamicStateRepository: RobotDynamicStateRepository,
    @Autowired private val batteryLogRepository: BatteryLogRepository
) : PeriodicBatteryLogging {
    @Scheduled(fixedRate = 20000, initialDelay = 1000)
    override fun logBatteryLevelsPeriodically(){
        val logEntries : List<BatteryLogEntry> = robotDynamicStateRepository.findBatteryLevelForAllRobots()
        val batteryLog : BatteryLog = BatteryLog(
            jsonLogEntries = jacksonObjectMapper().writeValueAsString(logEntries))
        batteryLogRepository.save(batteryLog)
    }
}