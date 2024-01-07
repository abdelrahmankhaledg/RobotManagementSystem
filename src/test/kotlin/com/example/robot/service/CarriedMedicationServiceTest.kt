package com.example.robot.service

import com.example.robot.exception.RobotCannotBeLoadedException
import com.example.robot.exception.RobotNotFoundException
import com.example.robot.exception.WeightLimitExceededException
import com.example.robot.model.Robot
import com.example.robot.model.RobotDynamicState
import com.example.robot.model.enums.RobotModel
import com.example.robot.model.enums.RobotState
import com.example.robot.repository.CarriedMedicationRepository
import com.example.robot.repository.MedicationRepository
import com.example.robot.repository.RobotDynamicStateRepository
import com.example.robot.repository.RobotRepository
import com.example.robot.service.impl.CarriedMedicationServiceImpl
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.springframework.data.repository.findByIdOrNull

class CarriedMedicationServiceTest() {
    private val robotRepository: RobotRepository = mockk()
    private val robotDynamicStateRepository: RobotDynamicStateRepository = mockk()
    private val medicationRepository: MedicationRepository = mockk()
    private val carriedMedicationRepository: CarriedMedicationRepository = mockk()
    private val carriedMedicationService: CarriedMedicationService = CarriedMedicationServiceImpl(
        robotRepository = robotRepository,
        robotDynamicStateRepository = robotDynamicStateRepository,
        medicationRepository = medicationRepository,
        carriedMedicationRepository = carriedMedicationRepository
    )

    private val serialNumber = "ABC123"
    private val robot : Robot = Robot(
        serialNumber = serialNumber,
        robotModel = RobotModel.LIGHTWEIGHT,
        weightLimit =  300,
        robotDynamicState = RobotDynamicState(
            serialNumber = serialNumber,
            batteryCapacity = 100,
            robotState = RobotState.IDLE
        )
    )
    private val medicationNames : List<String> = arrayListOf("PANADOL", "ADOL", "BRUFEN")

    @Test
    fun loadRobotWithMedicationHappy(){
        every { robotRepository.findByIdOrNull(serialNumber) } returns robot
        every { medicationRepository.findMedicationsWeight(medicationNames)} returns robot.weightLimit - 1
        every { robotDynamicStateRepository.save(any())} returns robot.robotDynamicState
        every { carriedMedicationRepository.storeLoadedMedication(serialNumber, medicationNames) } returns Unit
        assertDoesNotThrow { carriedMedicationService.loadRobotWithMedication(serialNumber, medicationNames) }
    }
    @Test
    fun loadNonExistentRobotWithMedication(){
        every { robotRepository.findByIdOrNull(serialNumber) } returns null
        assertThrows<RobotNotFoundException> { carriedMedicationService.loadRobotWithMedication(serialNumber, medicationNames) }
    }

    @Test
    fun loadNotIdleRobotWithMedication(){
        robot.robotDynamicState.robotState = RobotState.LOADING
        every { robotRepository.findByIdOrNull(serialNumber) } returns robot
        assertThrows<RobotCannotBeLoadedException> { carriedMedicationService.loadRobotWithMedication(serialNumber, medicationNames) }
    }

    @Test
    fun loadLowBatteryRobotWithMedication(){
        robot.robotDynamicState.batteryCapacity = 25
        every { robotRepository.findByIdOrNull(serialNumber) } returns robot
        assertThrows<RobotCannotBeLoadedException> { carriedMedicationService.loadRobotWithMedication(serialNumber, medicationNames) }
    }

    @Test
    fun overloadRobot() {
        every { robotRepository.findByIdOrNull(serialNumber) } returns robot
        every { medicationRepository.findMedicationsWeight(medicationNames)} returns robot.weightLimit + 1
        assertThrows<WeightLimitExceededException> { carriedMedicationService.loadRobotWithMedication(serialNumber, medicationNames)}
    }

    @Test
    fun getLoadedMedicationHappy(){
        every { carriedMedicationRepository.getLoadedMedication(serialNumber) } returns medicationNames
        assert(carriedMedicationService.getLoadedMedication(serialNumber) == medicationNames)
    }

    @Test
    fun getLoadedMedicationForNotLoadedRobot(){
        every { carriedMedicationRepository.getLoadedMedication(serialNumber) } returns emptyList()
        assert(carriedMedicationService.getLoadedMedication(serialNumber).isEmpty())
    }
}