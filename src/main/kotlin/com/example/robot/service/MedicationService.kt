package com.example.robot.service

import com.example.robot.command.RegisterMedicationCommand
import com.example.robot.model.Medication

interface MedicationService {
    fun registerMedication(registerMedicationCommand: RegisterMedicationCommand) : Medication
}