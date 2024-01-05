package com.example.robot.repository

interface CarriedMedicationRepository {
    fun storeLoadedMedication(serialNumber : String, medicationNames : List<String>) : Unit
    fun getLoadedMedication(serialNumber : String) : List<String>?
}