package com.example.robot.service.impl

import com.example.robot.repository.CarriedMedicationRepository
import com.example.robot.service.CarriedMedication
import org.springframework.stereotype.Service

@Service
class CarriedMedicationHashmap(private val carriedMedicationRepository: CarriedMedicationRepository)
        : CarriedMedication {
    override fun loadRobotWithMedication(serialNumber: String, medicationNames: List<String>): Unit {
        carriedMedicationRepository.storeLoadedMedication(serialNumber, medicationNames)
        //TODO mark caller with @Transactional because it will change the state of the robot to Loading in DB
        //which should be altered if operation failed.
    }

    override fun getLoadedMedication(serialNumber: String): List<String>? {
        return carriedMedicationRepository.getLoadedMedication(serialNumber)
    }
}