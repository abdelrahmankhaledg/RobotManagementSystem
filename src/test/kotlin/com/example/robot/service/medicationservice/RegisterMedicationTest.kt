package com.example.robot.service.medicationservice

import com.example.robot.command.RegisterMedicationCommand
import com.example.robot.exception.MedicationAlreadyExistsException
import com.example.robot.model.Medication
import com.example.robot.repository.MedicationRepository
import com.example.robot.service.MedicationService
import com.example.robot.service.impl.MedicationServiceImpl
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class RegisterMedicationTest() {
    private val medicationRepository: MedicationRepository = mockk()
    private val medicationService : MedicationService = MedicationServiceImpl(medicationRepository)
    private val registerMedicationCommand : RegisterMedicationCommand = RegisterMedicationCommand(
        name = "PANADOL",
        weight = 10,
        code =  "ABC123",
        imageUrl = "KKK@google.com",
    )
    private fun createMedication(registerMedicationCommand: RegisterMedicationCommand) : Medication {
        return Medication(
            name = registerMedicationCommand.name,
            weight = registerMedicationCommand.weight,
            code = registerMedicationCommand.code,
            imageUrl = registerMedicationCommand.imageUrl
        )
    }
    @Test
    fun registerMedicationHappy(){
        val medication : Medication = createMedication(registerMedicationCommand)
        every { medicationRepository.existsById(medication.name) } returns false
        every { medicationRepository.save(medication) } returns medication
        val savedMedication : Medication = medicationService.registerMedication(registerMedicationCommand)
        assert(savedMedication.name == medication.name)
        assert(savedMedication.weight == medication.weight)
        assert(savedMedication.code == medication.code)
        assert(savedMedication.imageUrl == medication.imageUrl)
    }

    @Test
    fun registerExistentMedication(){
        every { medicationRepository.existsById(registerMedicationCommand.name) } returns true
        assertThrows<MedicationAlreadyExistsException> {  medicationService.registerMedication(registerMedicationCommand)}
    }


}