package com.example.robot.controller.dispatchcontroller

import com.example.robot.reponse.enums.ResponseEnum
import com.example.robot.resource.LoadRobotWithMedicationResource
import com.example.robot.service.CarriedMedicationService
import com.example.robot.service.RobotService
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

@WebMvcTest
class LoadRobotWithMedicationTest (@Autowired val mockMvc : MockMvc){

    @MockkBean
    lateinit var carriedMedicationService: CarriedMedicationService
    @MockkBean
    lateinit var robotService: RobotService

    private val serialNumber : String = "ABC123"
    private val medicationNames : List<String> = arrayListOf("PANADOL", "ADOL", "BRUFEN")

    private val mapper = jacksonObjectMapper()



    @Test
    fun loadRobotWithMedicationHappy(){
        every { carriedMedicationService.loadRobotWithMedication(serialNumber, medicationNames) } returns Unit
        mockMvc.perform(
            MockMvcRequestBuilders.post("/robot/load")
                .content(mapper.writeValueAsString(LoadRobotWithMedicationResource(
                    serialNumber = serialNumber,
                    medicationNames = medicationNames)))
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isEqualTo(ResponseEnum.SUCCESS.httpStatus.value()))
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.jsonPath("$.description").value(ResponseEnum.SUCCESS.description))
            .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(ResponseEnum.SUCCESS.code))
    }

    @Test
    fun nullRobotSerialNumber(){
        mockMvc.perform(
            MockMvcRequestBuilders.post("/robot/load")
                .content(mapper.writeValueAsString(LoadRobotWithMedicationResource(null, medicationNames)))
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isEqualTo(ResponseEnum.METHOD_ARGUMENT_NOT_VALID.httpStatus.value()))
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(ResponseEnum.METHOD_ARGUMENT_NOT_VALID.code))
    }
    @Test
    fun blankRobotSerialNumber(){
        mockMvc.perform(
            MockMvcRequestBuilders.post("/robot/load")
                .content(mapper.writeValueAsString(LoadRobotWithMedicationResource("  ", medicationNames)))
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isEqualTo(ResponseEnum.METHOD_ARGUMENT_NOT_VALID.httpStatus.value()))
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(ResponseEnum.METHOD_ARGUMENT_NOT_VALID.code))
    }

    @Test
    fun nullMedicationNamesList(){
        mockMvc.perform(
            MockMvcRequestBuilders.post("/robot/load")
                .content(mapper.writeValueAsString(LoadRobotWithMedicationResource("ABC", null)))
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isEqualTo(ResponseEnum.METHOD_ARGUMENT_NOT_VALID.httpStatus.value()))
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(ResponseEnum.METHOD_ARGUMENT_NOT_VALID.code))
    }
    @Test
    fun emptyMedicationNamesList(){
        mockMvc.perform(
            MockMvcRequestBuilders.post("/robot/load")
                .content(mapper.writeValueAsString(LoadRobotWithMedicationResource("ABC", emptyList())))
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isEqualTo(ResponseEnum.METHOD_ARGUMENT_NOT_VALID.httpStatus.value()))
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(ResponseEnum.METHOD_ARGUMENT_NOT_VALID.code))
    }

    //TODO Make this test case work
    /*@Test
    fun someMedicationNamesAreBlank(){
        mockMvc.perform(
            MockMvcRequestBuilders.post("/robot/load")
                .content(mapper.writeValueAsString(LoadRobotWithMedicationResource("ABC", listOf("","ABC"))))
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isEqualTo(ResponseEnum.METHOD_ARGUMENT_NOT_VALID.httpStatus.value()))
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(ResponseEnum.METHOD_ARGUMENT_NOT_VALID.code))
    }*/

}