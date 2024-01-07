package com.example.robot.repository

import com.example.robot.repository.impl.CarriedMedicationHashmapRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CarriedMedicationServiceRepositoryTest() {
    private val carriedMedicationRepository: CarriedMedicationHashmapRepository = CarriedMedicationHashmapRepository()
    private val serialNumber : String = "ABC123"
    private val medicationNames : List<String> = arrayListOf("PANADOL", "ADOL", "BRUFEN")

    @BeforeEach
    fun populateData(){
        carriedMedicationRepository.storeLoadedMedication(serialNumber, medicationNames)
    }

    @Test
    fun getLoadedMedicationHappyTest(){
        assert(carriedMedicationRepository.getLoadedMedication(serialNumber) == medicationNames)
    }

    @Test
    fun getLoadedMedicationOfNotLoadedRobot(){
        val notLoadedRobotSerialNumber = "abc"
        assert(carriedMedicationRepository.getLoadedMedication(notLoadedRobotSerialNumber) == emptyList<String>())
    }
}