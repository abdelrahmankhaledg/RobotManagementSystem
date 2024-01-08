package com.example.robot.service

import com.example.robot.command.LoadRobotWithMedicationCommand
import com.example.robot.query.GetRobotLoadedMedicationsQuery


interface CarriedMedicationService {
    fun loadRobotWithMedication(loadRobotWithMedicationCommand: LoadRobotWithMedicationCommand)
    fun getLoadedMedication(getRobotLoadedMedicationsQuery: GetRobotLoadedMedicationsQuery) : List<String>
}