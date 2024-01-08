package com.example.robot.service

import com.example.robot.command.LoadRobotWithMedicationCommand


interface CarriedMedicationService {
    fun loadRobotWithMedication(loadRobotWithMedicationCommand: LoadRobotWithMedicationCommand)
    fun getLoadedMedication(serialNumber : String) : List<String>
}