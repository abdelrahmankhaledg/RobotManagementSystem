package com.example.robot.logging.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.sql.Timestamp

@Entity
data class BatteryLog(
    @Id
    val timestamp: Timestamp = Timestamp(System.currentTimeMillis()),

    @Column(columnDefinition = "json")
    val jsonLogEntries : String
) {
}