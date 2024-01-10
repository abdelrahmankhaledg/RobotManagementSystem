package com.example.robot.service.impl

import com.example.robot.command.LoadRobotWithMedicationCommand
import com.example.robot.command.UnloadRobotCommand
import com.example.robot.exception.MedicationNameNotMatchingRulesException
import com.example.robot.exception.RobotCannotBeLoadedException
import com.example.robot.exception.RobotNotFoundException
import com.example.robot.exception.WeightLimitExceededException
import com.example.robot.model.Medication
import com.example.robot.model.Robot
import com.example.robot.model.RobotDynamicState
import com.example.robot.model.enums.RobotState
import com.example.robot.query.GetRobotLoadedMedicationsQuery
import com.example.robot.repository.CarriedMedicationRepository
import com.example.robot.repository.MedicationRepository
import com.example.robot.repository.RobotDynamicStateRepository
import com.example.robot.repository.RobotRepository
import com.example.robot.service.CarriedMedicationService
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CarriedMedicationServiceImpl(
        private val robotRepository: RobotRepository,
        private val robotDynamicStateRepository: RobotDynamicStateRepository,
        private val medicationRepository: MedicationRepository,
        private val carriedMedicationRepository: CarriedMedicationRepository
    ) : CarriedMedicationService
{
    @Transactional
    override fun loadRobotWithMedication(loadRobotWithMedicationCommand: LoadRobotWithMedicationCommand): Unit {
        val serialNumber : String = loadRobotWithMedicationCommand.serialNumber
        val medicationNames : List<String> = loadRobotWithMedicationCommand.medicationNames
        validateMedicationNames(medicationNames)
        val robot : Robot = robotRepository.findByIdOrNull(serialNumber) ?: throw RobotNotFoundException()
        canLoadMedications(robot, medicationNames)
        loadRobot(robot, medicationNames)
    }
    private fun validateMedicationNames(medicationNames : List<String?>){
        val regexExpression : Regex = Regex("^[a-zA-Z0-9_-]+\$")
        for(name : String? in medicationNames){
            if(name == null || !regexExpression.matches(name)){
                throw MedicationNameNotMatchingRulesException()
            }
        }
    }
    private fun canLoadMedications(
        robot: Robot,
        medicationNames: List<String>
    )
    {
        val robotDynamicState : RobotDynamicState = robot.robotDynamicState
        if(robotDynamicState.batteryCapacity <= 25 ||
            RobotState.IDLE != robotDynamicState.robotState)
                throw RobotCannotBeLoadedException()

        val medications : MutableIterable<Medication> = medicationRepository.findAllById(medicationNames)
        val medicationsWeight : Int = getMedicationsWeight(medicationNames, medications)
        if(medicationsWeight > robot.weightLimit)
            throw  WeightLimitExceededException()
    }
    fun getMedicationsWeight(medicationNames : List<String>, medications : MutableIterable<Medication>) : Int{
        var medicationsWeight : Int = 0
        val uniqueMedicationNames : HashMap<String, Int> = HashMap()
        for(name in medicationNames){
            uniqueMedicationNames[name] = uniqueMedicationNames.getOrDefault(name,0) + 1
        }
        for(medication in medications){
            medicationsWeight += medication.weight * uniqueMedicationNames[medication.name]!!
        }
        return medicationsWeight
    }

    private fun loadRobot(robot: Robot, medicationNames: List<String>){
        changeRobotState(robot.robotDynamicState, RobotState.LOADING)
        carriedMedicationRepository.storeLoadedMedication(robot.serialNumber, medicationNames)
        changeRobotState(robot.robotDynamicState, RobotState.LOADED)
    }

    private fun changeRobotState(robotDynamicState: RobotDynamicState, robotState : RobotState){
        robotDynamicState.robotState = robotState
        robotDynamicStateRepository.save(robotDynamicState)
    }

    override fun getLoadedMedication(getRobotLoadedMedicationsQuery: GetRobotLoadedMedicationsQuery): List<String> {
        return carriedMedicationRepository.getLoadedMedication(getRobotLoadedMedicationsQuery.serialNumber)
    }

    override fun unloadRobot(unloadRobotCommand: UnloadRobotCommand) {
        val robotDynamicState = robotDynamicStateRepository
            .findByIdOrNull(unloadRobotCommand.serialNumber)
            ?: throw RobotNotFoundException()
        carriedMedicationRepository.unloadRobot(unloadRobotCommand.serialNumber)
        changeRobotState(robotDynamicState, RobotState.IDLE)
    }
}