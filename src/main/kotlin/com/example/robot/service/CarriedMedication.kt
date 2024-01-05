package com.example.robot.service

import com.example.robot.model.Medication

interface CarriedMedication {
    fun loadRobotWithMedication(serialNumber : String, medicationNames : List<String>) : Unit
    fun getLoadedMedication(serialNumber : String) : List<String>?
}