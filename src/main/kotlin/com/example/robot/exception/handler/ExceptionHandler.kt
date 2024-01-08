package com.example.robot.exception.handler

import com.example.robot.exception.*
import com.example.robot.reponse.enums.ResponseEnum
import com.example.robot.reponse.HttpResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(RobotNotFoundException::class)
    fun handleRobotNotFoundException(ex: RobotNotFoundException): ResponseEntity<HttpResponse> {
        return ResponseEntity(
            HttpResponse(
                description = ResponseEnum.ROBOT_NOT_FOUND.description,
                code = ResponseEnum.ROBOT_NOT_FOUND.code),
            ResponseEnum.ROBOT_NOT_FOUND.httpStatus)
    }

    @ExceptionHandler(RobotCannotBeLoadedException::class)
    fun handleRobotCannotBeLoadedException(ex: RobotCannotBeLoadedException): ResponseEntity<HttpResponse> {
        return ResponseEntity(
        HttpResponse(
            description = ResponseEnum.ROBOT_CANNOT_BE_LOADED.description,
            code = ResponseEnum.ROBOT_CANNOT_BE_LOADED.code),
            ResponseEnum.ROBOT_CANNOT_BE_LOADED.httpStatus)
    }

    @ExceptionHandler(WeightLimitExceededException::class)
    fun handleWeightLimitExceededException(ex: WeightLimitExceededException): ResponseEntity<HttpResponse> {
        return ResponseEntity(
            HttpResponse(
                description = ResponseEnum.WEIGHT_LIMIT_EXCEEDED.description,
                code = ResponseEnum.WEIGHT_LIMIT_EXCEEDED.code),
            ResponseEnum.WEIGHT_LIMIT_EXCEEDED.httpStatus)
    }

    @ExceptionHandler(RobotAlreadyExistsException::class)
    fun handleRobotAlreadyExistsException(ex: RobotAlreadyExistsException): ResponseEntity<HttpResponse> {
        return ResponseEntity(
            HttpResponse(
                description = ResponseEnum.ROBOT_ALREADY_EXISTS.description,
                code = ResponseEnum.ROBOT_ALREADY_EXISTS.code),
            ResponseEnum.ROBOT_ALREADY_EXISTS.httpStatus)
    }

    @ExceptionHandler(MedicationAlreadyExistsException::class)
    fun handleMedicationAlreadyExistsException(ex: MedicationAlreadyExistsException): ResponseEntity<HttpResponse> {
        return ResponseEntity(
            HttpResponse(
                description = ResponseEnum.MEDICATION_ALREADY_EXISTS.description,
                code = ResponseEnum.MEDICATION_ALREADY_EXISTS.code),
            ResponseEnum.MEDICATION_ALREADY_EXISTS.httpStatus)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(ex : MethodArgumentNotValidException) : ResponseEntity<HttpResponse>{
        var errorMessage = ""
        for (fieldError in ex.fieldErrors) {
            errorMessage += "${fieldError.defaultMessage}\n"
        }
        return ResponseEntity(
            HttpResponse(
                description = errorMessage,
                code = ResponseEnum.METHOD_ARGUMENT_NOT_VALID.code),
            ResponseEnum.METHOD_ARGUMENT_NOT_VALID.httpStatus)
    }

    @ExceptionHandler(Exception::class)
    fun handleGeneralException(ex: Exception): ResponseEntity<HttpResponse> {
        println(ex.printStackTrace())
        return ResponseEntity(
            HttpResponse(
                description = ResponseEnum.INTERNAL_SERVER_ERROR.description,
                code = ResponseEnum.INTERNAL_SERVER_ERROR.code),
            ResponseEnum.INTERNAL_SERVER_ERROR.httpStatus)
    }
}
