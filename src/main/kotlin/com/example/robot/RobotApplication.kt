package com.example.robot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class RobotApplication

fun main(args: Array<String>) {
	runApplication<RobotApplication>(*args)
}
