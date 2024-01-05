package com.example.robot.model

import com.example.robot.model.enums.RobotState
import com.example.robot.model.enums.converters.RobotStateConverter
import jakarta.persistence.*

@Entity
@Table(name = "ROBOT_DYNAMIC_STATE")
data class RobotDynamicState (
    @Column(name = "BATTERY_CAPACITY")
    var batteryCapacity : Int,

    @Column(name = "ROBOT_STATE")
    @Convert(converter = RobotStateConverter::class)
    var robotState : RobotState
){
}