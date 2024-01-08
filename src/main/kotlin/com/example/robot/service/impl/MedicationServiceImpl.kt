package com.example.robot.service.impl

import com.example.robot.command.RegisterMedicationCommand
import com.example.robot.exception.MedicationAlreadyExistsException
import com.example.robot.model.Medication
import com.example.robot.repository.MedicationRepository
import com.example.robot.service.MedicationService
import org.springframework.stereotype.Service

@Service
class MedicationServiceImpl(
    private val medicationRepository : MedicationRepository
    ) : MedicationService{
    override fun registerMedication(registerMedicationCommand: RegisterMedicationCommand): Medication {
        throwIfMedicationAlreadyRegistered(registerMedicationCommand.name)
        val newMedication : Medication = createMedication(registerMedicationCommand)
        return medicationRepository.save(newMedication)
    }

    fun throwIfMedicationAlreadyRegistered(medicationName : String){
        if(medicationRepository.existsById(medicationName)){
            throw MedicationAlreadyExistsException()
        }
    }
    private fun createMedication(registerMedicationCommand: RegisterMedicationCommand) : Medication{
        return Medication(
            name = registerMedicationCommand.name,
            weight = registerMedicationCommand.weight,
            code = registerMedicationCommand.code,
            imageUrl = registerMedicationCommand.imageUrl
        )
    }
}