package com.example.robot.repository.impl

import com.example.robot.repository.CarriedMedicationRepository
import org.springframework.stereotype.Repository
import java.util.concurrent.ConcurrentHashMap

@Repository
class CarriedMedicationHashmapRepository(
    private val carriedMedication : ConcurrentHashMap<String, List<String>> = ConcurrentHashMap()

    ) : CarriedMedicationRepository {
    override fun storeLoadedMedication(serialNumber: String, medicationNames: List<String>) {
        carriedMedication[serialNumber] = medicationNames
    }

    override fun getLoadedMedication(serialNumber: String): List<String>? {
        return carriedMedication[serialNumber]
    }
}