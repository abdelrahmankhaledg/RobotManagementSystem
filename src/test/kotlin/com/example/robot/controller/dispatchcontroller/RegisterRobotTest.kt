package com.example.robot.controller.dispatchcontroller

import com.example.robot.command.RegisterMedicationCommand
import com.example.robot.controller.MedicationController
import com.example.robot.model.Medication
import com.example.robot.reponse.enums.ResponseEnum
import com.example.robot.resource.RegisterMedicationResource
import com.example.robot.service.MedicationService
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.isEqualTo

@WebMvcTest(controllers = [MedicationController::class])
class RegisterRobotTest (@Autowired val mockMvc : MockMvc) {

    @MockkBean
    lateinit var medicationService: MedicationService

    private val mapper = jacksonObjectMapper()


    private fun createMedication(registerMedicationCommand: RegisterMedicationCommand): Medication {
        return Medication(
            name = registerMedicationCommand.name,
            weight = registerMedicationCommand.weight,
            code = registerMedicationCommand.code,
            imageUrl = registerMedicationCommand.imageUrl
        )
    }

    @Test
    fun registerMedicationHappy() {
        val registerMedicationResource : RegisterMedicationResource = RegisterMedicationResource(
            name = "PANADOL",
            weight = 100,
            code = "ABC123",
            imageUrl = "https://www.google.com"
        )
        val registerMedicationCommand : RegisterMedicationCommand = RegisterMedicationCommand(registerMedicationResource)
        val medication : Medication = createMedication(registerMedicationCommand)
        every { medicationService.registerMedication(registerMedicationCommand) } returns medication
        mockMvc.perform(
            MockMvcRequestBuilders.post("/medication/register")
                .content(mapper.writeValueAsString(registerMedicationResource))
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isEqualTo(ResponseEnum.SUCCESS.httpStatus.value()))
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.jsonPath("$.httpResponse.description").value(ResponseEnum.SUCCESS.description))
            .andExpect(MockMvcResultMatchers.jsonPath("$.httpResponse.code").value(ResponseEnum.SUCCESS.code))
            .andExpect(MockMvcResultMatchers.jsonPath("$.payLoad.name").value(medication.name))
            .andExpect(MockMvcResultMatchers.jsonPath("$.payLoad.weight").value(medication.weight))
            .andExpect(MockMvcResultMatchers.jsonPath("$.payLoad.code").value(medication.code))
            .andExpect(MockMvcResultMatchers.jsonPath("$.payLoad.imageUrl").value(medication.imageUrl))
    }

}