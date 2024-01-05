package com.example.robot.model

import com.example.robot.model.enums.RobotModel
import com.example.robot.model.enums.converters.RobotModelConverter
import jakarta.persistence.*

@Entity
@Table(name = "ROBOT")
data class Robot(
    @Id
    @Column(name = "SERIAL_NUMBER")
    val serialNumber : String,

    @Column(name = "ROBOT_MODEL")
    @Convert(converter = RobotModelConverter::class)
    val robotModel : RobotModel,

    @Column(name = "WEIGHT_LIMIT")
    val weightLimit : Int,

    @OneToOne(cascade = [CascadeType.ALL])
    val robotDynamicState : RobotDynamicState


){
}