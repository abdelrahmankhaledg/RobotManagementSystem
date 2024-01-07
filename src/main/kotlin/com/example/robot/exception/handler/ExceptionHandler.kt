package com.example.robot.exception.handler

import com.example.robot.exception.RobotCannotBeLoadedException
import com.example.robot.exception.RobotNotFoundException
import com.example.robot.exception.WeightLimitExceededException
import com.example.robot.exception.enums.ErrorsEnum
import com.example.robot.exception.reponse.ErrorResponse
import jakarta.validation.ConstraintViolationException
import org.apache.catalina.valves.ErrorReportValve
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(RobotNotFoundException::class)
    fun handleRobotNotFoundException(ex: RobotNotFoundException): ResponseEntity<ErrorResponse> {
        return ResponseEntity(
            ErrorResponse(
                errorDescription = ErrorsEnum.ROBOT_NOT_FOUND.description,
                errorCode = ErrorsEnum.ROBOT_NOT_FOUND.errorCode),
            ErrorsEnum.ROBOT_NOT_FOUND.httpStatus)
    }

    @ExceptionHandler(RobotCannotBeLoadedException::class)
    fun handleRobotCannotBeLoadedException(ex: RobotCannotBeLoadedException): ResponseEntity<ErrorResponse> {
        return ResponseEntity(
        ErrorResponse(
            errorDescription = ErrorsEnum.ROBOT_CANNOT_BE_LOADED.description,
            errorCode = ErrorsEnum.ROBOT_CANNOT_BE_LOADED.errorCode),
            ErrorsEnum.ROBOT_CANNOT_BE_LOADED.httpStatus)
    }

    @ExceptionHandler(WeightLimitExceededException::class)
    fun handleWeightLimitExceededException(ex: WeightLimitExceededException): ResponseEntity<ErrorResponse> {
        return ResponseEntity(
            ErrorResponse(
                errorDescription = ErrorsEnum.WEIGHT_LIMIT_EXCEEDED.description,
                errorCode = ErrorsEnum.WEIGHT_LIMIT_EXCEEDED.errorCode),
            ErrorsEnum.WEIGHT_LIMIT_EXCEEDED.httpStatus)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(ex : MethodArgumentNotValidException) : ResponseEntity<ErrorResponse>{
        var errorMessage = ""
        for (fieldError in ex.fieldErrors) {
            errorMessage += "${fieldError.defaultMessage}\n"
        }
        return ResponseEntity(
            ErrorResponse(
                errorDescription = errorMessage,
                errorCode = ErrorsEnum.METHOD_ARGUMENT_NOT_VALID.errorCode),
            ErrorsEnum.METHOD_ARGUMENT_NOT_VALID.httpStatus)
    }

    @ExceptionHandler(Exception::class)
    fun handleGeneralException(ex: Exception): ResponseEntity<ErrorResponse> {
        println(ex.printStackTrace())
        return ResponseEntity(
            ErrorResponse(
                errorDescription = ErrorsEnum.INTERNAL_SERVER_ERROR.description,
                errorCode = ErrorsEnum.INTERNAL_SERVER_ERROR.errorCode),
            ErrorsEnum.INTERNAL_SERVER_ERROR.httpStatus)
    }
}
