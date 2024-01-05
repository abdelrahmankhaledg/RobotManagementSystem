package com.example.robot.service.impl

import com.example.robot.service.CarriedMedication
import org.springframework.stereotype.Service
import java.util.concurrent.ConcurrentHashMap

@Service
class CarriedMedicationHashMap(private val carriedMedication: ConcurrentHashMap<String, List<String>>)
        : CarriedMedication {
    override fun loadRobotWithMedication(serialNumber: String, medicationNames: List<String>): Unit {
        carriedMedication[serialNumber] = medicationNames
        //TODO mark caller with @Transactional because it will change the state of the robot to Loading in DB
        //which should be altered if operation failed.
    }

    override fun getLoadedMedication(serialNumber: String): List<String>? {
        return carriedMedication[serialNumber]
    }
}