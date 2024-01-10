package com.example.robot.controller

import com.example.robot.command.LoadRobotWithMedicationCommand
import com.example.robot.command.RegisterRobotCommand
import com.example.robot.command.UnloadRobotCommand
import com.example.robot.model.Robot
import com.example.robot.query.CheckRobotBatteryLevelQuery
import com.example.robot.query.GetRobotLoadedMedicationsQuery
import com.example.robot.reponse.HttpResponse
import com.example.robot.reponse.HttpResponseWithPayload
import com.example.robot.reponse.enums.ResponseEnum
import com.example.robot.resource.*
import com.example.robot.service.CarriedMedicationService
import com.example.robot.service.RobotService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
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
    @Operation(summary = "Register a new robot")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "The robot has been registered successfully",
            content = [ Content(mediaType = "application/json",
                schema = Schema(implementation = HttpResponseWithPayload::class)) ])])
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
    @Operation(summary = "Load a robot with medication items")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "The robot has been loaded successfully",
            content = [ Content(mediaType = "application/json",
                schema = Schema(implementation = HttpResponse::class)) ])])
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
    @Operation(summary = "Retrieve the names of the loaded medications for a given robot")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "The items have been retrieved successfully",
            content = [ Content(mediaType = "application/json",
                schema = Schema(implementation = HttpResponseWithPayload::class)) ])])
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
    @Operation(summary = "Retrieve the robots that are available for loading")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "The robots have been retrieved successfully",
            content = [ Content(mediaType = "application/json",
                schema = Schema(implementation = HttpResponseWithPayload::class)) ])])
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
    @Operation(summary = "Check the battery level for a given robot")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "The battery level has been retrieved successfully",
            content = [ Content(mediaType = "application/json",
                schema = Schema(implementation = HttpResponseWithPayload::class)) ])])
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
    @Operation(summary = "Make robot unload medications")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "The robot has been unloaded successfully",
            content = [ Content(mediaType = "application/json",
                schema = Schema(implementation = ResponseEntity::class)) ])])
    fun unloadRobot(@NotNull @Valid @RequestBody unloadRobotResource: UnloadRobotResource) : ResponseEntity<HttpResponse>{
        val unloadRobotCommand : UnloadRobotCommand = UnloadRobotCommand(unloadRobotResource)
        carriedMedicationService.unloadRobot(unloadRobotCommand)
        return ResponseEntity(
                HttpResponse(
                    description = ResponseEnum.SUCCESS.description,
                    code = ResponseEnum.SUCCESS.code
                ),
            ResponseEnum.SUCCESS.httpStatus
        )
    }
}