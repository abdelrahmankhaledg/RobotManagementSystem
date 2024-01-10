package com.example.robot.service.carriedmedicationservice

import com.example.robot.command.LoadRobotWithMedicationCommand
import com.example.robot.command.UnloadRobotCommand
import com.example.robot.exception.MedicationNameNotMatchingRulesException
import com.example.robot.exception.RobotCannotBeLoadedException
import com.example.robot.exception.RobotNotFoundException
import com.example.robot.exception.WeightLimitExceededException
import com.example.robot.model.Medication
import com.example.robot.model.Robot
import com.example.robot.model.RobotDynamicState
import com.example.robot.model.enums.RobotModel
import com.example.robot.model.enums.RobotState
import com.example.robot.query.GetRobotLoadedMedicationsQuery
import com.example.robot.repository.CarriedMedicationRepository
import com.example.robot.repository.MedicationRepository
import com.example.robot.repository.RobotDynamicStateRepository
import com.example.robot.repository.RobotRepository
import com.example.robot.service.CarriedMedicationService
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
    private val medicationNames : List<String> = arrayListOf("PANADOL", "ADOL", "BRUFEN")
    private val loadRobotWithMedicationCommand : LoadRobotWithMedicationCommand = LoadRobotWithMedicationCommand(
        serialNumber = serialNumber,
        medicationNames = medicationNames
    )
    private val getRobotLoadedMedicationsQuery : GetRobotLoadedMedicationsQuery = GetRobotLoadedMedicationsQuery(
        serialNumber = serialNumber
    )
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

    private val medication : Medication = Medication(
        name = "PANADOL",
        weight = 300,
        code =  "ABC123",
        imageUrl = "KKK@google.com",
    )

    @Test
    fun loadRobotWithMedicationHappy(){

        every { robotRepository.findByIdOrNull(serialNumber) } returns robot
        every { medicationRepository.findAllById(any())} returns mutableListOf(medication)
        every { robotDynamicStateRepository.save(any())} returns robot.robotDynamicState
        every { carriedMedicationRepository.storeLoadedMedication(serialNumber, medicationNames) } returns Unit
        assertDoesNotThrow { carriedMedicationService.loadRobotWithMedication(loadRobotWithMedicationCommand) }
    }
    @Test
    fun loadNonExistentRobotWithMedication(){
        every { robotRepository.findByIdOrNull(serialNumber) } returns null
        assertThrows<RobotNotFoundException> { carriedMedicationService.loadRobotWithMedication(loadRobotWithMedicationCommand) }
    }

    @Test
    fun loadNotIdleRobotWithMedication(){
        robot.robotDynamicState.robotState = RobotState.LOADING
        every { robotRepository.findByIdOrNull(serialNumber) } returns robot
        assertThrows<RobotCannotBeLoadedException> { carriedMedicationService.loadRobotWithMedication(loadRobotWithMedicationCommand) }
    }

    @Test
    fun loadLowBatteryRobotWithMedication(){
        robot.robotDynamicState.batteryCapacity = 25
        every { robotRepository.findByIdOrNull(serialNumber) } returns robot
        assertThrows<RobotCannotBeLoadedException> { carriedMedicationService.loadRobotWithMedication(loadRobotWithMedicationCommand) }
    }

    @Test
    fun overloadRobot() {
        every { robotRepository.findByIdOrNull(serialNumber) } returns robot
        every { medicationRepository.findAllById(any())} returns mutableListOf(medication, medication)
        assertThrows<WeightLimitExceededException> { carriedMedicationService.loadRobotWithMedication(loadRobotWithMedicationCommand)}
    }

    @Test
    fun someMedicationNamesAreBlank(){
        val loadRobotWithMedicationCommand : LoadRobotWithMedicationCommand = LoadRobotWithMedicationCommand(
            serialNumber = serialNumber,
            medicationNames = listOf("  ", "ADOL")
        )
        assertThrows<MedicationNameNotMatchingRulesException> { carriedMedicationService.loadRobotWithMedication(loadRobotWithMedicationCommand) }
    }

    @Test
    fun getLoadedMedicationHappy(){
        every { carriedMedicationRepository.getLoadedMedication(serialNumber) } returns medicationNames
        assert(carriedMedicationService.getLoadedMedication(getRobotLoadedMedicationsQuery) == medicationNames)
    }

    @Test
    fun getLoadedMedicationForNotLoadedRobot(){
        every { carriedMedicationRepository.getLoadedMedication(serialNumber) } returns emptyList()
        assert(carriedMedicationService.getLoadedMedication(getRobotLoadedMedicationsQuery).isEmpty())
    }

    @Test
    fun unloadRobotHappy(){
        val unloadRobotCommand : UnloadRobotCommand = UnloadRobotCommand("ABC123")
        every { robotDynamicStateRepository.findByIdOrNull(serialNumber) } returns robot.robotDynamicState
        every { carriedMedicationRepository.unloadRobot(serialNumber) } returns Unit
        every { robotDynamicStateRepository.save(any()) } returns robot.robotDynamicState
        assertDoesNotThrow { carriedMedicationService.unloadRobot(unloadRobotCommand)}
    }

    @Test
    fun unloadNonExistentRobot(){
        val unloadRobotCommand : UnloadRobotCommand = UnloadRobotCommand("ABC123")
        every { robotDynamicStateRepository.findByIdOrNull(serialNumber) } returns null
        assertThrows<RobotNotFoundException> { carriedMedicationService.unloadRobot(unloadRobotCommand)}
    }


}