package com.example.robot.controller.dispatchcontroller



import com.example.robot.command.RegisterRobotCommand
import com.example.robot.controller.DispatchController
import com.example.robot.model.Robot
import com.example.robot.model.RobotDynamicState
import com.example.robot.reponse.enums.ResponseEnum
import com.example.robot.resource.RegisterRobotResource
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

@WebMvcTest(controllers = [DispatchController::class])
class RegisterRobotTest (@Autowired val mockMvc : MockMvc) {

    @MockkBean
    lateinit var carriedMedicationService: CarriedMedicationService

    @MockkBean
    lateinit var robotService: RobotService


    private val mapper = jacksonObjectMapper()


    private fun createRobot(registerRobotCommand: RegisterRobotCommand): Robot {
        return Robot(
            serialNumber = registerRobotCommand.serialNumber,
            robotModel = registerRobotCommand.robotModel,
            weightLimit = registerRobotCommand.weightLimit,
            robotDynamicState = RobotDynamicState(
                serialNumber = registerRobotCommand.serialNumber,
                batteryCapacity = registerRobotCommand.batteryCapacity,
                robotState = registerRobotCommand.robotState
            )
        )
    }

    @Test
    fun registerRobotHappy() {
        val registerRobotResource: RegisterRobotResource = RegisterRobotResource(
            serialNumber = "ABC123",
            robotModel = "LIGHTWEIGHT",
            weightLimit = 400,
            batteryCapacity = 70,
            robotState = "IDLE"
        )
        val registerRobotCommand: RegisterRobotCommand = RegisterRobotCommand(registerRobotResource)
        val robot: Robot = createRobot(registerRobotCommand)
        every { robotService.registerRobot(any()) } returns robot
        mockMvc.perform(
            MockMvcRequestBuilders.post("/robot/register")
                .content(mapper.writeValueAsString(registerRobotResource))
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isEqualTo(ResponseEnum.SUCCESS.httpStatus.value()))
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.jsonPath("$.httpResponse.description").value(ResponseEnum.SUCCESS.description))
            .andExpect(MockMvcResultMatchers.jsonPath("$.httpResponse.code").value(ResponseEnum.SUCCESS.code))
            .andExpect(MockMvcResultMatchers.jsonPath("$.payLoad.serialNumber").value(robot.serialNumber))
            .andExpect(MockMvcResultMatchers.jsonPath("$.payLoad.robotModel").value(robot.robotModel.name))
            .andExpect(MockMvcResultMatchers.jsonPath("$.payLoad.weightLimit").value(robot.weightLimit))
            .andExpect(MockMvcResultMatchers.jsonPath("$.payLoad.robotDynamicState.batteryCapacity")
                .value(robot.robotDynamicState.batteryCapacity))
            .andExpect(MockMvcResultMatchers.jsonPath("$.payLoad.robotDynamicState.robotState")
                .value(robot.robotDynamicState.robotState.name))

    }

}