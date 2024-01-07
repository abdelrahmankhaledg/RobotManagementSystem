package com.example.robot.service


interface CarriedMedicationService {
    fun loadRobotWithMedication(serialNumber : String, medicationNames : List<String>)
    fun getLoadedMedication(serialNumber : String) : List<String>?
}