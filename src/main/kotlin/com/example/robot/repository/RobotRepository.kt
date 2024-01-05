package com.example.robot.repository

import com.example.robot.model.Robot
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface RobotRepository : CrudRepository<Robot, String> {
}