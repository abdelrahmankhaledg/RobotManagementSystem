package com.example.robot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RobotApplication

fun main(args: Array<String>) {
	runApplication<RobotApplication>(*args)
}
