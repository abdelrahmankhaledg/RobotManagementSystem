package com.example.robot.model

import com.example.robot.model.enums.RobotState
import com.example.robot.model.enums.converters.RobotStateConverter
import jakarta.persistence.*
import org.hibernate.annotations.OptimisticLockType
import org.hibernate.annotations.OptimisticLocking

@Entity
@Table(name = "ROBOT_DYNAMIC_STATE")
@OptimisticLocking(type = OptimisticLockType.DIRTY)
data class RobotDynamicState (
    @Id
    @Column(name = "SERIAL_NUMBER")
    val serialNumber : String,

    @Column(name = "BATTERY_CAPACITY")
    var batteryCapacity : Int,

    @Column(name = "ROBOT_STATE")
    @Convert(converter = RobotStateConverter::class)
    var robotState : RobotState
){
}