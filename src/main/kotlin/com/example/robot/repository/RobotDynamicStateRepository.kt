package com.example.robot.repository

import com.example.robot.model.RobotDynamicState
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface RobotDynamicStateRepository : CrudRepository<RobotDynamicState, String>{
}