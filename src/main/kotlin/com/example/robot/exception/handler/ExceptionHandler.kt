package com.example.robot.exception.handler

import com.example.robot.exception.RobotCannotBeLoadedException
import com.example.robot.exception.RobotNotFoundException
import com.example.robot.exception.WeightLimitExceededException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(RobotNotFoundException::class)
    fun handleRobotNotFoundException(ex: RobotNotFoundException): ResponseEntity<String> {
        return ResponseEntity(ex.message, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(RobotCannotBeLoadedException::class)
    fun handleRobotCannotBeLoadedException(ex: RobotCannotBeLoadedException): ResponseEntity<String> {
        return ResponseEntity(ex.message, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(WeightLimitExceededException::class)
    fun handleWeightLimitExceededException(ex: WeightLimitExceededException): ResponseEntity<String> {
        return ResponseEntity(ex.message, HttpStatus.BAD_REQUEST)
    }


    @ExceptionHandler(Exception::class)
    fun handleGeneralException(ex: Exception): ResponseEntity<String> {
        return ResponseEntity("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR)
    }
}
