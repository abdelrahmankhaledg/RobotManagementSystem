package com.example.robot.controller.dispatchcontroller

import com.example.robot.controller.DispatchController
import com.example.robot.model.Robot
import com.example.robot.model.RobotDynamicState
import com.example.robot.model.enums.RobotModel
import com.example.robot.model.enums.RobotState
import com.example.robot.reponse.enums.ResponseEnum
import com.example.robot.service.CarriedMedicationService
import com.example.robot.service.RobotService
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
import kotlin.random.Random

@WebMvcTest(controllers = [DispatchController::class])
class GetAvailableRobotsForLoadingTest(@Autowired val mockMvc : MockMvc) {

    @MockkBean
    lateinit var carriedMedicationService: CarriedMedicationService
    @MockkBean
    lateinit var robotService: RobotService

    private fun generateRobots(count : Int = 2): List<Robot>{
        val robots : List<Robot> = emptyList()
        for(i in 1..count){
            robots.plus(generateRandomRobot())
        }
        return robots
    }
    private fun generateRandomRobot(): Robot {
        val serialNumberRandomSuffix = Random.nextInt(1, 1000)
        val randomWeightLimit = Random.nextInt(1, 500)
        val randomInt = Random.nextInt(1, 101)
        return Robot(
            serialNumber = "ABC${serialNumberRandomSuffix}",
            robotModel = RobotModel.LIGHTWEIGHT,
            weightLimit = randomWeightLimit,
            robotDynamicState = RobotDynamicState(
                serialNumber = "ABC${serialNumberRandomSuffix}",
                batteryCapacity = randomInt,
                robotState = RobotState.IDLE
            )
        )
    }
    @Test
    fun getAvailableRobotsForLoadingHappy(){
        val robots : List<Robot> = generateRobots()
        every { robotService.getAvailableRobotsForLoading() } returns robots
        mockMvc.perform(
            MockMvcRequestBuilders.get("/robot/available")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isEqualTo(ResponseEnum.SUCCESS.httpStatus.value()))
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.jsonPath("$.httpResponse.description").value(ResponseEnum.SUCCESS.description))
            .andExpect(MockMvcResultMatchers.jsonPath("$.httpResponse.code").value(ResponseEnum.SUCCESS.code))
            .andExpect(MockMvcResultMatchers.jsonPath("$.payLoad[0]").value(robots[0]))
            .andExpect(MockMvcResultMatchers.jsonPath("$.payLoad[1]").value(robots[1]))
    }
}