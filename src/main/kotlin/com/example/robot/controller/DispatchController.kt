package com.example.robot.controller

import com.example.robot.command.LoadRobotWithMedicationCommand
import com.example.robot.command.RegisterRobotCommand
import com.example.robot.model.Robot
import com.example.robot.query.CheckRobotBatteryLevelQuery
import com.example.robot.query.GetRobotLoadedMedicationsQuery
import com.example.robot.reponse.HttpResponse
import com.example.robot.reponse.HttpResponseWithPayload
import com.example.robot.reponse.enums.ResponseEnum
import com.example.robot.resource.CheckRobotBatteryLevelResource
import com.example.robot.resource.GetRobotLoadedMedicationsResource
import com.example.robot.resource.LoadRobotWithMedicationResource
import com.example.robot.resource.RegisterRobotResource
import com.example.robot.service.CarriedMedicationService
import com.example.robot.service.RobotService
import jakarta.validation.Valid
import jakarta.validation.constraints.NotNull

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/robot")
@Validated
class DispatchController(
    @Autowired val carriedMedicationService: CarriedMedicationService,
    @Autowired val robotService: RobotService
) {
    @PostMapping("/register")
    fun registerRobot(
        @NotNull @Valid @RequestBody registerRobotResource: RegisterRobotResource
    ) : ResponseEntity<HttpResponseWithPayload>{
        val registerRobotCommand : RegisterRobotCommand = RegisterRobotCommand(registerRobotResource)
        val registeredRobot = robotService.registerRobot(registerRobotCommand)
        return ResponseEntity(
            HttpResponseWithPayload(
                HttpResponse(
                    description = ResponseEnum.SUCCESS.description,
                    code = ResponseEnum.SUCCESS.code
                ),
                payLoad = registeredRobot
            ),
            ResponseEnum.SUCCESS.httpStatus
        )
    }

    @PostMapping("/load")
    fun loadRobotWithMedication(
        @NotNull @Valid @RequestBody loadRobotWithMedicationResource: LoadRobotWithMedicationResource
    ) : ResponseEntity<HttpResponse>
    {
        val loadRobotWithMedicationCommand : LoadRobotWithMedicationCommand = LoadRobotWithMedicationCommand(loadRobotWithMedicationResource)
        carriedMedicationService.loadRobotWithMedication(loadRobotWithMedicationCommand);
        return ResponseEntity(
            HttpResponse(
                description = ResponseEnum.SUCCESS.description,
                code = ResponseEnum.SUCCESS.code,
                ),
            ResponseEnum.SUCCESS.httpStatus
        )
    }

    @GetMapping("/loaded/medications")
    fun getRobotLoadedMedication(
        @NotNull @Valid getRobotLoadedMedicationsResource: GetRobotLoadedMedicationsResource
    ) : ResponseEntity<HttpResponseWithPayload>{
        val getRobotLoadedMedicationsQuery : GetRobotLoadedMedicationsQuery = GetRobotLoadedMedicationsQuery(getRobotLoadedMedicationsResource)
        val loadedMedicationNames : List<String> = carriedMedicationService.getLoadedMedication(getRobotLoadedMedicationsQuery)
        return ResponseEntity(
            HttpResponseWithPayload(
                HttpResponse(
                    description = ResponseEnum.SUCCESS.description,
                    code = ResponseEnum.SUCCESS.code
                ),
                payLoad = loadedMedicationNames
            ),
            ResponseEnum.SUCCESS.httpStatus
        )
    }
    @GetMapping("/available")
    fun getAvailableRobotsForLoading() : ResponseEntity<HttpResponseWithPayload>{
        val availableRobotsForLoading : List<Robot> = robotService.getAvailableRobotsForLoading()
        return ResponseEntity(
            HttpResponseWithPayload(
                HttpResponse(
                    description = ResponseEnum.SUCCESS.description,
                    code = ResponseEnum.SUCCESS.code
                ),
                payLoad = availableRobotsForLoading
            ),
            ResponseEnum.SUCCESS.httpStatus
        )
    }

    @GetMapping("/battery")
    fun checkRobotBatteryLevel(
        @NotNull @Valid checkRobotBatteryLevelResource: CheckRobotBatteryLevelResource
    ) : ResponseEntity<HttpResponseWithPayload>{
        val checkRobotBatteryLevelQuery : CheckRobotBatteryLevelQuery = CheckRobotBatteryLevelQuery(checkRobotBatteryLevelResource)
        val robotBatteryLevel : Int = robotService.checkRobotBatteryLevel(checkRobotBatteryLevelQuery)
        return ResponseEntity(
            HttpResponseWithPayload(
                HttpResponse(
                    description = ResponseEnum.SUCCESS.description,
                    code = ResponseEnum.SUCCESS.code
                ),
                payLoad = "${robotBatteryLevel}%"
            ),
            ResponseEnum.SUCCESS.httpStatus
        )
    }
    @DeleteMapping("/unload")
    fun unloadRobot(@RequestParam serialNumber : String) : ResponseEntity<String>{
        carriedMedicationService.unloadRobot(serialNumber)
        return ResponseEntity.ok("Robot $serialNumber was unloaded")
    }
}